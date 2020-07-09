package com.example.pint_android_v3.viagens_efetuadas_condutor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.mapas.mais_info_mapa_cliente;
import com.example.pint_android_v3.mapas.mais_info_mapa_condutor;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;

import java.util.ArrayList;

public class CustomListAdapter_efetuadas_condutor extends BaseAdapter {

    Context context;

    private final int user_id;
    private final ArrayList<String> Local_Chegada;
    private final ArrayList<String> Local_Partida;
    private final ArrayList<String> Local_PartidaCoordenadas;
    private final ArrayList<String> Local_ChegadaCoordenadas;
    private final ArrayList<String> hora;
    private final ArrayList<String> data;
    private final ArrayList<Integer> idViagem;
    private final ArrayList<String> valorViagem;
    private final ArrayList<Integer> bagagemPedido;
    private final ArrayList<Integer> animalPedido;
    private final ArrayList<Integer> necessidadesEspeciaisPedido;

    public CustomListAdapter_efetuadas_condutor(Context cont, int user_id,
              ArrayList<String> data,  ArrayList<String> local_Chegada,ArrayList<String> Local_ChegadaCoordenadas
            , ArrayList<String> local_Partida, ArrayList<String> Local_PartidaCoordenadas, ArrayList<String> hora, ArrayList<Integer> idViagem,
              ArrayList<String> valorViagem, ArrayList<Integer> bagagemPedido,  ArrayList<Integer> animalPedido, ArrayList<Integer> necessidadesEspeciaisPedido) {

        context = cont;
        this.user_id = user_id;
        this.Local_Chegada = local_Chegada;
        this.Local_Partida = local_Partida;
        this.Local_ChegadaCoordenadas = Local_ChegadaCoordenadas;
        this.Local_PartidaCoordenadas = Local_PartidaCoordenadas;
        this.hora = hora;
        this.data = data;
        this.idViagem = idViagem;
        this.valorViagem = valorViagem;
        this.bagagemPedido = bagagemPedido;
        this.animalPedido = animalPedido;
        this.necessidadesEspeciaisPedido = necessidadesEspeciaisPedido;
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
            convertView = inflater.inflate(R.layout.viagens_efetuadas_adapter_condutor, parent, false);
            viewHolder.txt_Local_Chegada = convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Chegada_condutor);
            viewHolder.txt_Local_Partida = convertView.findViewById(R.id.viagens_efetuadas_adapter_Local_Partida_condutor);
            viewHolder.txt_hora = convertView.findViewById(R.id.viagens_efetuadas_adapter_hora_trip_condutor);
            viewHolder.txt_data = convertView.findViewById(R.id.viagens_efetuadas_adapter_data_trip_condutor);
            //btn
            viewHolder.btnMaisInfo = convertView.findViewById(R.id.viagens_efetuadas_adapter_mais_info_text_condutor);


            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (CustomListAdapter_efetuadas_condutor.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_data.setText(data.get(position));
        viewHolder.txt_Local_Chegada.setText(Local_Chegada.get(position));
        viewHolder.txt_Local_Partida.setText(Local_Partida.get(position));
        viewHolder.txt_hora.setText(hora.get(position));
        viewHolder.localChegadaCoordenadas = (Local_PartidaCoordenadas.get(position));
        viewHolder.localPartidaCoordenadas = (Local_ChegadaCoordenadas.get(position));
        viewHolder.idViagem = idViagem.get(position);
        viewHolder.valorViagem = valorViagem.get(position);
        viewHolder.bagagemPedido = bagagemPedido.get(position);
        viewHolder.animalPedido = animalPedido.get(position);
        viewHolder.necessidadesEspeciaisPedido = necessidadesEspeciaisPedido.get(position);

        viewHolder.btnMaisInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maisInfoBtnClick(viewHolder);
            }
        });


        return result;
    }

    private static class ViewHolder {
        TextView txt_data;
        TextView txt_Local_Chegada;
        TextView txt_Local_Partida;
        TextView txt_hora;
        TextView btnMaisInfo;
        //informação
        int idViagem;
        String valorViagem;
        String localChegadaCoordenadas;
        String localPartidaCoordenadas;
        int bagagemPedido;
        int animalPedido;
        int necessidadesEspeciaisPedido;
    }

    public void maisInfoBtnClick(ViewHolder viewHolder)
    {
        TextView localPartidatxt = viewHolder.txt_Local_Partida;
        TextView localChegadatxt = viewHolder.txt_Local_Chegada;

        String localPartidaCoord = viewHolder.localPartidaCoordenadas;
        String localChegadaCoord = viewHolder.localChegadaCoordenadas;

        TextView dataViagemtxt = viewHolder.txt_data;
        TextView horaViagemtxt = viewHolder.txt_hora;

        int idViagemCurrent = viewHolder.idViagem;
        String valorViagemCurrent = viewHolder.valorViagem;

        int bagagemPedidoCurrent = viewHolder.bagagemPedido;
        int animalPedidoCurrent = viewHolder.animalPedido;
        int necessidadesEspeciaisPedidoCurrent = viewHolder.necessidadesEspeciaisPedido;



        //Log.i("text",  localPartidatxt.getText().toString() + "|"+ localChegadatxt.getText().toString());
        Intent goMaisInfo = new Intent(context, mais_info_mapa_cliente.class);
        goMaisInfo.putExtra("user_id", user_id);
        goMaisInfo.putExtra("localPartida", localPartidatxt.getText());
        goMaisInfo.putExtra("localChegada", localChegadatxt.getText());
        goMaisInfo.putExtra("localPartidaCoord", localPartidaCoord);
        goMaisInfo.putExtra("localChegadaCoord", localChegadaCoord);
        goMaisInfo.putExtra("dataViagem", dataViagemtxt.getText());
        goMaisInfo.putExtra("horaViagem", horaViagemtxt.getText());
        goMaisInfo.putExtra("idViagem", idViagemCurrent);
        goMaisInfo.putExtra("valorViagem", valorViagemCurrent);
        goMaisInfo.putExtra("bagagemPedido", bagagemPedidoCurrent);
        goMaisInfo.putExtra("animalPedido", animalPedidoCurrent);
        goMaisInfo.putExtra("necessidadesEspeciaisPedido", necessidadesEspeciaisPedidoCurrent);
        goMaisInfo.putExtra("cidadao", false);
        context.startActivity(goMaisInfo);

    }
}
