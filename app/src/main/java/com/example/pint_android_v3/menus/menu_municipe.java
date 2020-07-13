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

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Model_User_Information;
import com.example.pint_android_v3.DataBase.DividaUtilizador.ModelDividaUtilizador;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.Marcar_viagem;
import com.example.pint_android_v3.perfis.perfil_cliente;
import com.example.pint_android_v3.pesquisar_utilizador;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Get_user;
import com.example.pint_android_v3.viagens_marcadas.viagens_marcadas;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class menu_municipe extends barra_lateral_pro {

    private String BASE_URL ="http://10.0.2.2:3000";
    private int user_id;

    TextView Nome;
    TextView Localidade;
    ImageView btn_Perfil;
    ImageView btn_Marcadas;
    ImageView btn_Efetuadas;
    ImageView btn_Pesquisar;
    ImageView btn_Marcar_Viagem;

    private Get_user user;

    private boolean emDivida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);

        Nome = findViewById(R.id.user_Name_menu_cliente);
        Localidade =  findViewById(R.id.user_localidade_menu_cliente);
        btn_Perfil =  findViewById(R.id.user_Inner_menu_cliente);
        btn_Marcadas =  findViewById(R.id.bt_Viagens_Marcadas_menu_cliente);
        btn_Efetuadas =  findViewById(R.id.btn_Viagens_Efetuadas_menu_cliente);
        btn_Pesquisar =  findViewById(R.id.btn_Pesquisar_Utilizador_menu_cliente);
        btn_Marcar_Viagem =  findViewById(R.id.btn_Marcar_Viagem_menu_cliente);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Bd
            Get_user_id_information(user_id);
            //ha uma possibilidade de haver divida e n puder marcar viagem
            verificarDividasUtilizador(user_id);
        }

        //vai usar a func da class barra_lateral_pro para criar a o hamburger e as setting
        Bar_Settings(user_id);

        if (user != null){
            Log.i("User info", user.toString());
        }

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
    }




    public void Clicar_Perfil() //ao clicar na foto de perfil do utilizador
    {
        Intent Perfil = new Intent(menu_municipe.this, perfil_cliente.class);
        Perfil.putExtra("user_id", user_id);
        if(user != null){
            Perfil.putExtra("Nome", user.getNome_utilizador());
            Perfil.putExtra("Origem", user.getMorada_utilizador());
            Perfil.putExtra("Idade", user.getData_nascimento_utilizador()); //temos que ver se é null pq se for podemos meter uma msg a dizer que falta dizer a idade ou ent uma func para calcular a idade
            Perfil.putExtra("Telefone", user.getTelefone_utilizador());
            Perfil.putExtra("Email", user.getEmail_utilizador());
        }
        startActivity(Perfil);
    }

    public void Clicar_Viagens_Marcadas()
    {
        Intent Viagens = new Intent(menu_municipe.this, viagens_marcadas.class);
        Viagens.putExtra("user_id", user_id);
        startActivity(Viagens);
    }

    public void Clicar_Viagens_Efetuadas()
    {
        Intent Viagens = new Intent(menu_municipe.this, viagens_efetuadas.class);
        Viagens.putExtra("user_id", user_id);
        startActivity(Viagens);
    }

    public void Clicar_Pesquisar()
    {
        Intent Pesquisar = new Intent(menu_municipe.this, pesquisar_utilizador.class);
        Pesquisar.putExtra("user_id", user_id);
        startActivity(Pesquisar);
    }

    public void Clicar_Marcar_Viagem()
    {
        if(!emDivida){
            Intent Marcar = new Intent(menu_municipe.this, Marcar_viagem.class);
            Marcar.putExtra("user_id", user_id);
            startActivity(Marcar);
        }
        else {
            makeToastFordesambiguacao("Tem Dividas Pendentes");
        }
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
        Call<Model_User_Information> call = baseDadosInterface.executeGetUser(id);

        call.enqueue(new Callback<Model_User_Information>() {
            @Override
            public void onResponse(Call<Model_User_Information> call, Response<Model_User_Information> response) {
                if (!response.isSuccessful()){
                    makeToastFordesambiguacao("Erro a ir ao link");
                }
                if (response.code() == 200){
                    //coloca a informacao do user na activity
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

    private void verificarDividasUtilizador(int id) {
        emDivida = false; //permite o utilizador marcar viagem antes de confirmar se pode ou nao
        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<ModelDividaUtilizador> call = baseDadosInterface.executeGetUserDivida(id);

        call.enqueue(new Callback<ModelDividaUtilizador>() {
            @Override
            public void onResponse(Call<ModelDividaUtilizador> call, Response<ModelDividaUtilizador> response) {
                if (!response.isSuccessful()){
                    makeToastFordesambiguacao("Erro a ir ao link");
                }
                //se um utilizador tiver uma divida nao paga, ja n pode marcar viagem
                for(int i = 0; i < response.body().getDataDividaUtilizadors().size(); i++){
                    if(response.body().getDataDividaUtilizadors().get(i).getEstado_Coima() == 0){
                        emDivida = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<ModelDividaUtilizador> call, Throwable t) {
                Log.i("Failure:", t.toString());
                makeToastFordesambiguacao("Failure: "+ t.toString());
            }
        });
    }

    public void makeToastFordesambiguacao(String msg){
        Toast.makeText(menu_municipe.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
