package com.example.pint_android_v3.classificar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.pint_android_v3.menus.menu_municipe;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class classificar_viagem extends barra_lateral_pro {


    private int user_id;
    private int id_viagem;
    private String BASE_URL ="https://pintbackend.herokuapp.com";
    RatingBar ratingBar1;


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
        }
        getInformationFromdb(id_viagem);
        Bar_Settings(user_id);

        ratingBar1 = findViewById(R.id.rating_classificar_viagem);

        Button classificarBtnFinal = findViewById(R.id.Button_classficar_viagem);
        classificarBtnFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MudarClassif();
                Intent pagamento = new Intent( classificar_viagem.this , menu_municipe.class);
                pagamento.putExtra("user_id", user_id);
                startActivity(pagamento);
            }
        });
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

        Call<ModelViagemUnica> call = baseDadosInterface.executeViagemUnica(id);

        call.enqueue(new Callback<ModelViagemUnica>() {
            @Override
            public void onResponse(Call<ModelViagemUnica> call, Response<ModelViagemUnica> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "L99 viagens efetuadas");
                }
                if (response.code() == 200){
                    ArrayList<dataViagem> informacaoViagem = response.body().getDataViagem();
                    populateContainer(informacaoViagem);
                }
            }

            @Override
            public void onFailure(Call<ModelViagemUnica> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });

    }

    private void populateContainer(ArrayList<dataViagem> informacaoViagem)
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
        double classif0_5 = (double) ratingBar1.getRating();
        int classif0_10 =(int) (classif0_5 * 2);

        ModelClassif classificacao = new ModelClassif(user_id, id_viagem, classif0_10);

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<ModelClassif> call = baseDadosInterface.executeMudarClassificacao(classificacao);

        call.enqueue(new Callback<ModelClassif>() {
            @Override
            public void onResponse(Call<ModelClassif> call, Response<ModelClassif> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "L171 viagens efetuadas");
                }
                else{
                    Log.i("Sucesso", "Classificacao updated");
                }
            }

            @Override
            public void onFailure(Call<ModelClassif> call, Throwable t) {
                Log.i("onFailure:", t.toString());
            }
        });
    }
}
