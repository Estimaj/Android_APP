package com.example.pint_android_v3.mapas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.quem_vai_consigo.quem_vai_consigo;

public class mais_info_mapa_cliente extends barra_lateral_pro {


    private int user_id;
    private int idViagem;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mais_info_mapa_cliente);



        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            idViagem = (int) b.get("idViagem");
            try {
                Button btnPassageiros = findViewById(R.id.ver_passageiros_mais_info_cliente);
                btnPassageiros.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Click_Quem_Vai();
                    }
                });

                colocarValoresMaisInfo(b);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        Bar_Settings(user_id);
    }

    private void colocarValoresMaisInfo(Bundle b) {

        String localPartida = (String) b.get("localPartida");
        String localChegada = (String) b.get("localChegada");


        TextView valorViagemtxt = findViewById(R.id.Dinheiro_pagar_mais_info_cliente);
        valorViagemtxt.setText("" + (String) b.get("valorViagem"));

        TextView localPartidatxtview = findViewById(R.id.Local_Partida_mais_info_cliente);
        localPartidatxtview.setText(localPartida);
        TextView localChegadatxtview = findViewById(R.id.Local_Chegada_mais_info_cliente);
        localChegadatxtview.setText(localChegada);

        ImageView certoGone;
        if((int) b.get("bagagemPedido") == 0){
            certoGone = findViewById(R.id.mala_icon_mais_info_cliente);
            certoGone.setVisibility(View.GONE);
        }
        if((int) b.get("animalPedido") == 0){
            certoGone = findViewById(R.id.canideo_icon_mais_info_cliente);
            certoGone.setVisibility(View.GONE);
        }
        if((int) b.get("necessidadesEspeciaisPedido") == 0){
            certoGone = findViewById(R.id.wheel_icon_mais_info_cliente);
            certoGone.setVisibility(View.GONE);
        }


        TextView localPartidaCoord = findViewById(R.id.viagens_efetuadas_adapter_Local_Partida_coordenadas);
        localPartidaCoord.setText("" + (String) b.get("localPartidaCoord"));
        TextView localChegadaCoord = findViewById(R.id.viagens_efetuadas_adapter_Local_Chegada_coordenadas);
        localChegadaCoord.setText(""+ (String) b.get("localChegadaCoord"));
    }

    public void Click_Quem_Vai(){
        Intent Consigo = new Intent(mais_info_mapa_cliente.this, quem_vai_consigo.class);
        Consigo.putExtra("user_id", user_id);
        Consigo.putExtra("idViagem", idViagem);
        startActivity(Consigo);
    }





}
