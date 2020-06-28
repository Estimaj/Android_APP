package com.example.pint_android_v3.viagens_efetuadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.classificar_condutor;
import com.example.pint_android_v3.menus.menu_municipe;

public class viagens_efetuadas extends AppCompatActivity {
    ListView lView;
    ListAdapter lAdapter;
    viagens_efetuadas_array_test lItems;
    ImageView classificar_condutor_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_efetuadas);
        Button X = (Button) findViewById(R.id.button_back_arrow_black_viagens_efetuadas);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Back();
            }
        });

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


    }

    public void Go_Back()
    {
        Intent GO = new Intent(viagens_efetuadas.this, menu_municipe.class);
        startActivity(GO);

    }

    public void Classificar_Condutor()
    {
        Intent GO = new Intent(viagens_efetuadas.this, classificar_condutor.class);
        startActivity(GO);


    }


}