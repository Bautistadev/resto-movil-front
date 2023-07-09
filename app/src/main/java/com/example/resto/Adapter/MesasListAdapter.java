package com.example.resto.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.resto.EntityDTO.MesaDTO;
import com.example.resto.R;

import java.util.List;

public class MesasListAdapter extends BaseAdapter {

    private Context context;
    private List<MesaDTO> listaObjeto;

    public MesasListAdapter(Context context, List<MesaDTO> lst) {
        this.context = context;
        this.listaObjeto = lst;
    }

    @Override
    public int getCount() {
        return listaObjeto.size();
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

        ImageView ImageViewMesa;
        TextView lblNroMesa;
        TextView lblEstado;

         MesaDTO mesa = this.listaObjeto.get(i);

        if(view == null)
            view = LayoutInflater.from(context).inflate(R.layout.mesas_list,null);

        ImageViewMesa = view.findViewById(R.id.imageViewEstado);
        lblNroMesa = view.findViewById(R.id.lblNroMesa);
        lblEstado = view.findViewById(R.id.lblEstado);

        //cambia el color dependiendo del estado de la mesa
        if(mesa.isEstado())
            ImageViewMesa.setImageResource(R.mipmap.oibre_image_foreground);
        else
            ImageViewMesa.setImageResource(R.mipmap.ocupado_image_foreground);

        lblNroMesa.setText("Nro de mesa: "+ mesa.getId().toString());
        lblEstado.setText("Estado:");
        return view;
    }
}
