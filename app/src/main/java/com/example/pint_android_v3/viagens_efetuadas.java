package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

public class viagens_efetuadas extends AppCompatActivity {
    ListView lView;
    ListAdapter lAdapter;
    viagens_efetuadas_array_test lItems;


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


    }

    public void Go_Back()
    {
        Intent GO = new Intent(viagens_efetuadas.this, menu_municipe.class);
        startActivity(GO);

    }
}
