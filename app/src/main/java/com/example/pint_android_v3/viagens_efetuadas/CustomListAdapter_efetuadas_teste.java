package com.example.pint_android_v3.viagens_efetuadas;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.classificar.classificar_condutor;
import com.example.pint_android_v3.classificar.classificar_viagem;
import com.example.pint_android_v3.mapas.mais_info_mapa_cliente;

import java.util.ArrayList;

public class CustomListAdapter_efetuadas_teste extends BaseAdapter {

    Context context;
    private final ArrayList<String> data;
    private final int user_id;
    private final ArrayList<String> Local_Chegada;
    private final ArrayList<String> Local_Partida;
    private final ArrayList<String> Local_PartidaCoordenadas;
    private final ArrayList<String> Local_ChegadaCoordenadas;
    private final ArrayList<String> hora;

    public CustomListAdapter_efetuadas_teste(Context cont, int user_id, ArrayList<String> data,  ArrayList<String> local_Chegada,ArrayList<String> Local_ChegadaCoordenadas, ArrayList<String> local_Partida, ArrayList<String> Local_PartidaCoordenadas, ArrayList<String> hora) {

        context = cont;
        this.user_id = user_id;
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
            viewHolder.imageViewViagens_efetuadas_adapter_mais_info = convertView.findViewById(R.id.viagens_efetuadas_adapter_mais_info);
            viewHolder.textViewclassificar_condutor_textview = convertView.findViewById(R.id.classificar_condutor_textview);
            viewHolder.viagens_efetuadas_adapter_class_viagem_txt = convertView.findViewById(R.id.viagens_efetuadas_adapter_class_viagem_txt);

            convertView.setTag(viewHolder);
            result = convertView;
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

        viewHolder.imageViewViagens_efetuadas_adapter_mais_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maisInfoBtnClick(viewHolder);
            }
        });
        viewHolder.textViewclassificar_condutor_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classificarCondutorBtnClick();
            }
        });
        viewHolder.viagens_efetuadas_adapter_class_viagem_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classificarViagemBtnClick();
            }
        });

        return result;
    }

    private static class ViewHolder {
        TextView txt_Local_Chegada;
        TextView txt_Local_Partida;
        TextView txt_data;
        TextView txt_hora;
        TextView txt_Local_ChegadaCoordenadas;
        TextView txt_Local_PartidaCoordenadas;
        //btns
        ImageView imageViewViagens_efetuadas_adapter_mais_info;
        TextView textViewclassificar_condutor_textview;
        TextView viagens_efetuadas_adapter_class_viagem_txt;
    }

    private void maisInfoBtnClick(ViewHolder viewHolder){
        TextView localPartidatxt = viewHolder.txt_Local_Partida;
        TextView localChegadatxt = viewHolder.txt_Local_Chegada;

        TextView localPartidaCoord = viewHolder.txt_Local_PartidaCoordenadas;
        TextView localChegadaCoord = viewHolder.txt_Local_ChegadaCoordenadas;

        TextView dataViagemtxt = viewHolder.txt_data;
        TextView horaViagemtxt = viewHolder.txt_hora;



        //Log.i("text",  localPartidatxt.getText().toString() + "|"+ localChegadatxt.getText().toString());
        Intent goMaisInfo = new Intent(context, mais_info_mapa_cliente.class);
        goMaisInfo.putExtra("user_id", user_id);
        goMaisInfo.putExtra("localPartida", localPartidatxt.getText());
        goMaisInfo.putExtra("localChegada", localChegadatxt.getText());
        goMaisInfo.putExtra("localPartidaCoord", localPartidaCoord.getText());
        goMaisInfo.putExtra("localChegadaCoord", localChegadaCoord.getText());
        goMaisInfo.putExtra("dataViagem", dataViagemtxt.getText());
        goMaisInfo.putExtra("horaViagem", horaViagemtxt.getText());
        context.startActivity(goMaisInfo);
    }
    private void classificarCondutorBtnClick(){
        Intent GO = new Intent(context, classificar_condutor.class);
        GO.putExtra("user_id", user_id);
        context.startActivity(GO);
    }
    private void classificarViagemBtnClick(){
        Intent Classificar_Viagem = new Intent(context, classificar_viagem.class);
        Classificar_Viagem.putExtra("user_id", user_id);
        context.startActivity(Classificar_Viagem);
    }
}


