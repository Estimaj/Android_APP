package com.example.pint_android_v3.menus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Get_user;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Model_User_Information;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;
import com.example.pint_android_v3.perfis.perfil_motorista;
import com.example.pint_android_v3.servico_decorrer.servico_a_decorrer;
import com.example.pint_android_v3.viagens_efetuadas_condutor.viagens_efetuadas_condutor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class menu_motorista extends barra_lateral_condutor {

    private String BASE_URL ="https://pintbackend.herokuapp.com";
    private Get_user user;
    private int user_id;

    TextView Nome;
    TextView Localidade;
    ImageView btn_Perfil;
    ImageView btn_Efetuadas;
    ImageView btn_Servicos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_motorista);
        Nome =  findViewById(R.id.activity_menu_motorista_user_Name);
        Localidade =  findViewById(R.id.activity_menu_motorista_user_localidade);
        btn_Perfil =  findViewById(R.id.activity_menu_motorista_user_Inner);
        btn_Efetuadas =  findViewById(R.id.activity_menu_motorista_btn_Viagens_Efetuadas);
        btn_Servicos = findViewById(R.id.activity_menu_motorista_btn_Servicos);


        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            Get_user_id_information(user_id);
        }

        if(user != null){
            Nome.setText(user.getNome_utilizador());
            Localidade.setText(user.getMorada_utilizador());
        }

        Bar_Settings(user_id);

        btn_Servicos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Servicos();
            }
        });

        btn_Efetuadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Viagens_Efetuadas();
            }
        });

        btn_Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Perfil();
            }
        });


    }

    public void Clicar_Servicos()
    {
        Intent servico = new Intent(menu_motorista.this, servico_a_decorrer.class);
        servico.putExtra("user_id", user_id);
        startActivity(servico);
    }

    public void Clicar_Viagens_Efetuadas()
    {
        Intent servico = new Intent(menu_motorista.this, viagens_efetuadas_condutor.class);
        servico.putExtra("user_id", user_id);
        startActivity(servico);
    }

    public void Clicar_Perfil()
    {
        Intent servico = new Intent(menu_motorista.this, perfil_motorista.class);
        servico.putExtra("user_id", user_id);
        startActivity(servico);
    }



    public void Get_user_id_information(int id){
        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<Model_User_Information> call = baseDadosInterface.executeGetUser(id);

        call.enqueue(new Callback<Model_User_Information>() {
            @Override
            public void onResponse(Call<Model_User_Information> call, Response<Model_User_Information> response) {
                if (!response.isSuccessful()){
                    makeToastFordesambiguacao("Erro a ir ao link");
                }
                if (response.code() == 200){
                    Nome.setText(response.body().getGet_user().get(0).getNome_utilizador());
                    Localidade.setText(response.body().getGet_user().get(0).getMorada_utilizador());
                    user = response.body().getGet_user().get(0);
                }
            }
            @Override
            public void onFailure(Call<Model_User_Information> call, Throwable t) {
                Log.i("Failure:", t.toString());
                makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });
    }

    public void makeToastFordesambiguacao(String msg){
        Toast.makeText(menu_motorista.this, msg,
                Toast.LENGTH_LONG).show();
    }

}