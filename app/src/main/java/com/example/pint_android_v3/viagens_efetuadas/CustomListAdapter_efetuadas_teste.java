package com.example.pint_android_v3.viagens_efetuadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pint_android_v3.R;

import java.util.ArrayList;

public class CustomListAdapter_efetuadas_teste extends BaseAdapter {

    Context context;
    private final ArrayList<String> data;

    private final ArrayList<String> Local_Chegada;
    private final ArrayList<String> Local_Partida;
    private final ArrayList<String> Local_PartidaCoordenadas;
    private final ArrayList<String> Local_ChegadaCoordenadas;
    private final ArrayList<String> hora;

    public CustomListAdapter_efetuadas_teste(Context cont, ArrayList<String> data,  ArrayList<String> local_Chegada,ArrayList<String> Local_ChegadaCoordenadas, ArrayList<String> local_Partida, ArrayList<String> Local_PartidaCoordenadas, ArrayList<String> hora) {

        context = cont;
        this.Local_Chegada = local_Chegada;
        this.Local_Partida = local_Partida;
        this.Local_ChegadaCoordenadas = Local_ChegadaCoordenadas;
        this.Local_PartidaCoordenadas = Local_PartidaCoordenadas;
        this.hora = hora;
        this.data = data;
    }

    @Override
    public int getCount() {
        if(Local_Chegada == null) return 0;
        return Local_Chegada.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if(convertView == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.viagens_efetuadas_adapter, parent, false);
            viewHolder.txt_data = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_data_trip);
            viewHolder.txt_Local_Chegada = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Chegada);
            viewHolder.txt_Local_Partida = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Partida);
            viewHolder.txt_hora = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_hora_trip);
            viewHolder.txt_Local_ChegadaCoordenadas = convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Chegada_coordenadas);
            viewHolder.txt_Local_PartidaCoordenadas = convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Partida_coordenadas);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_data.setText(data.get(position));
        viewHolder.txt_Local_Chegada.setText(Local_Chegada.get(position));
        viewHolder.txt_Local_Partida.setText(Local_Partida.get(position));
        viewHolder.txt_hora.setText(hora.get(position));
        viewHolder.txt_Local_PartidaCoordenadas.setText(Local_PartidaCoordenadas.get(position));
        viewHolder.txt_Local_ChegadaCoordenadas.setText(Local_ChegadaCoordenadas.get(position));


        return convertView;
    }

    private static class ViewHolder {
        TextView txt_Local_Chegada;
        TextView txt_Local_Partida;
        TextView txt_data;
        TextView txt_hora;
        TextView txt_Local_ChegadaCoordenadas;
        TextView txt_Local_PartidaCoordenadas;
    }
}


