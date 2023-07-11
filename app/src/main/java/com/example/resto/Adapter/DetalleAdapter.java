package com.example.resto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resto.Bebida;
import com.example.resto.EntityDTO.BebidaDTO;
import com.example.resto.EntityDTO.PlatoDTO;
import com.example.resto.R;

import java.util.List;

public class DetalleAdapter extends BaseAdapter {

    private Context context;


    private List<PlatoDTO> listaPlato;
    private  List<Integer> listaCantidad;

    private TextView lblNombre;
    private TextView lblPrecio;
    private TextView lblCantidad;

    public DetalleAdapter(Context context, List<PlatoDTO> listaBebida, List<Integer> listaCantidad) {
        this.context = context;
        this.listaPlato = listaBebida;
        this.listaCantidad = listaCantidad;
    }


    @Override
    public int getCount() {
        return this.listaPlato.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.fragment_pizzas_list,null);

        ImageView imageViewPizza = view.findViewById(R.id.imageViewPizza);
        this.lblCantidad = view.findViewById(R.id.lblCantidad);
        this.lblNombre = view.findViewById(R.id.lblPizza);
        this.lblPrecio = view.findViewById(R.id.lblPrecio);

        System.out.println(listaCantidad.get(i).toString());

       this.lblNombre.setText(listaPlato.get(i).getNombre());
        this.lblPrecio.setText("$"+listaPlato.get(i).getPrecio().toString());
        this.lblCantidad.setText(listaCantidad.get(i).toString());

        imageViewPizza.setImageResource(R.mipmap.oibre_image_foreground);


        return view;
    }
}
