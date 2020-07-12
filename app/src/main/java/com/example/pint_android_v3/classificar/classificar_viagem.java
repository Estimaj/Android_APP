package com.example.pint_android_v3.classificar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.Classificacao.ModelClassif;
import com.example.pint_android_v3.DataBase.Pedido_Viagem;
import com.example.pint_android_v3.DataBase.ViagemUnica.ModelViagemUnica;
import com.example.pint_android_v3.DataBase.ViagensInformacao.dataViagem;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class classificar_viagem extends barra_lateral_pro {


    private int user_id;
    private int id_viagem;
    private String BASE_URL ="http://10.0.2.2:3000";
    RatingBar ratingBar1;
    private ArrayList<dataViagem> informacaoViagem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificar_viagem);
        final RatingBar ratingBar = findViewById(R.id.rating_classificar_viagem);
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "Rating is :" + ratingBar.getRating();
                Toast.makeText(classificar_viagem.this, rating, Toast.LENGTH_LONG).show();
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null) {
            user_id = (int) b.get("user_id");
            id_viagem = (int) b.get("id_viagem");
            //Log.i("id_user", ""+ id_user);

        }
        getInformationFromdb(id_viagem);
        Bar_Settings(user_id);

        ratingBar1 = findViewById(R.id.rating_classificar_viagem);


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
        Call<ModelViagemUnica> call = baseDadosInterface.executeViagemUnica(id);

        call.enqueue(new Callback<ModelViagemUnica>() {
            @Override
            public void onResponse(Call<ModelViagemUnica> call, Response<ModelViagemUnica> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "L99 viagens efetuadas");
                }
                if (response.code() == 200){
                    if (response.body().getDataViagem() != null) {
                        //Log.i("body", "" + response.body().getDataViagem().get(0).toString());
                        informacaoViagem = response.body().getDataViagem();
                        populateContainer();


                    }else
                        Log.i("Erro", "L105 listagem condutor");
                }
                else{
                    Log.i("Erro", "L109 listagem condutor sem sata");
                }
            }

            @Override
            public void onFailure(Call<ModelViagemUnica> call, Throwable t) {
                Log.i("Failure:", t.toString());
                //makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });

    }

    private void populateContainer()
    {
        ImageView CheckDog = findViewById(R.id.checkbox_cao_classificar_viagem);
        ImageView CheckMala = findViewById(R.id.checkbox_mala_classificar_viagem);
        ImageView CheckWheel = findViewById(R.id.checkbox_cadeira_rodas_classificar_viagem);
        TextView Origem = findViewById(R.id.origem_value_classificar_viagem);
        TextView  Destino = findViewById(R.id.destino_value_classificar_viagem);
        TextView Hora = findViewById(R.id.hora_classificar_viagem);
        TextView Dia = findViewById(R.id.dia_value_classificar_viagem);

        if(informacaoViagem.get(0).getAnimal() == 0)
        {
            CheckDog.setVisibility(View.GONE);

        }
        if(informacaoViagem.get(0).getBagagem_pedido() == 0)
        {
            CheckMala.setVisibility(View.GONE);

        }
        if(informacaoViagem.get(0).getNecessidadesespeciais_pedido() == 0)
        {
            CheckWheel.setVisibility(View.GONE);
        }
        Origem.setText(informacaoViagem.get(0).getOrigemNome());
        Destino.setText(informacaoViagem.get(0).getDestinoNome());
        Hora.setText(informacaoViagem.get(0).getHora_viagem() + "h");
        Dia.setText(informacaoViagem.get(0).getDia_viagem());





    }


    private void MudarClassif(){
        //o cancelar é suposto estar a 0, falta o locals e partilha
        if(user_id == 0){
            Log.i("user_id_error", "user id = 0, func criarPedido_viagem, marcar_viagem.java");
            return;
        }
        int classif = (int) ratingBar1.getRating();


        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<ModelClassif> call = baseDadosInterface.executeMudarClassificacao(classif);

        call.enqueue(new Callback<ModelClassif>() {
            @Override
            public void onResponse(Call<ModelClassif> call, Response<ModelClassif> response) {
                if (!response.isSuccessful()){

                }
                else{
                    if(response.body() != null) {
                        //makeToastForMarcar("Penso eu que devia haver um novo pedido");
                        Log.i("Pedido", response.body().toString());
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<ModelClassif> call, Throwable t) {
                Log.i("onFailure:", t.toString());

            }
        });

    }


}
