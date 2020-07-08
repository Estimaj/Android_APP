package com.example.pint_android_v3.viagens_marcadas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.Model_Viagens_Efetuadas;

import com.example.pint_android_v3.Cancelar_Viagem;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.dataViagem;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.mapas.mais_info_mapa_cliente;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas_array_test;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viagens_marcadas extends barra_lateral_pro {

    ListView lView;
    ListAdapter lAdapter;
    viagens_marcadas_array_test lItems;
    private int user_id;

    private String BASE_URL ="http://10.0.2.2:3000";
    private ArrayList<dataViagem> informacaoViagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_marcadas);



        Intent I = getIntent();
        Bundle b = I.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");

        }
        user_id = 7;
        getInformationFromdb(user_id);
        Bar_Settings(user_id);
    }

    private void getInformationFromdb(int id) {
        if(id == 0) return;

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        //Log.i("O id do user:", ""+ id);
        Call<Model_Viagens_Efetuadas> call = baseDadosInterface.executeViagemEfetuada(id);

        call.enqueue(new Callback<Model_Viagens_Efetuadas>() {
            @Override
            public void onResponse(Call<Model_Viagens_Efetuadas> call, Response<Model_Viagens_Efetuadas> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "L99 viagens efetuadas");
                }
                if (response.code() == 200){
                    if (response.body().getDataViagem().size() != 0) {
                        //Log.i("body", "" + response.body().getDataViagem().get(0).toString());
                        informacaoViagem = response.body().getDataViagem();
                        createAdapters();
                    }else
                        Log.i("Erro", "L105 viagens efetuadas");
                }
                else{
                    Log.i("Erro", "L109 viagens efetuadas_sem data");
                }
            }

            @Override
            public void onFailure(Call<Model_Viagens_Efetuadas> call, Throwable t) {
                Log.i("Failure:", t.toString());
                //makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });
    }

    public void createAdapters(){


        ArrayList<String> Local_Partida = new ArrayList<>();
        ArrayList<String> Local_Chegada = new ArrayList<>();
        ArrayList<String> Local_PartidaCoordenadas = new ArrayList<>();
        ArrayList<String> Local_ChegadaCoordenadas = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> hora = new ArrayList<>();
        ArrayList<Integer> idViagem = new ArrayList<>();
        ArrayList<String> valorViagem = new ArrayList<>();

        for (int i = 0; i < informacaoViagem.size(); i++) {
            if(informacaoViagem.get(i).getViagem_efetuada() == 0) { //confirma se a viagem é efetuada ou não
                Local_Partida.add(informacaoViagem.get(i).getOrigemNome());
                Local_Chegada.add(informacaoViagem.get(i).getDestinoNome());
                Local_PartidaCoordenadas.add(informacaoViagem.get(i).getDestino_Coordenadas());
                Local_ChegadaCoordenadas.add(informacaoViagem.get(i).getOrigem_Coordenadas());

                data.add(informacaoViagem.get(i).getDia_viagem());
                hora.add(informacaoViagem.get(i).getHora_viagem());

                idViagem.add(informacaoViagem.get(i).getId_viagem());
                valorViagem.add(informacaoViagem.get(i).getValor_viagem());
            }
        }
        viagens_marcadas_array_test lItems = new viagens_marcadas_array_test(Local_Partida, Local_Chegada, Local_PartidaCoordenadas, Local_ChegadaCoordenadas, data, hora, idViagem, valorViagem);




        lView = (ListView)findViewById(R.id.viagens_marcadas_listview);
        lAdapter = new CustomListAdapter_marcadas_teste(viagens_marcadas.this,
                user_id,
                lItems.data,
                lItems.Local_Chegada,
                lItems.Local_ChegadaCoordenadas,
                lItems.Local_Partida,
                lItems.Local_PartidaCoordenadas,
                lItems.hora,
                lItems.idViagem,
                lItems.valorViagem);

        lView.setAdapter(lAdapter);


    }

    public void Cancelar_Viagem(View view){//falta saber como enviar a viagem certa
        Intent Cancelar_Viagem = new Intent(viagens_marcadas.this, Cancelar_Viagem.class);
        Cancelar_Viagem.putExtra("user_id", user_id);
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
        goMaisInfo.putExtra("user_id", user_id);
        goMaisInfo.putExtra("localPartida", localPartidatxt.getText());
        goMaisInfo.putExtra("localChegada", localChegadatxt.getText());
        goMaisInfo.putExtra("dataViagem", dataViagemtxt.getText());
        goMaisInfo.putExtra("horaViagem", horaViagemtxt.getText());
       // goMaisInfo.putExtra("distanciaViagem", distanciaViagemtxt.getText());
        startActivity(goMaisInfo);
    }

}
