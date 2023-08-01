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

public class DetalleBebidaAdapter extends BaseAdapter {
    private Context context;
    private List<BebidaDTO> listaBebida;
    private  List<Integer> listaCantidad;

    private TextView lblNombre;
    private TextView lblPrecio;
    private TextView lblCantidad;

    public DetalleBebidaAdapter(Context context, List<BebidaDTO> listaBebida, List<Integer> listaCantidad) {
        this.context = context;
        this.listaBebida = listaBebida;
        this.listaCantidad = listaCantidad;
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



        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.fragment_bebida,null);

        ImageView imageViewPizza = view.findViewById(R.id.imageViewBebida);
        this.lblCantidad = view.findViewById(R.id.lblCantidad);
        this.lblNombre = view.findViewById(R.id.lblNombre);
        this.lblPrecio = view.findViewById(R.id.lblPrecioBebida);

        this.lblNombre.setText(listaBebida.get(i).getNombre());
        this.lblPrecio.setText("$"+listaBebida.get(i).getPrecio().toString());
        this.lblCantidad.setText(listaCantidad.get(i).toString());
        if(listaBebida.get(i).getId() == 1)
            imageViewPizza.setImageResource(R.mipmap.bebida1_round);
        else if (listaBebida.get(i).getId() == 2) {
            imageViewPizza.setImageResource(R.mipmap.bebida2_round);
        }else if (listaBebida.get(i).getId() ==3) {
            imageViewPizza.setImageResource(R.mipmap.bebida3_round);
        }else
            imageViewPizza.setImageResource(R.mipmap.oibre_image_foreground);


        return view;
    }
}
