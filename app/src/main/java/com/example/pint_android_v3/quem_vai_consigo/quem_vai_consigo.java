package com.example.pint_android_v3.quem_vai_consigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.ModelListagemPassageirosCondutor;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.dataListagemCondutor;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class quem_vai_consigo extends barra_lateral_pro {

    //Testar se isto funciona
    private int user_id;
    private int viagem_id;
    private String BASE_URL ="http://10.0.2.2:3000";
    private ArrayList<dataListagemCondutor> informacaoViagem;
    ArrayList<String> ListNome;
    ArrayList<String> ListLocalidade;
    ArrayList<Integer> ListIdPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_vai_consigo);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            viagem_id = (int) b.get("idViagem");
            //Log.i("id_user", ""+ id_user);

        }
        getInformationFromdb(viagem_id);
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
        Call<ModelListagemPassageirosCondutor> call = baseDadosInterface.executeGetListagem(id);

        call.enqueue(new Callback<ModelListagemPassageirosCondutor>() {
            @Override
            public void onResponse(Call<ModelListagemPassageirosCondutor> call, Response<ModelListagemPassageirosCondutor> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "L99 viagens efetuadas");
                }
                if (response.code() == 200){
                    if (response.body().getDataListagemCondutor() != null) {
                        //Log.i("body", "" + response.body().getDataViagem().get(0).toString());
                        informacaoViagem = response.body().getDataListagemCondutor();
                        PopulateLista();

                    }else
                        Log.i("Erro", "L105 listagem condutor");
                }
                else{
                    Log.i("Erro", "L109 listagem condutor sem sata");
                }
            }

            @Override
            public void onFailure(Call<ModelListagemPassageirosCondutor> call, Throwable t) {
                Log.i("Failure:", t.toString());
                //makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });
    }

    private void PopulateLista()
    {
       ListNome = new ArrayList<>();
       ListLocalidade = new ArrayList<>();
       ListIdPass = new ArrayList<>();

        for (int i = 0; i < informacaoViagem.size(); i++) {
            ListNome.add(informacaoViagem.get(i).getNomeUtilizador());
            ListLocalidade.add(informacaoViagem.get(i).getMoradaUtilizador());
            ListIdPass.add(informacaoViagem.get(i).getIdPass());
        }
        HideButtons(ListNome.size());//Esconde os botões
        SetTexts();




    }


    private void HideButtons(int num)
    {
        if(num >= 4) return;

        ImageView Contentor;
        TextView Nome;
        TextView Localidade;
        ImageView Perfil;
        ImageView Caixa = findViewById(R.id.contentor_outros_tripulantes);

        if(num <= 3)
        {
            Contentor = findViewById(R.id.contentor4_quem_vai_consigo2);
            Perfil = findViewById(R.id.tripulante_image_4_quem_vai_consigo2);
            Localidade = findViewById(R.id.tripulante_localidade_4_quem_vai_consigo2);
            Nome = findViewById(R.id.tripulante_nome_4_quem_vai_consigo2);

            Contentor.setVisibility(View.GONE);
            Perfil.setVisibility(View.GONE);
            Localidade.setVisibility(View.GONE);
            Nome.setVisibility(View.GONE);

        }
        if(num <=2)
        {
            Contentor = findViewById(R.id.contentor3_quem_vai_consigo);
            Perfil = findViewById(R.id.tripulante_image_3_quem_vai_consigo);
            Localidade = findViewById(R.id.tripulante_localidade_3_quem_vai_consigo);
            Nome = findViewById(R.id.tripulante_nome_3_quem_vai_consigo);

            Contentor.setVisibility(View.GONE);
            Perfil.setVisibility(View.GONE);
            Localidade.setVisibility(View.GONE);
            Nome.setVisibility(View.GONE);

        }
        if(num <=1)
        {
            Contentor = findViewById(R.id.contentor2_quem_vai_consigo2);
            Perfil = findViewById(R.id.tripulante_image_2_quem_vai_consigo2);
            Localidade = findViewById(R.id.tripulante_localidade_2_quem_vai_consigo2);
            Nome = findViewById(R.id.tripulante_nome_2_quem_vai_consigo2);

            Contentor.setVisibility(View.GONE);
            Perfil.setVisibility(View.GONE);
            Localidade.setVisibility(View.GONE);
            Nome.setVisibility(View.GONE);


        }




    }

    private void SetTexts()
    {
        int num = ListNome.size();


        TextView Nome;
        TextView Localidade;


        if(num <= 4)
        {
            Localidade = findViewById(R.id.tripulante_localidade_4_quem_vai_consigo2);
            Nome = findViewById(R.id.tripulante_nome_4_quem_vai_consigo2);
            Localidade.setText(ListNome.get(3));
            Nome.setText(ListLocalidade.get(3));


        }
        if(num <= 3)
        {
            Localidade = findViewById(R.id.tripulante_localidade_3_quem_vai_consigo);
            Nome = findViewById(R.id.tripulante_nome_3_quem_vai_consigo);
            Localidade.setText(ListNome.get(2));
            Nome.setText(ListLocalidade.get(2));


        }
        if(num <=2)
        {
            Localidade = findViewById(R.id.tripulante_localidade_2_quem_vai_consigo2);
            Nome = findViewById(R.id.tripulante_nome_2_quem_vai_consigo2);
            Localidade.setText(ListNome.get(1));
            Nome.setText(ListLocalidade.get(1));


        }
        if(num <=1)
        {
            Localidade = findViewById(R.id.tripulante_localidade_1_quem_vai_consigo);
            Nome = findViewById(R.id.tripulante_nome_1_quem_vai_consigo);
            Localidade.setText(ListNome.get(0));
            Nome.setText(ListLocalidade.get(0));


        }




    }


}
