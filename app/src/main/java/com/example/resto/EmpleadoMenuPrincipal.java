package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.TokenObject;
import com.example.resto.Service.LoginService;
import com.example.resto.Service.MesaService;
import com.example.resto.Utils.Apis;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.io.IOError;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmpleadoMenuPrincipal extends AppCompatActivity implements AdapterView.OnItemClickListener, Callback<List<MesaDTO>>  {

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

    @Override
    public void onFailure(Call<List<MesaDTO>> call, Throwable t) {
        Log.e("MainActivity", "Error en la consulta a la API", t);
    }


}