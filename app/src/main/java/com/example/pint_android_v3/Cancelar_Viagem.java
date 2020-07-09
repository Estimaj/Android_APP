package com.example.pint_android_v3;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

public class Cancelar_Viagem extends barra_lateral_pro {

    private Button cancelar_viagem_btn;
    private int user_id;
    private int idViagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_viagem_pro);

        cancelar_viagem_btn = findViewById(R.id.btn_cancelar_viagem_pro);


        cancelar_viagem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mostrar_Pop(); //adicionar o codigo relativo a bd, aparecer o SucessoDialog se ele tiver sido apagado corretamente
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            try {
                colocarValoresMaisInfo(b);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        Bar_Settings(user_id);

    }


    private void colocarValoresMaisInfo(Bundle b) {
        TextView Origem_txt = findViewById(R.id.local_user_textView_origem_cancelar_viagem_new);
        TextView Destino_txt = findViewById(R.id.local_user_textView_cancelar_viagem_new);
        TextView Horas_txt = findViewById(R.id.local_user_textView_hora_cancelar_viagem_new);
        TextView Dia_txt = findViewById(R.id.local_user_textView_dia_cancelar_viagem_new);
        //TextView Taxa_desconto_txt = findViewById(R.id.local_user_textView_taxa_desconto_cancelar_viagem_new);
        TextView Total_a_pagar = findViewById(R.id.total_textview_cancelar_viagem_new);

        String localPartida = (String) b.get("localPartida");
        String localChegada = (String) b.get("localChegada");

        Origem_txt.setText(localPartida);
        Destino_txt.setText(localChegada);
        Total_a_pagar.setText("" + (String) b.get("valorViagem"));
        idViagem = (int) b.get("idViagem");

        Horas_txt.setText((String) b.get("horaViagem"));
        Dia_txt.setText((String) b.get("dataViagem"));

        ImageView certoGone;
        if((int) b.get("bagagemPedido") == 0){
            certoGone = findViewById(R.id.mala_check_cancelar_viagem_new);
            certoGone.setVisibility(View.GONE);
        }
        if((int) b.get("animalPedido") == 0){
            certoGone = findViewById(R.id.canideo_check_cancelar_viagem_new);
            certoGone.setVisibility(View.GONE);
        }
        if((int) b.get("necessidadesEspeciaisPedido") == 0){
            certoGone = findViewById(R.id.wheel_check_cancelar_viagem_new);
            certoGone.setVisibility(View.GONE);
        }




    }

    public void Mostrar_Pop()
    {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cancelar_viagem_pop_up_sucesso, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter_cancelar_viagem_popup);
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
