package com.example.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.resto.EntityDTO.GeolocalizacionDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Carta extends AppCompatActivity implements Runnable,LocationListener{


    private static final String API_ENDPOINT = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Geolocalizacion/retriveAll"; // URL de la API que deseas monitorear
    private static final long CHECK_INTERVAL_MS = 5000; // Intervalo de tiempo en milisegundos entre cada consulta

    private OkHttpClient client;
    private List<GeolocalizacionDTO> previousList;

    private ExecutorService executorService;


    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        client = new OkHttpClient();
        previousList = new ArrayList<>();

        int permiso = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);

        if(permiso == PackageManager.PERMISSION_DENIED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION },1);
            }
        }


        locationManager =(LocationManager) Carta.this.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);

        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(this);
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

        System.out.println("DENTROO====>"+previousList.get(0).estaDentroDelArea((float) -34.9233539, (float) -57.9465919));

        System.out.println("vacio: "+ previousList.get(0) == null);
    }
}