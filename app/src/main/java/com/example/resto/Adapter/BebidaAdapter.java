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
import com.example.resto.R;

import java.util.List;

public class BebidaAdapter extends BaseAdapter {

    private Context context;
    private List<BebidaDTO> listaBebida;
    private TextView lblNombreBebida;
    private TextView lblPrecioBebida;
    private TextView lblCantidad;


    public BebidaAdapter(Context context, List<BebidaDTO> bebida) {
        this.context = context;
        this.listaBebida = bebida;


    }

    @Override
    public int getCount() {
        return this.listaBebida.size();
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

        Bebida.cantidades.add(0);
        BebidaDTO bebida = this.listaBebida.get(i);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.fragment_bebida,null);

        ImageView imageViewPizza = view.findViewById(R.id.imageViewBebida);
        this.lblCantidad = view.findViewById(R.id.lblCantidad);
        this.lblNombreBebida = view.findViewById(R.id.lblNombre);
        this.lblPrecioBebida = view.findViewById(R.id.lblPrecioBebida);


        this.lblNombreBebida.setText(listaBebida.get(i).getNombre());
        this.lblPrecioBebida.setText("$"+listaBebida.get(i).getPrecio());
        this.lblCantidad.setText("0");

        switch (i){
            case 0: imageViewPizza.setImageResource(R.mipmap.bebida1_round);
                break;
            case 1: imageViewPizza.setImageResource(R.mipmap.bebida2_round);
                break;
            case 2: imageViewPizza.setImageResource(R.mipmap.bebida3_round);
                break;
            default:
                imageViewPizza.setImageResource(R.mipmap.oibre_image_foreground);
                break;
        }


        return view;
    }
}
