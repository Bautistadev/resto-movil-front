package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resto.Adapter.PizzasListAdapter;
import com.example.resto.EntityDTO.DetallePlatoDTO;
import com.example.resto.EntityDTO.DetallePlatoRequestDTO;
import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.PlatoDTO;
import com.example.resto.EntityDTO.Porcion;
import com.example.resto.Enum.porcionEnum;
import com.example.resto.Utils.Apis;
import com.google.gson.Gson;

import java.io.IOError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PizzasActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Callback<List<PlatoDTO>> {
    private ListView ListViewPizza;
    private List<PlatoDTO> listaPizza;
    private PizzasListAdapter adapter;

    private Button btnCargarDetalle;

    private Intent panelCarta;
    Map<Integer, DetallePlatoRequestDTO> hashMap = new HashMap<>();

    public static List<Integer> cantidades =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzas);


        this.ListViewPizza =findViewById(R.id.ListViewPizzas);
        this.btnCargarDetalle = findViewById(R.id.btnCargar);
        this.panelCarta= new Intent(PizzasActivity.this,Carta.class );

        Call<List<PlatoDTO>> apiService = Apis.getPlatoService().retriveAllPlato();
        apiService.enqueue(this);

        this.ListViewPizza.setOnItemClickListener(this);

        this.btnCargarDetalle.setOnClickListener(this);

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        cantidades.set(i,cantidades.get(i)+1);

        DetallePlatoRequestDTO detalle  =  new DetallePlatoRequestDTO(cantidades.get(i),new Porcion(1L,porcionEnum.COMPLETA),listaPizza.get(i),new OcupacionDTO());

        TextView txt = view.findViewById(R.id.lblCantidad);
        txt.setText(cantidades.get(i).toString());

        this.hashMap.put(i,detalle);

        System.out.println(hashMap.get(i).toString());


    }

    @Override
    public void onResponse(Call<List<PlatoDTO>> call, Response<List<PlatoDTO>> response) {
        if(response.isSuccessful()){
            listaPizza = response.body();

            System.out.println(listaPizza.toString());

            this.adapter = new PizzasListAdapter(this,listaPizza);

            this.ListViewPizza.setAdapter(adapter);

        }else{
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
    public void onFailure(Call<List<PlatoDTO>> call, Throwable t) {
        Log.e("MainActivity", "Error en la consulta a la API", t);
    }

    @Override
    public void onClick(View view) {
        List<DetallePlatoRequestDTO>detalle = this.hashMap.values().stream().collect(Collectors.toList());
        System.out.println("listado===>"+detalle.toString());
        SharedPreferences memoria = getSharedPreferences("DetallePlato",MODE_PRIVATE);
        SharedPreferences.Editor editor = memoria.edit();
        editor.putString("detallePlatoMemory",new Gson().toJson(detalle));
        startActivity(this.panelCarta);
        editor.apply();
        System.out.println("DETALLE DB=====>"+memoria.getString("detallePlatoMemory","0").toString());
    }
}