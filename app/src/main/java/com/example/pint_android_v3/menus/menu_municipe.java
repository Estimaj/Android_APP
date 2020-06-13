package com.example.pint_android_v3.menus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.BaseDadosInterface;
import com.example.pint_android_v3.Model;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral_pro;
import com.example.pint_android_v3.marcar_viagem;
import com.example.pint_android_v3.perfis.perfil_cliente;
import com.example.pint_android_v3.pesquisar_utilizador;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.Get_user;
import com.example.pint_android_v3.viagens_marcadas.viagens_marcadas;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class menu_municipe extends barra_lateral_pro {

    private String BASE_URL ="http://10.0.2.2:3000";
    private int id_user;

    TextView Nome;
    TextView Localidade;
    ImageView btn_Perfil;
    ImageView btn_Marcadas;
    ImageView btn_Efetuadas;
    ImageView btn_Pesquisar;
    ImageView btn_Marcar_Viagem;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Log.i("oncreate:", "on create Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);
        Bar_Settings();


        Nome = (TextView) findViewById(R.id.user_Name_menu_cliente);
        Localidade = (TextView) findViewById(R.id.user_localidade_menu_cliente);
        btn_Perfil = (ImageView) findViewById(R.id.user_Inner_menu_cliente);
        btn_Marcadas = (ImageView) findViewById(R.id.bt_Viagens_Marcadas_menu_cliente);
        btn_Efetuadas = (ImageView) findViewById(R.id.btn_Viagens_Efetuadas_menu_cliente);
        btn_Pesquisar = (ImageView) findViewById(R.id.btn_Pesquisar_Utilizador_menu_cliente);
        btn_Marcar_Viagem = (ImageView) findViewById(R.id.btn_Marcar_Viagem_menu_cliente);
        btn_Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Perfil();
            }
        });
        btn_Efetuadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Viagens_Efetuadas();
            }
        });
        btn_Marcadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Viagens_Marcadas();
            }
        });
        btn_Pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Pesquisar();
            }
        } );
        btn_Marcar_Viagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Marcar_Viagem();
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
           id_user = (int) b.get("user_id");
           //Log.i("id_user", ""+ id_user);
        }
        Get_user_id_information(id_user);

    }

    public void Clicar_Perfil()
    {
        Intent Perfil = new Intent(menu_municipe.this, perfil_cliente.class);
        Perfil.putExtra("Nome", Nome.getText());
        startActivity(Perfil);
    }
    public void Clicar_Viagens_Marcadas()
    {
        Intent Viagens = new Intent(menu_municipe.this, viagens_marcadas.class);
        startActivity(Viagens);

    }

    public void Clicar_Viagens_Efetuadas()
    {
        Intent Viagens = new Intent(menu_municipe.this, viagens_efetuadas.class);
        startActivity(Viagens);

    }

    public void Clicar_Pesquisar()
    {
        Intent Pesquisar = new Intent(menu_municipe.this, pesquisar_utilizador.class);
        startActivity(Pesquisar);

    }
    public void Clicar_Marcar_Viagem()
    {
        Intent Marcar = new Intent(menu_municipe.this, marcar_viagem.class);
        startActivity(Marcar);

    }

    public void Get_user_id_information(int id){
        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Log.i("O id do user:", ""+ id);
        Call<Model> call = baseDadosInterface.executeGetUser(""+id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if (!response.isSuccessful()){
                    makeToastFordesambiguacao("Erro a ir ao link");
                }
                if (response.code() == 200){
                    if (response.body() != null) {
                        Log.i("Server Info:", ""+ response.body().getSuccess());
                        Log.i("Server Info:", ""+ response.body().getGet_user().toString());
                    }else
                        makeToastFordesambiguacao("Erro Server Info");
                }
                else{
                    makeToastFordesambiguacao("Erro: 'Sem data'"+ response.message());
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                makeToastFordesambiguacao("Failure");
            }
        });
    }

    public void makeToastFordesambiguacao(String msg){
        Toast.makeText(menu_municipe.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
