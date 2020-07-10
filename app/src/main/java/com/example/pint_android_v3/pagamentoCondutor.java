package com.example.pint_android_v3;

import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.menus.menu_motorista;
import com.example.pint_android_v3.menus.menu_municipe;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class pagamentoCondutor extends barra_lateral_condutor {

    private int user_id;
    private int idViagem;

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

        }

        Bar_Settings(user_id);




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
