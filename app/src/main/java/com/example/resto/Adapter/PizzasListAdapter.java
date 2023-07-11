package com.example.resto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resto.EntityDTO.PlatoDTO;
import com.example.resto.PizzasActivity;
import com.example.resto.R;

import java.nio.InvalidMarkException;
import java.util.List;

public class PizzasListAdapter extends BaseAdapter {

    private Context context;

    private List<PlatoDTO> listaPizza;

    private TextView lblPizza;
    private TextView lblPrecio;
    private TextView lblCantidad;

    public PizzasListAdapter(Context context, List<PlatoDTO> listaPizza) {
        this.context = context;
        this.listaPizza = listaPizza;


    }

    @Override
    public int getCount() {
        return this.listaPizza.size();
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


        PizzasActivity.cantidades.add(0);
        PlatoDTO pizza = this.listaPizza.get(i);

        if(view == null)
            view  = LayoutInflater.from(context).inflate(R.layout.fragment_pizzas_list,null);

        ImageView imageViewPizza = view.findViewById(R.id.imageViewPizza);
        lblPizza = view.findViewById(R.id.lblPizza);
        lblPrecio = view.findViewById(R.id.lblPrecio);
        lblCantidad = view.findViewById(R.id.lblCantidad);

        lblPizza.setText(listaPizza.get(i).getNombre());
        lblPrecio.setText("$"+listaPizza.get(i).getPrecio());
        lblCantidad.setText("0");
        switch (i){
            case 0 :
                imageViewPizza.setImageResource(R.mipmap.pizza1);
                break;
            case 1:
                imageViewPizza.setImageResource(R.mipmap.pizza2);
                break;
            case 2:
                imageViewPizza.setImageResource(R.mipmap.pizza3);
                break;
            case 3:
                imageViewPizza.setImageResource(R.mipmap.pizza4);
                break;
            default:
                imageViewPizza.setImageResource(R.mipmap.oibre_image_foreground);
                break;
        }

        return view;
    }
}
