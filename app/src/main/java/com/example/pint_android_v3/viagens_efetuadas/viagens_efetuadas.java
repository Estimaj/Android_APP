package com.example.pint_android_v3.viagens_efetuadas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar.classificar_condutor;
import com.example.pint_android_v3.classificar.classificar_viagem;
import com.example.pint_android_v3.mapas.mais_info_mapa_cliente;

public class viagens_efetuadas extends barra_lateral_pro {
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

        getInformationFromdb(user_id);

        lItems = new viagens_efetuadas_array_test(Local_Partida, Local_Chegada, distancia, tempo, data, hora);
        lView = findViewById(R.id.viagens_efetuadas_listview);
        lAdapter = new CustomListAdapter_efetuadas_teste(viagens_efetuadas.this,
                lItems.data, lItems.tempo, lItems.distancia,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);


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

    private void getInformationFromdb(int id) {
        if(id == 0) return;

    }


    public void Classificar_Condutor(View view) //o textview tem um onclick
    {
        //falta saber como enviar a viagem certa
        Intent GO = new Intent(viagens_efetuadas.this, classificar_condutor.class);
        GO.putExtra("user_id", user_id);
        startActivity(GO);
    }


    public void Classificar_Viagem(View view){//falta saber como enviar a viagem certa
        Intent Classificar_Viagem = new Intent(viagens_efetuadas.this, classificar_viagem.class);
        Classificar_Viagem.putExtra("user_id", user_id);
        startActivity(Classificar_Viagem);
    }



    public void maisInfo(View view){ //falta saber como enviar a viagem certa
        Intent goMaisInfo = new Intent(viagens_efetuadas.this, mais_info_mapa_cliente.class);
        goMaisInfo.putExtra("user_id", user_id);
        startActivity(goMaisInfo);
    }


}
