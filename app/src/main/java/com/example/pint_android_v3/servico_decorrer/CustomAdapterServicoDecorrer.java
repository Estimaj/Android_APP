package com.example.pint_android_v3.servico_decorrer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.viagens_efetuadas_condutor.CustomListAdapter_efetuadas_condutor;

public class CustomAdapterServicoDecorrer extends BaseAdapter {

    Context context;
    private final String[] data;
    //private final String[] tempo;
    //private final String[] distancia;
    private final String[] Local_Chegada;
    private final String[] Local_Partida;
    private final String[] hora;

    public CustomAdapterServicoDecorrer(Context cont, String[] data, String[] local_Chegada, String[] local_Partida, String[] hora) {

        context = cont;
        this.data = data;
        //this.tempo = tempo;
        // this.distancia = distancia;
        Local_Chegada = local_Chegada;
        Local_Partida = local_Partida;
        this.hora = hora;
    }

    @Override
    public int getCount() {

        if(Local_Chegada == null)
            return 0;
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

        CustomAdapterServicoDecorrer.ViewHolder viewHolder;
        final View result;

        if(convertView == null)
        {
            viewHolder = new CustomAdapterServicoDecorrer.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.servico_a_decorrer_adapter, parent, false);
            viewHolder.txt_data = (TextView)convertView.findViewById(R.id.servico_a_decorrer_adapter_data_trip);
            //viewHolder.txt_tempo = (TextView)convertView.findViewById(R.id.servico_a_decorrer_adapter_Tempo);
            //viewHolder.txt_distancia = (TextView)convertView.findViewById(R.id.servico_a_decorrer_adapter_Distancia);
            viewHolder.txt_Local_Chegada = (TextView)convertView.findViewById(R.id.servico_a_decorrer_adapter_Local_Chegada);
            viewHolder.txt_Local_Partida = (TextView)convertView.findViewById(R.id.servico_a_decorrer_adapter_Local_Partida);
            viewHolder.txt_hora = (TextView)convertView.findViewById(R.id.servico_a_decorrer_adapter_hora_trip);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (CustomAdapterServicoDecorrer.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_data.setText(data[position]);
        //viewHolder.txt_distancia.setText(distancia[position]);
        //viewHolder.txt_tempo.setText(tempo[position]);
        viewHolder.txt_Local_Chegada.setText(Local_Chegada[position]);
        viewHolder.txt_Local_Partida.setText(Local_Partida[position]);
        viewHolder.txt_hora.setText(hora[position]);



        return convertView;
    }

    private static class ViewHolder {
        TextView txt_data;
        //TextView txt_distancia;
        //TextView txt_tempo;
        TextView txt_Local_Chegada;
        TextView txt_Local_Partida;
        TextView txt_hora;

    }}
