package com.example.pint_android_v3.viagens_efetuadas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Model_User_Information;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.dataViagem;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar.classificar_condutor;
import com.example.pint_android_v3.classificar.classificar_viagem;
import com.example.pint_android_v3.mapas.mais_info_mapa_cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viagens_efetuadas extends barra_lateral_pro {
    ListView lView;
    ListAdapter lAdapter;
    viagens_efetuadas_array_test lItems;

    private String BASE_URL ="http://10.0.2.2:3000";
    private int user_id;

    //luis 
    private String[] Local_Partida = {};
    private String[] Local_Chegada = {};
    private String[] distancia= {};
    private String[] tempo= {};
    private String[] data= {};
    private String[] hora= {};

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
        user_id = 4;
        getInformationFromdb(user_id);

        Local_Partida[0] = "";
        Local_Chegada[0] = "";
        distancia[0] = "";
        tempo[0] = "";
        data[0] = "";
        hora[0] = "";

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
                    if (response.body() != null) {
                        Log.i("body", ""+ response.body().getDataViagem().get(0).toString());
                       for (int i = 0; i < response.body().getDataViagem().size(); i++){
                           /*private String[] Local_Partida;
                            private String[] Local_Chegada;
                            private String[] distancia;
                            private String[] tempo;
                            private String[] data;
                            private String[] hora;*/
                           Local_Partida[i] =response.body().getDataViagem().get(i).getLocalidade_Origem().getNome_localidade();
                           Local_Chegada[i] =response.body().getDataViagem().get(i).getLocalidade_Destino().getNome_localidade();

                           distancia[i] = "";
                           tempo[i] = "";

                           data[i] = response.body().getDataViagem().get(i).getViagemRegistada().getDia_viagem();
                           hora[i] = response.body().getDataViagem().get(i).getViagemRegistada().getHora_viagem();

                           lItems = new viagens_efetuadas_array_test(Local_Partida, Local_Chegada, distancia, tempo, data, hora);
                       }
                    }else
                        Log.i("Erro", "L105 viagens efetuadas");
                        //makeToastFordesambiguacao("Erro Server Info");
                }
                else{
                    Log.i("Erro", "L109 viagens efetuadas");
                    //makeToastFordesambiguacao("Erro: 'Sem data'"+ response.message());
                }
            }

            @Override
            public void onFailure(Call<Model_Viagens_Efetuadas> call, Throwable t) {
                Log.i("Failure:", t.toString());
                //makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });
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
