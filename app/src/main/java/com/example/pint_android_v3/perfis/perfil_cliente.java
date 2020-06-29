package com.example.pint_android_v3.perfis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.Model;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral_pro;
import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class perfil_cliente extends barra_lateral_pro {

    private String BASE_URL ="http://10.0.2.2:3000";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView Nome, Origem, Idade, Telefone, Email;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_motorista);
        Log.i("Entrei aqui", "Estou na class perfil_cliente");
        Nome = findViewById(R.id.User_Name);
        Origem = findViewById(R.id.textView_user_location_information_perfil_motorista);
        Idade = findViewById(R.id.textView_user_idade_information_perfil_motorista);
        Telefone = findViewById(R.id.textView_user_telefone_information_perfil_motorista);
        Email= findViewById(R.id.textView_user_email_information_perfil_motorista);

        Intent I = getIntent();
        Bundle b = I.getExtras();
        if(b!=null)
        {
            user_id =(int) b.get("user_id");
            //Get_user_id_information(user_id);
        }

        Bar_Settings(user_id);
    }

    public void Go_Back()
    {
        Intent GO = new Intent(perfil_cliente.this, menu_municipe.class);
        GO.putExtra("user_id", user_id);
        startActivity(GO);
    }

    public void Get_user_id_information(int id){
        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        //Log.i("O id do user:", ""+ id);
        Call<Model> call = baseDadosInterface.executeGetUser(""+id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (!response.isSuccessful()){
                    //makeToastFordesambiguacao("Erro a ir ao link");
                    Log.i("Erro", "Erro a ir ao link class perfil_cliente");
                }
                if (response.code() == 200){
                    if (response.body() != null) {
                        Nome.setText(response.body().getGet_user().get(0).getNome_utilizador());
                        Origem.setText(response.body().getGet_user().get(0).getMorada_utilizador());
                        //Idade.setText(""+ getIdadeUser(response.body().getGet_user().get(0).getData_nascimento_utilizador()));
                        Telefone.setText(response.body().getGet_user().get(0).getTelefone_utilizador());
                        Email.setText(response.body().getGet_user().get(0).getEmail_utilizador());
                    }
                        //makeToastFordesambiguacao("Erro Server Info");
                }
                else{
                    //makeToastFordesambiguacao("Erro: 'Sem data'"+ response.message());
                    Log.i("Erro", "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Log.i("Failure:", t.toString());
                //makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });


    }

    public int getIdadeUser(String dataNascimento){
        int idade=0;

        return idade;
    }

}