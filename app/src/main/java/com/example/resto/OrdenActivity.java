package com.example.resto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.resto.Adapter.BebidaAdapter;
import com.example.resto.Adapter.DetalleAdapter;
import com.example.resto.Adapter.DetalleBebidaAdapter;
import com.example.resto.Adapter.PizzasListAdapter;
import com.example.resto.EntityDTO.DetalleBebidaDTO;
import com.example.resto.EntityDTO.DetalleBebidaRequestDTO;
import com.example.resto.EntityDTO.DetallePlatoRequestDTO;
import com.example.resto.EntityDTO.GeolocalizacionDTO;
import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.EntityDTO.OcupacionDTO;
import com.example.resto.EntityDTO.PlatoDTO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrdenActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView ListViewPizza;
    private ListView ListViewBebida;

    List<DetalleBebidaRequestDTO>bebidas;
    List<DetallePlatoRequestDTO> platos;

    OcupacionDTO ocupacion;

    private DetalleAdapter adapterPizza;
    private DetalleBebidaAdapter adapterBebida;

    private Button btnOrdenar;

    private Intent panelCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);


        this.ListViewPizza = findViewById(R.id.LPizza);
        this.ListViewBebida = findViewById(R.id.ListBebida);
        this.btnOrdenar = findViewById(R.id.btnOrdena);

        this.panelCarta =  new Intent(OrdenActivity.this,Carta.class);

       List<DetalleBebidaRequestDTO> detalleBebida = new ArrayList<>();
        SharedPreferences memoriaBebida = getSharedPreferences("DetalleBebida",MODE_PRIVATE);


        List<DetalleBebidaRequestDTO> detallePlato = new ArrayList<>();
        SharedPreferences memoriaPlato = getSharedPreferences("DetallePlato",MODE_PRIVATE);


        SharedPreferences memoriaOcupacion = getSharedPreferences("Ocupacion",MODE_PRIVATE);
        SharedPreferences.Editor editorOcupacion = memoriaOcupacion.edit();
        System.out.println("Ocupacion =======>>>>>><<<"+memoriaOcupacion.getString("Ocupacion","0").toString());



       // bebidas = gson.fromJson( memoriaBebida.getString("detalleBebidaMemory","0"), List.class);
        bebidas =  parseListBebidaFromResponse(memoriaBebida.getString("detalleBebidaMemory","0").toString());
        platos = parseListPlatoFromResponse(memoriaPlato.getString("detallePlatoMemory","0").toString());
        ocupacion = parseListOcupacionFromResponse(memoriaOcupacion.getString("Ocupacion","0").toString());

       if(!platos.isEmpty()) {
           this.adapterPizza = new DetalleAdapter(this,
                   this.platos.stream().map(DetallePlatoRequestDTO::getPlato).collect(Collectors.toList()),
                   this.platos.stream().map(DetallePlatoRequestDTO::getCantidad).collect(Collectors.toList()));
       }
       if(!bebidas.isEmpty()) {
           this.adapterBebida = new DetalleBebidaAdapter(this,
                   this.bebidas.stream().map(DetalleBebidaRequestDTO::getBebida).collect(Collectors.toList()),
                   this.bebidas.stream().map(DetalleBebidaRequestDTO::getCantidad).collect(Collectors.toList()));
       }


         this.btnOrdenar.setOnClickListener(this);
         this.ListViewPizza.setAdapter(this.adapterPizza);
         this.ListViewBebida.setAdapter(this.adapterBebida);

    }

    private List<DetallePlatoRequestDTO> parseListPlatoFromResponse(String response) {
        // Implementa el código para convertir la respuesta de la API en una lista de Strings
        // Dependiendo del formato de respuesta de tu API, puedes utilizar JSON, XML u otros formatos y bibliotecas de parsing correspondientes.
        // En este ejemplo, asumimos que la respuesta es una lista de Strings separada por comas
        Gson gson = new Gson();
        Type listType = new TypeToken<List<DetallePlatoRequestDTO>>(){}.getType();
        List<DetallePlatoRequestDTO> resultList = gson.fromJson(response, listType);

        return resultList;
    }

    private List<DetalleBebidaRequestDTO> parseListBebidaFromResponse(String response) {
        // Implementa el código para convertir la respuesta de la API en una lista de Strings
        // Dependiendo del formato de respuesta de tu API, puedes utilizar JSON, XML u otros formatos y bibliotecas de parsing correspondientes.
        // En este ejemplo, asumimos que la respuesta es una lista de Strings separada por comas
        Gson gson = new Gson();
        Type listType = new TypeToken<List<DetalleBebidaRequestDTO>>(){}.getType();
        List<DetalleBebidaRequestDTO> resultList = gson.fromJson(response, listType);

        return resultList;
    }
    private OcupacionDTO parseListOcupacionFromResponse(String response) {
        // Implementa el código para convertir la respuesta de la API en una lista de Strings
        // Dependiendo del formato de respuesta de tu API, puedes utilizar JSON, XML u otros formatos y bibliotecas de parsing correspondientes.
        // En este ejemplo, asumimos que la respuesta es una lista de Strings separada por comas
        Gson gson = new Gson();
        Type listType = new TypeToken<OcupacionDTO>(){}.getType();
        OcupacionDTO resultList = gson.fromJson(response, listType);

        return resultList;
    }

    @Override
    public void onClick(View view) {
        SharedPreferences memoriaPlato = getSharedPreferences("DetallePlato",MODE_PRIVATE);
        SharedPreferences.Editor editorPlato = memoriaPlato.edit();
        editorPlato.remove("detallePlatoMemory");
        editorPlato.apply();

        SharedPreferences memoriaBebida = getSharedPreferences("DetalleBebida",MODE_PRIVATE);
        SharedPreferences.Editor editorBebida = memoriaBebida.edit();
        editorBebida.remove("detalleBebidaMemory");
        editorBebida.apply();

        startActivity(this.panelCarta);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}