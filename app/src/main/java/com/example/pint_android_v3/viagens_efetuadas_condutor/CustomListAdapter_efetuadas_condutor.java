package com.example.pint_android_v3.viagens_efetuadas_condutor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pint_android_v3.R;

public class CustomListAdapter_efetuadas_condutor extends BaseAdapter {

    Context context;
    private final String[] data;
    private final String[] tempo;
    private final String[] distancia;
    private final String[] Local_Chegada;
    private final String[] Local_Partida;
    private final String[] hora;

    public CustomListAdapter_efetuadas_condutor(Context cont, String[] data, String[] tempo, String[] distancia, String[] local_Chegada, String[] local_Partida, String[] hora) {

        context = cont;
        this.data = data;
        this.tempo = tempo;
        this.distancia = distancia;
        Local_Chegada = local_Chegada;
        Local_Partida = local_Partida;
        this.hora = hora;
    }

    @Override
    public int getCount() {
        return Local_Chegada.length;
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

        CustomListAdapter_efetuadas_condutor.ViewHolder viewHolder;
        final View result;

        if(convertView == null)
        {
            viewHolder = new CustomListAdapter_efetuadas_condutor.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.viagens_efetuadas_adapter_condutor, parent, false);
            viewHolder.txt_data = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_data_trip_condutor);
            viewHolder.txt_tempo = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_Tempo_condutor);
            viewHolder.txt_distancia = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_Distancia_condutor);
            viewHolder.txt_Local_Chegada = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Chegada_condutor);
            viewHolder.txt_Local_Partida = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Partida_condutor);
            viewHolder.txt_hora = (TextView)convertView.findViewById(R.id.viagens_efetuadas_adapter_hora_trip_condutor);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (CustomListAdapter_efetuadas_condutor.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_data.setText(data[position]);
        viewHolder.txt_distancia.setText(distancia[position]);
        viewHolder.txt_tempo.setText(tempo[position]);
        viewHolder.txt_Local_Chegada.setText(Local_Chegada[position]);
        viewHolder.txt_Local_Partida.setText(Local_Partida[position]);
        viewHolder.txt_hora.setText(hora[position]);



        return convertView;
    }

    private static class ViewHolder {
        TextView txt_data;
        TextView txt_distancia;
        TextView txt_tempo;
        TextView txt_Local_Chegada;
        TextView txt_Local_Partida;
        TextView txt_hora;

    }
}
