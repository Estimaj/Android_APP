package com.example.pint_android_v3.viagens_efetuadas_condutor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas_array_test;

public class viagens_efetuadas_condutor extends barra_lateral_pro {

    ListView lView;
    ListAdapter lAdapter;
    viagens_efetuadas_array_test lItems;


    private int user_id;

    private String[] Local_Partida;
    private String[] Local_Chegada;
    private String[] distancia;
    private String[] tempo;
    private String[] data;
    private String[] hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_efetuadas);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }

        //getInformationFromdb(user_id);

        /*lItems = new viagens_efetuadas_array_test(Local_Partida, Local_Chegada, distancia, tempo, data, hora);
        lView = findViewById(R.id.viagens_efetuadas_listview);
        lAdapter = new CustomListAdapter_efetuadas_teste(viagens_efetuadas.this,
                lItems.data, lItems.tempo, lItems.distancia,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);*/


        /*
        lView.setOnItemClickListener(new ListView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView sacarInfoDaViagem = view.findViewById(R.id.viagens_efetuadas_adapter_Local_Partida);
                sacarInfoDaViagem.setText("ola");
                //lAdapter.getItem(position);
                //Classificar_Condutor();
            }
        });
        */

        Bar_Settings(user_id);
    }


}
