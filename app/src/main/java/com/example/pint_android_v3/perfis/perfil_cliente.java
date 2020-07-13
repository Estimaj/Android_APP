package com.example.pint_android_v3.perfis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Model_User_Information;
import com.example.pint_android_v3.DataBase.DividaUtilizador.ModelDividaUtilizador;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class perfil_cliente extends barra_lateral_pro {

    private String BASE_URL ="http://10.0.2.2:3000";

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
            Get_user_id_information(user_id);
            GetUserDividaSoma(user_id);
        }

        Bar_Settings(user_id);
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
                    Log.i("Erro", "Erro a ir ao link class perfil_cliente");
                }
                if (response.code() == 200){
                    //Log.i("user_perfil_cliente", response.body().getGet_user().get(0).toString());
                    Nome.setText(response.body().getGet_user().get(0).getNome_utilizador());
                    Origem.setText(response.body().getGet_user().get(0).getMorada_utilizador());
                    Idade.setText(""+ getIdadeUser(response.body().getGet_user().get(0).getData_nascimento_utilizador()));
                    Telefone.setText(response.body().getGet_user().get(0).getTelefone_utilizador());
                    Email.setText(response.body().getGet_user().get(0).getEmail_utilizador());
                }
                else{
                    Log.i("Erro", "" + response.message());
                }
            }

            @Override
            public void onFailure(Call<Model_User_Information> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });


    }

    public int getIdadeUser(String dataNascimento){
        if(dataNascimento == null) return 0;
        int idade;
        //Log.i("idade", dataNascimento);

        //get data nascimento separado dia[0] mes[1] ano[2]
        String dividindoDataNascimento[]= dataNascimento.split("\\W");//dividir a data nascimento em numeros separados... o \\W é a dizer que o separador é '/'

        //get data current separado dia[0] mes[1] ano[2]
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(System.currentTimeMillis());
        String dividindoDataCurrent [] = formatter.format(date).split("\\W");

        idade = Integer.parseInt(dividindoDataCurrent[0]) - Integer.parseInt(dividindoDataNascimento[0]);
        if(Integer.parseInt(dividindoDataNascimento[1]) > Integer.parseInt(dividindoDataCurrent[1])){ //comparar mes
            idade --;
        }
        else if(Integer.parseInt(dividindoDataNascimento[1]) == Integer.parseInt(dividindoDataCurrent[1])){
            if(Integer.parseInt(dividindoDataNascimento[2]) > Integer.parseInt(dividindoDataCurrent[2])){ //compara o dia
                idade--;
            }
        }
        return idade;
    }

    private void GetUserDividaSoma(int id) {
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
                    Log.i("Erro", "Erro a ir ao link class perfil_motorista");
                }
                if (response.code() == 200) {
                    double valorDivida = 0;
                    for (int i = 0; i < response.body().getDataDividaUtilizadors().size(); i++) {
                        if (response.body().getDataDividaUtilizadors().get(i).getEstado_Coima() == 0) {
                            valorDivida += Double.parseDouble(response.body().getDataDividaUtilizadors().get(i).getValor_Coima());
                        }
                    }
                    TextView txtDividaPerfilMotorista = findViewById(R.id.textView_user_money_information_perfil_motorista);
                    txtDividaPerfilMotorista.setText("" + valorDivida);
                }
            }

            @Override
            public void onFailure(Call<ModelDividaUtilizador> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
    }

}