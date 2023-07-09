package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

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

public class Carta extends AppCompatActivity implements Runnable {


    private static final String API_ENDPOINT = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Geolocalizacion/retriveAll"; // URL de la API que deseas monitorear
    private static final long CHECK_INTERVAL_MS = 5000; // Intervalo de tiempo en milisegundos entre cada consulta

    private OkHttpClient client;
    private List<GeolocalizacionDTO> previousList;

    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);

        client = new OkHttpClient();
        previousList = new ArrayList<>();

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
                System.out.println(resultList.toString());
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
}