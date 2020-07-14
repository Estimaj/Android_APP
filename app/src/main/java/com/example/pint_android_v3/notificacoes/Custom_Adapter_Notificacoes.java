package com.example.pint_android_v3.notificacoes;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pint_android_v3.R;

import java.util.ArrayList;

public class Custom_Adapter_Notificacoes extends BaseAdapter {

    Context context;
    private final ArrayList<String> data;
    private final ArrayList<String> nome;
    private final ArrayList<Integer> tipo_notif;
    private final ArrayList<Integer> id_viagem;
    private final int utilizadorSwitch;
    private final int user_id;

    public Custom_Adapter_Notificacoes(Context cont, int user_id, ArrayList<String> data, ArrayList<String> nome, ArrayList<Integer> tipo_notif, ArrayList<Integer> id_viagem, int utilizador) {

        context = cont;
        this.user_id = user_id;
        this.data = data;
        this.nome = nome;
        this.tipo_notif = tipo_notif;
        this.id_viagem = id_viagem;
        this.utilizadorSwitch = utilizador;
    }

    @Override
    public int getCount() {
        if(data == null) return 0;
        return data.size();
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

        Custom_Adapter_Notificacoes.ViewHolder viewHolder;
        final View result;

        if(convertView == null)
        {
            viewHolder = new Custom_Adapter_Notificacoes.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.notificacoes_adapter, parent, false);
            viewHolder.txt_data = (TextView)convertView.findViewById(R.id.data_notificacoes_adapter);
            viewHolder.txt_nome = (TextView)convertView.findViewById(R.id.nome_utilizador_notificacoes_adapter);
            viewHolder.txt_mensagem = (TextView)convertView.findViewById(R.id.mensagem_notificacao_adapter);
            viewHolder.btnMaisInfo = convertView.findViewById(R.id.btn_mais_info_not_adapter);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (Custom_Adapter_Notificacoes.ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.id_viagem = id_viagem.get(position);
        viewHolder.txt_data.setText(data.get(position));
        viewHolder.txt_nome.setText(nome.get(position));
        viewHolder.txt_mensagem.setText(defineNotificationType(tipo_notif.get(position)));
        if(utilizadorSwitch == 0)//se vier do cliente
        {
            viewHolder.btnMaisInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaisInfoCliente(viewHolder);
                }
            });
        }
        else
        {
            viewHolder.btnMaisInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MaisInfoCondutor(viewHolder);
                }
            });

        }

        return result;
    }

    public void MaisInfoCliente(ViewHolder viewHolder){
        Intent MaisInfo = new Intent(context, notificacoes_mais_info.class);
        MaisInfo.putExtra("id_viagem", viewHolder.id_viagem);
        MaisInfo.putExtra("user_id", user_id);
        context.startActivity(MaisInfo);
    }

    public void MaisInfoCondutor(ViewHolder viewHolder){
        Intent MaisInfo = new Intent(context, notificacoes_mais_info_condutor.class);
        MaisInfo.putExtra("id_viagem", viewHolder.id_viagem);
        MaisInfo.putExtra("user_id", user_id);
        context.startActivity(MaisInfo);
    }

    private static class ViewHolder {
        TextView txt_data;
        TextView txt_nome;
        TextView txt_mensagem;
        int id_viagem;
        ImageView btnMaisInfo;

    }

    public String defineNotificationType(int num)
    {
        //desta forma podemos adicionar um novo tipo de notificaçao em caso
        String message = "Error";
        switch(num)
        {
            case 0:
                message = "Um dos passageiros cancelou a sua ida na viagem marcada para o dia: ";
                break; //cancelar viagem

            case 1:
                message = "A viagem que pediu foi marcada com sucesso para o dia: ";
                break; //viagem marcada com sucesso
        }

        return message;
    }
}
