package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.resto.Adapter.MesasListAdapter;
import com.example.resto.EntityDTO.GeolocalizacionDTO;
import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.TokenObject;
import com.example.resto.Service.LoginService;
import com.example.resto.Service.MesaService;
import com.example.resto.Utils.Apis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOError;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoMenuPrincipal extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener, Callback<List<MesaDTO>>  {

    private static final String API_ENDPOINT = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Mesa/retriveAll"; // URL de la API que deseas monitorear
    private static final long CHECK_INTERVAL_MS = 5000; // Intervalo de tiempo en milisegundos entre cada consulta

    private OkHttpClient client;

    private ExecutorService executorService;
    private LocationManager locationManager;

    private ListView ListViewMesa;
    private  List<MesaDTO>listaMesa;
    private  MesasListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado_menu_principal);

        this.ListViewMesa = findViewById(R.id.ListViewMesa);

        Call<List<MesaDTO>>apiService = Apis.getMesaService().retriveAll();
        apiService.enqueue(this);

        ListViewMesa.setOnItemClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel("Mi notificacion","Mi notificacion", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        this.client = new OkHttpClient();
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);

    }

    public String getUserName(){
        SharedPreferences sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE);

        return sharedPreferences.getString("userName", "0");
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        System.out.println("xx======>"+ listaMesa.get(i));
        Intent qrActivity = new Intent(EmpleadoMenuPrincipal.this,QR.class);

        MesaDTO mesa  = listaMesa.get(i);
        QR.code = mesa.getToken();
        startActivity(qrActivity);

    }

    @Override
    public void onResponse(Call<List<MesaDTO>> call, Response<List<MesaDTO>> response) {
        if (response.isSuccessful()) {
            //FILTRAMOS LA MESA QUE LE CORRESPONDE A ESE USUARIO
            listaMesa = response.body().stream().filter(mesa -> mesa.getEmpleado().getUserName().equals(this.getUserName())).collect(Collectors.toList());

            //ADAPTAMOS
            adapter = new MesasListAdapter(this,listaMesa);

            ListViewMesa.setAdapter(adapter);
        } else {
            if(response.errorBody() != null){
                try {
                    System.out.println(response.code());
                    System.out.println("error");
                }catch (IOError e){
                    e.printStackTrace();
                }
            }
        }
    }

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

            try (okhttp3.Response response = client.newCall(request).execute()) {
                List<MesaDTO> resultList = parseListFromResponse(response.body().string());
                System.out.println("MENSAJE ENTRAAAAAAA");
                Thread.sleep(5000);
                Iterator<MesaDTO> lisTADO = listaMesa.iterator();
                Iterator<MesaDTO> result = resultList.iterator();
                Integer contador =0;
                System.out.println("contador"+ contador);
                while(lisTADO.hasNext() && result.hasNext()){
                    MesaDTO i = lisTADO.next();
                    MesaDTO j = result.next();

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(EmpleadoMenuPrincipal.this,"Mi notificacion");
                    builder.setContentTitle("Mesas");
                    builder.setSmallIcon(R.mipmap.logo_image2);
                    builder.setAutoCancel(true);

                    System.out.println(this.listaMesa.get(contador).isEstado());
                    if(!i.toString().equals(j.toString())){
                        System.out.println("i>"+i.toString());
                        System.out.println("j>"+j.toString());
                        System.out.println("contador"+ contador);
                        System.out.println(i.getId().toString()+" NO SON IGUALES");

                        if(!this.listaMesa.get(contador).isEstado()) {
                            this.listaMesa.get(contador).setEstado(true);

                            builder.setContentText("MESA:" + i.getId() + " OCUPADA");
                        }else {
                            this.listaMesa.get(contador).setEstado(false);
                            builder.setContentText("MESA:"+ i.getId()+" LIBRE");
                        }
                        NotificationManagerCompat managerCompat =  NotificationManagerCompat.from(EmpleadoMenuPrincipal.this);
                        managerCompat.notify(1,builder.build());
                    }
                    contador++;
                }


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private List<MesaDTO> parseListFromResponse(String response) {
        // Implementa el código para convertir la respuesta de la API en una lista de Strings
        // Dependiendo del formato de respuesta de tu API, puedes utilizar JSON, XML u otros formatos y bibliotecas de parsing correspondientes.
        // En este ejemplo, asumimos que la respuesta es una lista de Strings separada por comas
        Gson gson = new Gson();
        Type listType = new TypeToken<List<MesaDTO>>(){}.getType();
        List<MesaDTO> resultList = gson.fromJson(response, listType);

        return resultList;
    }
    @Override
    public void onFailure(Call<List<MesaDTO>> call, Throwable t) {
        Log.e("MainActivity", "Error en la consulta a la API", t);
    }


}