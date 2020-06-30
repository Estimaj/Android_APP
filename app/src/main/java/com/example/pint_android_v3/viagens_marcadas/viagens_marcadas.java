package com.example.pint_android_v3.viagens_marcadas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas_array_test;

public class viagens_marcadas extends barra_lateral_pro {

    ListView lView;
    ListAdapter lAdapter;
    viagens_marcadas_array_test lItems;
    private int id_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_marcadas);

        lItems = new viagens_marcadas_array_test();
        lView = (ListView)findViewById(R.id.viagens_marcadas_listview);
        lAdapter = new CustomListAdapter_marcadas_teste(viagens_marcadas.this,
                lItems.data, lItems.tempo, lItems.distancia,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);


        Intent I = getIntent();
        Bundle b = I.getExtras();
        if(b!=null){
            id_user = (int) b.get("user_id");

        }
        Bar_Settings(id_user);
    }
}
