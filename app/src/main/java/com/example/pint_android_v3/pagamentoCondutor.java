package com.example.pint_android_v3;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.DividaCreate;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.CidadaoPassageiroInformaçao;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.DataListagemCondutor;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.ModelListagemPassageirosCondutor;
import com.example.pint_android_v3.DataBase.UpdatePassageiro.Passageiro;
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

    private String BASE_URL ="https://pintbackend.herokuapp.com";

    private int user_id;
    private int idViagem;

    private dataViagem viagem;
    private DataListagemCondutor passageiroValoresIndividuais;

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

        Call<Model_Viagens_Efetuadas> call = baseDadosInterface.executeViagemEfetuadaMotorista(id);

        call.enqueue(new Callback<Model_Viagens_Efetuadas>() {
            @Override
            public void onResponse(Call<Model_Viagens_Efetuadas> call, Response<Model_Viagens_Efetuadas> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "verificar o link na interface");
                }
                if (response.code() == 200){
                for (int i = 0; i< response.body().getDataViagem().size(); i++){

                        if (response.body().getDataViagem().get(i).getId_viagem() == idViagem){
                            viagem = response.body().getDataViagem().get(i);
                        }
                    }
                    createadapter();
                }
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
        ImageView x_btn = dialogView.findViewById(R.id.white_x_solid_leave_alert_dialog_adapter_pagamento_primeiro);

        valorAPagarPassageiro(dialogView);

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
        x_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();
    }

    private void valorAPagarPassageiro(View dialogView) {
        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<ModelListagemPassageirosCondutor> call2 = baseDadosInterface.executeGetListagem(idViagem);

        call2.enqueue(new Callback<ModelListagemPassageirosCondutor>() {
            @Override
            public void onResponse(Call<ModelListagemPassageirosCondutor> call, Response<ModelListagemPassageirosCondutor> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "verificar o link na interface");
                }
                for (int i = 0; i < response.body().getDataListagemCondutor().size(); i++){
                    if(user_id == response.body().getDataListagemCondutor().get(i).getCidadao().getId_Utilizador()){
                        passageiroValoresIndividuais = response.body().getDataListagemCondutor().get(i);
                    }
                    TextView popUpTexto = dialogView.findViewById(R.id.textView2_pagamento_primeiro);
                    popUpTexto.setText("Preço da viagem: "+passageiroValoresIndividuais.getValorAPagarIndividual()+" euros");
                }
            }

            @Override
            public void onFailure(Call<ModelListagemPassageirosCondutor> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
    }

    public void Click_Botao_Confirm_Pagar()
    {
        updateBDPassageiroComparecenciaPagamento(1,1);

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_pagamento_sim_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_pagamento_sim);

        TextView popUpTexto = dialogView.findViewById(R.id.textView_ValorAPagar_pagamento_sim);
        popUpTexto.setText("Preço da viagem: "+passageiroValoresIndividuais.getValorAPagarIndividual()+" euros");

        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarMenu = new Intent(pagamentoCondutor.this, menu_motorista.class);
                voltarMenu.putExtra("user_id", user_id);
                startActivity(voltarMenu);
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();
    }



    public void Click_Botao_Negar_Pagar()
    {
        updateBDPassageiroComparecenciaPagamento(1, 0);
        criarADividaDoPassageiroQueNaoPagou();
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_pagamento_nao_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_pagamento_nao);

        TextView popUpTexto = dialogView.findViewById(R.id.textView2_pagamento_nao);
        popUpTexto.setText("Preço da viagem: "+passageiroValoresIndividuais.getValorAPagarIndividual()+" euros");

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

    private void criarADividaDoPassageiroQueNaoPagou() {
        DividaCreate divida = new DividaCreate(user_id, passageiroValoresIndividuais.getValorAPagarIndividual());

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<DividaCreate> call = baseDadosInterface.executeCriarDividaPassageiro(divida);

        call.enqueue(new Callback<DividaCreate>() {
            @Override
            public void onResponse(Call<DividaCreate> call, Response<DividaCreate> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "verificar o link na interface");
                }
                else
                    Log.i("Sucesso", "Divida Criada com Sucesso");
            }

            @Override
            public void onFailure(Call<DividaCreate> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
    }

    private void updateBDPassageiroComparecenciaPagamento(int comparecimento, int pagamento) {
        Passageiro passageiro = new Passageiro();
        passageiro.setComparencia_viagem(comparecimento);
        passageiro.setPagou_viagem(pagamento);
        passageiro.setId_viagem(idViagem);
        passageiro.setCidadao_id_utilizador(user_id);

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<Passageiro> call = baseDadosInterface.executeUpdatePassageiro(passageiro);

        call.enqueue(new Callback<Passageiro>() {
            @Override
            public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "verificar o link na interface");
                }
                if (response.body() != null) {
                    Log.i("Sucesso", "Update Feito, "+ comparecimento + ", " + pagamento);
                }else
                    Log.i("Erro", "L105 viagens efetuadas");
            }

            @Override
            public void onFailure(Call<Passageiro> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
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
                Intent voltarMenu = new Intent(pagamentoCondutor.this, menu_motorista.class);
                voltarMenu.putExtra("user_id", user_id);
                startActivity(voltarMenu);
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();
    }







}
