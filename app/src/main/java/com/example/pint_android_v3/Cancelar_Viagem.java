package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pint_android_v3.menus.menu_municipe;

public class Cancelar_Viagem extends AppCompatActivity {

    private Button cancelar_viagem_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar__viagem);

        Button X_btn = findViewById(R.id.cancelar_X_botton_cancelar_viagem);
        cancelar_viagem_btn = findViewById(R.id.cancelar_cancelar_viagem_botton_cancelar_viagem);
        TextView Origem_txt = findViewById(R.id.textView_user_location_information_cancelar_viagem);
        TextView Destino_txt = findViewById(R.id.local_user_textView_destino_viagem_cancelar_viagem);
        TextView Horas_txt = findViewById(R.id.local_user_textView_hora_viagem_cancelar_viagem);
        TextView Dia_txt = findViewById(R.id.local_user_textView_dia_viagem_cancelar_viagem);
        TextView Taxa_desconto_txt = findViewById(R.id.local_user_textView_taxa_desconto_viagem_cancelar_viagem);
        TextView Distancia_txt = findViewById(R.id.local_user_textView_distancia_viagem_cancelar_viagem);
        TextView Total_a_receber = findViewById(R.id.local_user_textView_total_a_receber_viagem_cancelar_viagem);

        cancelar_viagem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSucessoDialog(); //adicionar o codigo relativo a bd, aparecer o SucessoDialog se ele tiver sido apagado corretamente
            }
        });

        X_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Cancelar_Viagem.this, viagens_marcadas.class));
            }
        });
    }

    private void showSucessoDialog(){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.cancelar_viagem_pop_up_sucesso, null);
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_cancelar_viagem_pop_up_sucesso);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Cancelar_Viagem.this, "yes",Toast.LENGTH_LONG).show();
                dialogBuilder.setCancelable(true);
            }
        });

        dialogBuilder.create();
        dialogBuilder.show();
    }
}
