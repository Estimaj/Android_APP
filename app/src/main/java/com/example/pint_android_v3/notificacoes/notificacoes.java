package com.example.pint_android_v3.notificacoes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.ListagemCidadao.dataListagemCidadao;
import com.example.pint_android_v3.DataBase.Notificacoes.DataNotificacoes;
import com.example.pint_android_v3.DataBase.Notificacoes.ModelNotificacoes;
import com.example.pint_android_v3.DataBase.ViagensInformacao.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.classificar.classificar_viagem;
import com.example.pint_android_v3.viagens_efetuadas.CustomListAdapter_efetuadas_teste;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas_array_test;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class notificacoes extends barra_lateral_pro {

    private int user_id;
    ListView lView;
    ListAdapter lAdapter;
    private String BASE_URL ="http://10.0.2.2:3000";
    private ArrayList<DataNotificacoes> informacaoViagem;
    private TextView Nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacoes);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }

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
        Call<ModelNotificacoes> call = baseDadosInterface.executeGetNotificacoes(id);

        call.enqueue(new Callback<ModelNotificacoes>() {
            @Override
            public void onResponse(Call<ModelNotificacoes> call, Response<ModelNotificacoes> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "L99 viagens efetuadas");
                }
                if (response.code() == 200){
                    if (response.body().getDataViagem() != null) {
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
            public void onFailure(Call<ModelNotificacoes> call, Throwable t) {
                Log.i("Failure:", t.toString());
                //makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });
    }

    public void createAdapters(){
        ArrayList<String> ListNome = new ArrayList<>();
        ArrayList<String> ListData = new ArrayList<>();
        ArrayList<Integer> ListTipo = new ArrayList<>();
        ArrayList<Integer> ListIDViagem = new ArrayList<>();



        for (int i = 0; i < informacaoViagem.size(); i++) {
                ListNome.add(informacaoViagem.get(i).getNome());
                ListData.add(informacaoViagem.get(i).getDia_viagem());
                ListTipo.add(informacaoViagem.get(i).getTipo_notif());
                ListIDViagem.add(informacaoViagem.get(i).getId_viagem());

            }

        notificacoes_array_test lItems = new notificacoes_array_test(ListNome, ListData, ListTipo, ListIDViagem);


        lView = findViewById(R.id.listView_para_layout_notificacoes);
        lAdapter = new Custom_Adapter_Notificacoes(
                notificacoes.this,
                user_id,
                ListData,
                ListNome,
                ListTipo,
                ListIDViagem,
                0
                );



        lView.setAdapter(lAdapter);
    }




}
