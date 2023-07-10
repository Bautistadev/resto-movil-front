package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.resto.Adapter.BebidaAdapter;
import com.example.resto.EntityDTO.BebidaDTO;
import com.example.resto.EntityDTO.DetalleBebidaRequestDTO;
import com.example.resto.EntityDTO.DetallePlatoRequestDTO;
import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.PlatoDTO;
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

public class Bebida extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Callback<List<BebidaDTO>> {

    private ListView ListViewBebida;

    private List<BebidaDTO> listaBebida;

    private BebidaAdapter adapter;
    private Button btnCarga;

    private Intent panelCarga;

    Map<Integer, DetalleBebidaRequestDTO> hashMap =  new HashMap<>();

    public static List<Integer> cantidades = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebida);

        this.ListViewBebida = findViewById(R.id.ListViewBebida);
        this.btnCarga = findViewById(R.id.btnCargar);
        this.panelCarga = new Intent(Bebida.this,Carta.class);

        Call<List<BebidaDTO>> apiService = Apis.BebidaService().retriveAllBebida();
        apiService.enqueue(this);

        this.ListViewBebida.setOnItemClickListener(this);
        this.btnCarga.setOnClickListener(this);



    }

    @Override
    public void onResponse(Call<List<BebidaDTO>> call, Response<List<BebidaDTO>> response) {
        if(response.isSuccessful()){
            listaBebida = response.body();
            System.out.println("BD====: "+ listaBebida.toString());

            this.adapter = new BebidaAdapter(this,listaBebida);

            this.ListViewBebida.setAdapter(adapter);
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
    public void onFailure(Call<List<BebidaDTO>> call, Throwable t) {
        Log.e("MainActivity", "Error en la consulta a la API", t);
    }
    @Override
    public void onClick(View view) {
        List<DetalleBebidaRequestDTO>detalle = this.hashMap.values().stream().collect(Collectors.toList());
        System.out.println("listado===>"+detalle.toString());
        SharedPreferences memoria = getSharedPreferences("DetalleBebida",MODE_PRIVATE);
        SharedPreferences.Editor editor = memoria.edit();
        editor.putString("detalleBebidaMemory",new Gson().toJson(detalle));
        startActivity(this.panelCarga);
        editor.apply();
        System.out.println("DETALLE DB=====>"+memoria.getString("detalleBebidaMemory","0").toString());

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        cantidades.set(i,cantidades.get(i)+1);

        DetalleBebidaRequestDTO detalle = new DetalleBebidaRequestDTO(cantidades.get(i),listaBebida.get(i),new OcupacionDTO());

        TextView txt = view.findViewById(R.id.lblCantidad);
        txt.setText(cantidades.get(i).toString());

        this.hashMap.put(i,detalle);

        System.out.println("hash"+ this.hashMap.get(i).toString());
    }


}