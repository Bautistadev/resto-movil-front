package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.resto.EntityDTO.ClienteDTO;
import com.example.resto.EntityDTO.EmpleadoDTO;
import com.example.resto.Service.ClienteService;
import com.example.resto.Service.EmpleadoService;
import com.example.resto.Utils.Apis;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String alerta = "hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EmpleadoService apiService = Apis.getEmpleadoService();

        Call<List<EmpleadoDTO>> call = apiService.retriveAllEmpleado();

        call.enqueue(new Callback<List<EmpleadoDTO>>() {

            @Override
            public void onResponse(Call<List<EmpleadoDTO>> call, Response<List<EmpleadoDTO>> response) {
                if (response.isSuccessful()) {
                    List<EmpleadoDTO> productos = response.body();
                    // Realiza las acciones necesarias con la lista de productos
                    for (EmpleadoDTO producto : productos) {
                        Log.d("MainActivity", "Producto: " + producto.toString());
                    }
                } else {
                    // Maneja el caso de respuesta no exitosa

                    Log.e("MainActivity", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<List<EmpleadoDTO>> call, Throwable t) {
                Log.e("MainActivity", "Error en la consulta a la API", t);
            }
        });

    }
}