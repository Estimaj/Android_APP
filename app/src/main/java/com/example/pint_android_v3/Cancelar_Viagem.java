package com.example.pint_android_v3;

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
import android.widget.Toast;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.UpdatePassageiro.Passageiro;
import com.example.pint_android_v3.DataBase.ViagemUnica.ModelViagemUnica;
import com.example.pint_android_v3.DataBase.ViagensInformacao.dataViagem;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.menus.menu_municipe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cancelar_Viagem extends barra_lateral_pro {

    private Button cancelar_viagem_btn;
    private int user_id;
    private int idViagem;
    private int idPedido;

    private String BASE_URL ="https://pintbackend.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancelar_viagem_pro);

        cancelar_viagem_btn = findViewById(R.id.btn_cancelar_viagem_pro);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            idPedido = (int) b.get("idPedido");
            try {
                colocarValoresMaisInfo(b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        cancelar_viagem_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RetirarPassageiroDaViagem(user_id, idPedido);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        Bar_Settings(user_id);

    }


    private void colocarValoresMaisInfo(Bundle b) {
        idViagem = (int) b.get("idViagem");
        TextView Origem_txt = findViewById(R.id.local_user_textView_origem_cancelar_viagem_new);
        TextView Destino_txt = findViewById(R.id.local_user_textView_cancelar_viagem_new);
        TextView Horas_txt = findViewById(R.id.local_user_textView_hora_cancelar_viagem_new);
        TextView Dia_txt = findViewById(R.id.local_user_textView_dia_cancelar_viagem_new);
        TextView Total_a_pagar = findViewById(R.id.total_textview_cancelar_viagem_new);

        String localPartida = (String) b.get("localPartida");
        String localChegada = (String) b.get("localChegada");

        Origem_txt.setText(localPartida);
        Destino_txt.setText(localChegada);
        Total_a_pagar.setText("" + (String) b.get("valorViagem"));

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
                dialogBuilder.dismiss();
                Intent voltarMenu = new Intent(Cancelar_Viagem.this, menu_municipe.class);
                voltarMenu.putExtra("user_id", user_id);
                startActivity(voltarMenu);
            }
        });
        dialogBuilder.show();
    }
    private void RetirarPassageiroDaViagem(int id, int idPedido) throws ParseException {
        if(!VerificarDiaHoraCurrent()){
            Toast.makeText(Cancelar_Viagem.this,
                    "Não é possivel cancelar devido as horas/data",
                    Toast.LENGTH_LONG).show();
            return;
        }

        HashMap<String, Integer> map = new HashMap<>();

        map.put("id", idPedido);
        map.put("cancele", 1);
        map.put("idCidadao", id);

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<Passageiro> call = baseDadosInterface.executeUpdatePedidoDeletePassageiro(map);


        call.enqueue(new Callback<Passageiro>() {
            @Override
            public void onResponse(Call<Passageiro> call, Response<Passageiro> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "Erro a ir ao link");
                }
                if (response.code() == 200){
                    Log.i("Sucesso", "passageiro retirado da viagem");
                    Mostrar_Pop();
                }
            }

            @Override
            public void onFailure(Call<Passageiro> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
    }

    private boolean VerificarDiaHoraCurrent() throws ParseException {
        TextView Horas_txt = findViewById(R.id.local_user_textView_hora_cancelar_viagem_new);
        TextView Day_txt = findViewById(R.id.local_user_textView_dia_cancelar_viagem_new);
        SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm");
        Date currentTimeDate = new Date(System.currentTimeMillis());
        String currentTimeString = dayFormatter.format(currentTimeDate);
        String viagemTimeString = Day_txt.getText().toString();

        Date Current = dayFormatter.parse(currentTimeString);
        Date Viagem = dayFormatter.parse(viagemTimeString);

        if (Current.before(Viagem)) {
            if((Current.getDay() + 1) == Viagem.getDay()){
                Date horamax = hourFormatter.parse("17:00");
                Date horaCurrent = hourFormatter.parse(hourFormatter.format(currentTimeDate));
                if(horaCurrent.before(horamax)){
                    return true;
                }
                else //se passou da hora
                    return false;
            }
            else //se tem mais que um dia de diferenca
                return true;
        }
        else //se a data ja passou, na teorica n devia precisar pq n devia haver viagens marcadas com datas depois da corrent
            return false;
    }
}
