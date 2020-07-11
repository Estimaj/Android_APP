package com.example.pint_android_v3;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.ViagensInformacao.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.DataBase.ViagensInformacao.dataViagem;
import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.menus.menu_motorista;
import com.example.pint_android_v3.menus.menu_municipe;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class pagamentoCondutor extends barra_lateral_condutor {

    private String BASE_URL ="http://10.0.2.2:3000";
    private ArrayList<dataViagem> informacaoViagem;

    private int user_id;
    private int idViagem;

    private dataViagem viagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_condutor);
        ImageView btnPagar = findViewById(R.id.dinheiro_btn_tarifa_condutor);
        ImageView btnTalao = findViewById(R.id.talao_btn_tarifa_condutor);

        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_Botao_Pagar();
            }
        });

        btnTalao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_Talao();
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            idViagem = (int) b.get("idViagem");

            povoarInformacaoActivity(user_id);
        }


        Bar_Settings(user_id);
    }

    private void povoarInformacaoActivity(int id) {
        if(idViagem == 0) return;

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        //Log.i("O id do user:", ""+ id);
        Call<Model_Viagens_Efetuadas> call = baseDadosInterface.executeViagemEfetuadaMotorista(id);

        call.enqueue(new Callback<Model_Viagens_Efetuadas>() {
            @Override
            public void onResponse(Call<Model_Viagens_Efetuadas> call, Response<Model_Viagens_Efetuadas> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "verificar o link na interface");
                }
                for (int i = 0; i< response.body().getDataViagem().size(); i++){
                    if (response.body().getDataViagem().get(i).getId_viagem() == idViagem){
                        viagem = response.body().getDataViagem().get(i);
                    }
                }
                createadapter();
            }

            @Override
            public void onFailure(Call<Model_Viagens_Efetuadas> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
    }

    private void createadapter(){
        if (viagem == null) return;

        //povoar
        TextView txt;
        ImageView img;
        txt = findViewById(R.id.local_user_textView_origem_tarifa_condutor);
        txt.setText(viagem.getOrigemNome());
        txt = findViewById(R.id.local_user_textView_destino__tarifa_condutor);
        txt.setText(viagem.getDestinoNome());
        txt = findViewById(R.id.local_user_textView_hora_tarifa_condutor);
        txt.setText(viagem.getHora_viagem());
        txt = findViewById(R.id.local_user_textView_dia_tarifa_condutor);
        txt.setText(viagem.getDia_viagem());
        if(viagem.getAnimal() == 0){
            img = findViewById(R.id.canideo_check_tarifa_condutor);
            img.setVisibility(View.GONE);
        }
        if(viagem.getBagagem_pedido() == 0){
            img = findViewById(R.id.mala_check_tarifa_condutor);
            img.setVisibility(View.GONE);
        }
        if(viagem.getNecessidadesespeciais_pedido() == 0){
            img = findViewById(R.id.wheel_check_tarifa_condutor);
            img.setVisibility(View.GONE);
        }
    }

    public void Click_Botao_Pagar()
    {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_pagamento_primeiro_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_pagamento_primeiro);
        ImageView no_btn = dialogView.findViewById(R.id.no_btn_alert_dialog_adapter_pagamento_primeiro);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_Botao_Confirm_Pagar();
                dialogBuilder.dismiss();

            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Click_Botao_Negar_Pagar();
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();
    }

    public void Click_Botao_Confirm_Pagar()
    {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_pagamento_sim_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_pagamento_sim);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent voltarMenu = new Intent(pagamentoCondutor.this, menu_motorista.class);
                voltarMenu.putExtra("user_id", user_id);
                startActivity(voltarMenu);*/
                dialogBuilder.dismiss();

            }
        });


        dialogBuilder.show();
    }

    public void Click_Botao_Negar_Pagar()
    {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_pagamento_nao_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_pagamento_nao);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent voltarMenu = new Intent(pagamentoCondutor.this, menu_motorista.class);
                voltarMenu.putExtra("user_id", user_id);
                startActivity(voltarMenu);

            }
        });


        dialogBuilder.show();




    }

    public void Click_Talao()
    {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_talao_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_pagamento_talao);

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent voltarMenu = new Intent(pagamentoCondutor.this, menu_motorista.class);
                voltarMenu.putExtra("user_id", user_id);
                startActivity(voltarMenu);*/
                dialogBuilder.dismiss();


            }
        });

        dialogBuilder.show();
    }







}
