package com.example.pint_android_v3.viagens_efetuadas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar_condutor;

public class viagens_efetuadas extends barra_lateral_pro {
    ListView lView;
    ListAdapter lAdapter;
    viagens_efetuadas_array_test lItems;
    ImageView classificar_condutor_btn;

    private int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_efetuadas);


        lItems = new viagens_efetuadas_array_test();
        lView = (ListView)findViewById(R.id.viagens_efetuadas_listview);
        lAdapter = new CustomListAdapter_efetuadas_teste(viagens_efetuadas.this,
                lItems.data, lItems.tempo, lItems.distancia,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);
        lView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Classificar_Condutor();
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }

        Bar_Settings(user_id);
    }



    public void Classificar_Condutor()
    {
        Intent GO = new Intent(viagens_efetuadas.this, classificar_condutor.class);
        startActivity(GO);


    }


}
