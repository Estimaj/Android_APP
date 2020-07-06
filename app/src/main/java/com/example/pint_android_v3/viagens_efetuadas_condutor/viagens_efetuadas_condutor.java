package com.example.pint_android_v3.viagens_efetuadas_condutor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar.classificar_condutor;
import com.example.pint_android_v3.mapas.mais_info_mapa_condutor;
import com.example.pint_android_v3.notificacoes.notificacoes_mais_info;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas_array_test;

public class viagens_efetuadas_condutor extends barra_lateral_condutor {

    private int user_id;
    ListView lView;
    ListAdapter lAdapter;
    viagens_efetuadas_condutor_array lItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_efetuadas_condutor);

        lItems = new viagens_efetuadas_condutor_array();
        lView = (ListView)findViewById(R.id.viagens_efetuadas_listview_condutor);
        lAdapter = new CustomListAdapter_efetuadas_condutor(viagens_efetuadas_condutor.this,
                lItems.data, lItems.tempo, lItems.distancia,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }
        Bar_Settings(user_id);


    }

    public void Mais_Info_Condutor_efetuadas(View view)
    {
        Intent GO = new Intent(viagens_efetuadas_condutor.this, mais_info_mapa_condutor.class);
        GO.putExtra("user_id", user_id);
        startActivity(GO);


    }


}
