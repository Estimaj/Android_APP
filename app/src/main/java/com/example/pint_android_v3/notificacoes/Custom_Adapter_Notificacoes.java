package com.example.pint_android_v3.notificacoes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;

public class Custom_Adapter_Notificacoes extends BaseAdapter {

    Context context;
    private final String[] data;
    private final String[] titulo;
    private final String[] mensagem;

    public Custom_Adapter_Notificacoes(Context cont, String[] data, String[] titulo, String[] mensagem) {

        context = cont;
        this.data = data;
        this.titulo = titulo;
        this.mensagem = mensagem;
    }

    @Override
    public int getCount() {
        return data.length;
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
            viewHolder.txt_titulo = (TextView)convertView.findViewById(R.id.nome_utilizador_notificacoes_adapter);
            viewHolder.txt_mensagem = (TextView)convertView.findViewById(R.id.mensagem_notificacao_adapter);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (Custom_Adapter_Notificacoes.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.txt_data.setText(data[position]);
        viewHolder.txt_titulo.setText(titulo[position]);
        viewHolder.txt_mensagem.setText(mensagem[position]);



        return convertView;
    }

    private static class ViewHolder {
        TextView txt_data;
        TextView txt_titulo;
        TextView txt_mensagem;

    }
}
