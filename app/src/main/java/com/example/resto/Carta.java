package com.example.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.resto.EntityDTO.DetalleBebidaDTO;
import com.example.resto.EntityDTO.DetalleBebidaRequestDTO;
import com.example.resto.EntityDTO.DetallePlatoRequestDTO;
import com.example.resto.EntityDTO.GeolocalizacionDTO;
import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.OcupacionRequestDTO;
import com.example.resto.Utils.Apis;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class Carta extends AppCompatActivity implements Runnable,LocationListener, Callback<MesaDTO>, View.OnClickListener {


    private static final String API_ENDPOINT = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Geolocalizacion/retriveAll"; // URL de la API que deseas monitorear
    private static final long CHECK_INTERVAL_MS = 5000; // Intervalo de tiempo en milisegundos entre cada consulta

    private OkHttpClient client;
    private List<GeolocalizacionDTO> previousList;
    private ExecutorService executorService;
    private LocationManager locationManager;
    private MesaDTO mesaActual;

    private Button btnPizza;
    private Button btnBebida;

    private Intent panelPizza;
    private Intent panelBebida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        this.btnPizza = findViewById(R.id.btnPizza);
        this.btnBebida = findViewById(R.id.btnBebida);

        client = new OkHttpClient();
        previousList = new ArrayList<>();

        mesaActual = this.getMemoryTable();


        this.panelPizza = new Intent(Carta.this, PizzasActivity.class);
        this.panelBebida = new Intent(Carta.this,Bebida.class);


        int permiso = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);
        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION },1);
            }
        }

        Call<MesaDTO> call = Apis.getMesaService().update(mesaActual);
        call.enqueue(this);

        this.btnPizza.setOnClickListener(this);
        this.btnBebida.setOnClickListener(this);

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);

        locationManager =(LocationManager) Carta.this.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);

        this.GenerarOcupacion();

        Ordenes();


    }

    void GenerarOcupacion(){

        OcupacionRequestDTO ocupacion = new OcupacionRequestDTO();

        ocupacion.setMesa(this.mesaActual);
        System.out.println("id mesaaaaaaa ====>"+this.mesaActual.toString());

        SharedPreferences memoriaPlato = getSharedPreferences("Ocupacion",MODE_PRIVATE);
        SharedPreferences.Editor editorPlato = memoriaPlato.edit();
        if(memoriaPlato.contains("Ocupacion")) {
            System.out.println("No la contiene, por lo tanto la creamos");
        }else{
            System.out.println(memoriaPlato.getString("Ocupacion","0").toString());

            Call<OcupacionRequestDTO> call = Apis.getOcupacionService().save(ocupacion);
            call.enqueue(new Callback<OcupacionRequestDTO>() {
                @Override
                public void onResponse(Call<OcupacionRequestDTO> call, retrofit2.Response<OcupacionRequestDTO> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(getBaseContext(),response.body().toString(),Toast.LENGTH_SHORT).show();

                        editorPlato.putString("Ocupacion", new Gson().toJson(response.body().toString()));
                        editorPlato.apply();
                        System.out.println(response.body());
                    }else {
                        if (response.errorBody() != null) {
                            try {
                                System.out.println(response.code());
                                System.out.println("error");
                                System.out.println(response.toString());
                            } catch (IOError e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<OcupacionRequestDTO> call, Throwable t) {
                    Log.e("MainActivity", "Error en la consulta a la API", t);
                }
            });
        }
    }

    public void Ordenes(){
        List<DetallePlatoRequestDTO> detallePlato = new ArrayList<>();
        SharedPreferences memoriaPlato = getSharedPreferences("DetallePlato",MODE_PRIVATE);
        SharedPreferences.Editor editorPlato = memoriaPlato.edit();
        if(!memoriaPlato.contains("detallePlatoMemory")) {
            editorPlato.putString("detallePlatoMemory", new Gson().toJson(detallePlato));
            System.out.println("No la contiene, por lo tanto la creamos");
        }else{
            System.out.println(memoriaPlato.getString("detallePlatoMemory","0").toString());
        }
        editorPlato.apply();

        List<DetalleBebidaRequestDTO> detalleBebida = new ArrayList<>();
        SharedPreferences memoriaBebida = getSharedPreferences("DetalleBebida",MODE_PRIVATE);
        SharedPreferences.Editor editorBebida = memoriaBebida.edit();
        if(!memoriaBebida.contains("detalleBebidaMemory")) {
            editorPlato.putString("detalleBebidaMemory", new Gson().toJson(detallePlato));
            System.out.println("No la contiene, por lo tanto la creamos");
        }else{
            System.out.println(memoriaBebida.getString("detalleBebidaMemory","0").toString());
        }
        editorPlato.apply();
    }

    public MesaDTO getMemoryTable(){
        SharedPreferences sharedPreferences = getSharedPreferences("Mesa", Context.MODE_PRIVATE);
        String json = sharedPreferences.getString("MesaMemory", "0");

        Gson gson = new Gson();

        MesaDTO mesa = gson.fromJson(json, MesaDTO.class);

        //LE CAMBIAMOS EL ESTADO OCUPADO
        if(mesa.isEstado() == true)
            mesa.setEstado(false);
        else {
            mesa.setEstado(true);
        }
        return mesa;
    }

    @Override
    public void run() {

        while (true) {
            fetchDataFromAPI();
            try {
                TimeUnit.MILLISECONDS.sleep(CHECK_INTERVAL_MS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void fetchDataFromAPI() {
        try {
            Request request = new Request.Builder()
                    .url(API_ENDPOINT)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                List<GeolocalizacionDTO> resultList = parseListFromResponse(response.body().string());

                if (resultList != null && !resultList.toString().equals(previousList.toString())) {
                    previousList = resultList;
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<GeolocalizacionDTO> parseListFromResponse(String response) {
        // Implementa el código para convertir la respuesta de la API en una lista de Strings
        // Dependiendo del formato de respuesta de tu API, puedes utilizar JSON, XML u otros formatos y bibliotecas de parsing correspondientes.
        // En este ejemplo, asumimos que la respuesta es una lista de Strings separada por comas
        Gson gson = new Gson();
        Type listType = new TypeToken<List<GeolocalizacionDTO>>(){}.getType();
        List<GeolocalizacionDTO> resultList = gson.fromJson(response, listType);

        return resultList;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        System.out.println("localizacion =====> "+ location.getLatitude()+":"+location.getLongitude());

        try {

            System.out.println("DENTROO====>" + previousList.get(0).estaDentroDelArea((float) location.getLatitude(), (float)location.getLongitude()));
            if(!previousList.get(0).estaDentroDelArea((float) location.getLatitude(), (float)location.getLongitude())){

                //MOSTRAMOS MENSAJE DE EXPULSION
                MainActivity.isExpulsed =  true;



                mesaActual = this.getMemoryTable();

                //CAMBIAMOS EL ESTADO DE LA MESA
                Call<MesaDTO> call = Apis.getMesaService().update(mesaActual);
                call.enqueue(this);

                //DETENEMOS TODOS LOS PROCESOS DE BUSQUEDA
                executorService.shutdown();
                locationManager.removeUpdates(this);

                //VOLVEMOS EL MENU
                startActivity( new Intent(Carta.this,MainActivity.class));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onResponse(Call<MesaDTO> call, retrofit2.Response<MesaDTO> response) {
        if(response.isSuccessful()){
            MesaDTO mesaDTO =  response.body();
            //GUARDAMOS EL TOKEN EN MEMORIA
            SharedPreferences memoria = getSharedPreferences("Mesa",MODE_PRIVATE);
            SharedPreferences.Editor editor = memoria.edit();
            editor.putString("MesaMemory",new Gson().toJson(mesaDTO));
            editor.apply();

        }else {
            if (response.errorBody() != null) {
                try {
                    System.out.println(response.code());
                    System.out.println("error");
                } catch (IOError e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onFailure(Call<MesaDTO> call, Throwable t) {
        Log.e("MainActivity", "Error en la consulta a la API", t);
    }

    public void onBackPressed() {
        // No hacer nada para deshabilitar el botón de "Volver"
        // Puedes dejar este método vacío o agregar algún otro código personalizado si lo deseas

        mesaActual = this.getMemoryTable();
        //CAMIAMOS EL ESTADO DE DEJAR MESA
        Call<MesaDTO> call = Apis.getMesaService().update(mesaActual);
        call.enqueue(this);

        //DETENEMOS LOS PROCESOS DE BUSQUEDA
        executorService.shutdown();
        locationManager.removeUpdates(this);

        //VOLVEMOS AL MENU
        startActivity( new Intent(Carta.this,MainActivity.class));



    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnPizza){
            startActivity(this.panelPizza);
        }
        if(view.getId() == R.id.btnBebida){
            startActivity(this.panelBebida);
        }
    }
}