package com.example.pint_android_v3.notificacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar.classificar_viagem;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas_array_test;

public class notificacoes extends barra_lateral_pro {

    private int user_id;
    ListView lView;
    ListAdapter lAdapter;
    notificacoes_array_test lItems;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        lItems = new notificacoes_array_test();
        lView = (ListView)findViewById(R.id.listView_para_layout_notificacoes);
        lAdapter = new Custom_Adapter_Notificacoes(notificacoes.this,
                lItems.data, lItems.titulo, lItems.mensagem);

        lView.setAdapter(lAdapter);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }
        Bar_Settings(user_id);


    }

    public void MaisInfo(View view){
        Intent MaisInfo = new Intent(notificacoes.this, notificacoes_mais_info.class);
        MaisInfo.putExtra("user_id", user_id);
        startActivity(MaisInfo);
    }


}
