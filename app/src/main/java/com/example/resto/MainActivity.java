package com.example.resto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.TokenObject;
import com.example.resto.Utils.Apis;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOError;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Callback<MesaDTO>/*, Runnable*/{

    private static final String API_ENDPOINT = "http://192.168.0.146:8080/resto-0.0.1-SNAPSHOT/api/v1/Hora/retriveHora"; // URL de la API que deseas monitorear
    private static final long CHECK_INTERVAL_MS = 5000; // Intervalo de tiempo en milisegundos entre cada consulta

   // private OkHttpClient client;
    private String previousValue;

    private Button btnEmpleados;
    private Button btnPizzeria;
    private Intent panelLogin;
    private Intent panelCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //client = new OkHttpClient();
        previousValue = "";

      //  new Thread(this).start();

        ///////////////////

        this.btnPizzeria = findViewById(R.id.btnPizzeria);
        this.btnEmpleados =  findViewById(R.id.btnEmpleados);

        this.panelLogin = new Intent(MainActivity.this, Login.class);
        this.panelCarta = new Intent(MainActivity.this,Carta.class);

        this.btnEmpleados.setOnClickListener(this);
        this.btnPizzeria.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnEmpleados){
            startActivity(this.panelLogin);
        }
        if(view.getId() == R.id.btnPizzeria){
            IntentIntegrator integrador = new IntentIntegrator(MainActivity.this);
            integrador.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrador.setPrompt("Lector - CDP");
            integrador.setCameraId(0);
            integrador.setBeepEnabled(true);
            integrador.setBarcodeImageEnabled(true);
            integrador.initiateScan();
        }
    }
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null){
            if(result.getContents() == null){
                Toast.makeText(this,"Lectora cancelada",Toast.LENGTH_LONG).show();
            }else{
               // Toast.makeText(this,result.getContents(),Toast.LENGTH_LONG).show();
                Call<MesaDTO> call = Apis.getMesaService().retriveMesaByToken(result.getContents());
                call.enqueue(this);
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResponse(Call<MesaDTO> call, Response<MesaDTO> response) {
        if(response.isSuccessful()){
            MesaDTO mesaDTO =  response.body();
            //GUARDAMOS EL TOKEN EN MEMORIA
            SharedPreferences memoria = getSharedPreferences("Mesa",MODE_PRIVATE);
            SharedPreferences.Editor editor = memoria.edit();
            editor.putString("MesaMemory",new Gson().toJson(mesaDTO));
            editor.apply();

            startActivity(panelCarta);
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









    /*
    private voi/d startMonitoring() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                fetchDataFromAPI();
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, CHECK_INTERVAL_MS);
    }*/

    /*private void fetchDataFromAPI() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    Request request = new Request.Builder()
                            .url(API_ENDPOINT)
                            .build();

                    try (Response response = client.newCall(request).execute()){
                        return response.body().string();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                if (result != null) {
                    if (!result.equals(previousValue)) {
                        Log.d("APIChangeNotifier", "¡El valor ha cambiado! Nuevo valor: " + result);
                        previousValue = result;
                    }
                }
            }
        }.execute();
    }


    @Override
    public void run() {
       // startMonitoring();

        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fetchDataFromAPI();
        }
    }*/
}