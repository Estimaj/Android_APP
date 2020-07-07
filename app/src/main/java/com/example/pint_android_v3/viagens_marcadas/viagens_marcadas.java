package com.example.pint_android_v3.viagens_marcadas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pint_android_v3.Cancelar_Viagem;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar.classificar_viagem;
import com.example.pint_android_v3.mapas.mais_info_mapa_cliente;
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
                lItems.data,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);


        Intent I = getIntent();
        Bundle b = I.getExtras();
        if(b!=null){
            id_user = (int) b.get("user_id");

        }
        Bar_Settings(id_user);
    }

    public void Cancelar_Viagem(View view){//falta saber como enviar a viagem certa
        Intent Cancelar_Viagem = new Intent(viagens_marcadas.this, Cancelar_Viagem.class);
        Cancelar_Viagem.putExtra("user_id", id_user);
        startActivity(Cancelar_Viagem);
    }

    public void maisInfo(View view){ //falta saber como enviar a viagem certa
        Log.i("hm", ""+ view.findViewById(R.id.viagens_marcadas_adapter_Local_Partida));
        TextView localPartidatxt = this.findViewById(R.id.viagens_marcadas_adapter_Local_Partida);
        TextView localChegadatxt = this.findViewById(R.id.viagens_marcadas_adapter_Local_Chegada);

        TextView dataViagemtxt = this.findViewById(R.id.viagens_marcadas_adapter_data_trip);
        TextView horaViagemtxt = this.findViewById(R.id.viagens_marcadas_adapter_hora_trip);

        //TextView distanciaViagemtxt = this.findViewById(R.id.viagens_marcadas_adapter_Distancia);

        //Log.i("text",  localPartidatxt.getText().toString() + "|"+ localChegadatxt.getText().toString());
        Intent goMaisInfo = new Intent(viagens_marcadas.this, mais_info_mapa_cliente.class);
        goMaisInfo.putExtra("user_id", id_user);
        goMaisInfo.putExtra("localPartida", localPartidatxt.getText());
        goMaisInfo.putExtra("localChegada", localChegadatxt.getText());
        goMaisInfo.putExtra("dataViagem", dataViagemtxt.getText());
        goMaisInfo.putExtra("horaViagem", horaViagemtxt.getText());
       // goMaisInfo.putExtra("distanciaViagem", distanciaViagemtxt.getText());
        startActivity(goMaisInfo);
    }

}
