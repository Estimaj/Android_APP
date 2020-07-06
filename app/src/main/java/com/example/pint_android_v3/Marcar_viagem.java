package com.example.pint_android_v3;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.Pedido_Viagem;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.menus.menu_municipe;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Marcar_viagem extends barra_lateral_pro implements DatePickerDialog.OnDateSetListener{

    private String BASE_URL ="http://10.0.2.2:3000";

    private TextView DateLayout_text;
    private TextView TimeLayout_text;
    private Button btn_Marcar_Viagem;

    private Switch switch_pet;
    private Switch switch_necessidades_especiais;
    private Switch switch_bagagem;
    private RadioGroup radioGroup_partilha;


    Spinner spinner_destino;
    Spinner spiner_partida;

    private int user_id;
    private int local_origem_pedido;
    private int local_destino_pedido;
    //1-yes 0-no
    private int animal;
    private int necessidades_especiais;
    private int bagagem;
    private int partilha = 1;
    private int modalidade = 0; // 0-ida, 1-ida e volta


    //...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_viagem);

        String time = GetTime();
        String date = GetDate();

        DateLayout_text = findViewById(R.id.agenda_date_picker_text_marcar_viagem);
        btn_Marcar_Viagem = findViewById(R.id.btn_marcar_viagem_marcar_viagem);
        TimeLayout_text = findViewById(R.id.agenda_time_picker_text_marcar_viagem);
        DateLayout_text.setText(date);
        TimeLayout_text.setText(time);

        //Relogio
        TimeLayout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        //calendario
        DateLayout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        //botao final aka- marcar_viagem
        btn_Marcar_Viagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_Botao_marcar();
            }
        });


        //meti aqui os switchs todos
        switchsResumidos();





        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }
        Bar_Settings(user_id);
    }

    private void switchsResumidos(){
        switch_pet = findViewById(R.id.dog_switch_marcar_viagem);
        switch_pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_pet.isChecked()){
                    Log.i("check", "on");
                    animal = 1;
                }else {
                    Log.i("check", "off");
                    animal = 0;
                }
            }
        });

        switch_bagagem = findViewById(R.id.mala_switch_marcar_viagem);
        switch_bagagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_bagagem.isChecked()){
                    //Log.i("check", "on");
                    bagagem = 1;
                }else {
                    //Log.i("check", "off");
                    bagagem = 0;
                }
            }
        });

        switch_necessidades_especiais = findViewById(R.id.wheel_switch_marcar_viagem);
        switch_necessidades_especiais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_necessidades_especiais.isChecked()){
                    //Log.i("check", "on");
                    necessidades_especiais = 1;
                }else {
                    //Log.i("check", "off");
                    necessidades_especiais = 0;
                }
            }
        });

        radioGroup_partilha = findViewById(R.id.partilha_radio_group_marcar_viagem);
        radioGroup_partilha.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_com_partilha_marcar_viagem:{
                        partilha = 1;
                        break;
                    }
                    case R.id.radio_sem_partilha_marcar_viagem:{
                        partilha=0;
                        break;
                    }
                    default:{
                        makeToastForMarcar("Erro no radiogroup!!");
                        break;
                    }
                }
            }
        });

        ArrayAdapter adapterViagens = ArrayAdapter.createFromResource(
                this,
                R.array.Spinner_items,
                R.layout.color_spinner_layout
        );
        adapterViagens.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        //Log.i("adapter", adapter.getPosition().toString());
        spiner_partida = findViewById(R.id.spinner_partida_marcar_viagem);
        spiner_partida.setAdapter(adapterViagens);

        spiner_partida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                local_origem_pedido = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        spinner_destino = findViewById(R.id.spinner_destino_marcar_viagem);
        spinner_destino.setAdapter(adapterViagens); //mm adapter que o de partida
        spinner_destino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                local_destino_pedido = position + 1;
                Log.i("teste", ""+ local_destino_pedido);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
    }

    private void showDatePickerDialog() //temos que ter em atençao que nos n vamos sempre partir do mm ponto se o utilizador abrir e escolher uma data, quando abrir era porreiro ter essa data
    {
        //Log.i("passei" , "passei aqui.....");
        DatePickerDialog DPD = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        DPD.show();

    }

    private void showTimePickerDialog()
    {
        TimePickerDialog TPD = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String hora = ""+hourOfDay, min = ""+minute;
                        if(hourOfDay < 10){
                            hora = "0" + hourOfDay;
                        }
                        if(minute < 10){
                            min = "0"+ minute;
                        }
                        TimeLayout_text.setText(hora + ":" + min);
                    }
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +1,
                Calendar.getInstance().get(Calendar.MINUTE),
                true

        );
        TPD.show();

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month += 1;
        String date = dayOfMonth + "/" + month + "/" +  year;
        DateLayout_text.setText(date);
    }

    public String GetDate()
    {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String x = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +
                "/" +
                 month +
                "/" +
                Calendar.getInstance().get(Calendar.YEAR);

        return x;


    }

    public String GetTime()
    {
        int nhora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY )+ 1, nMin = Calendar.getInstance().get(Calendar.MINUTE);
        String hora = ""+ nhora, min = ""+ nMin;
        if(nhora < 10){
            hora = "0" + nhora;
        }
        if(nMin < 10){
            min = "0"+ nMin;
        }
        return hora + ":" + min;
    }

    public void Click_Botao_marcar()
    {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_marcar_viagem_adapter, null);
        dialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter);
        ImageView no_btn = dialogView.findViewById(R.id.no_btn_alert_dialog_adapter);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToastForMarcar("yes");
                criarPedido_viagem();
                //mudar de intent
                Intent Viagens = new Intent(Marcar_viagem.this, menu_municipe.class);
                Viagens.putExtra("user_id", user_id);
                startActivity(Viagens);
            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.show();
    }

    private void criarPedido_viagem(){
        //o cancelar é suposto estar a 0, falta o locals e partilha
        if(user_id == 0){
            Log.i("user_id_error", "user id = 0, func criarPedido_viagem, marcar_viagem.java");
            return;
        }
        Pedido_Viagem pedido_viagem = new Pedido_Viagem(1, user_id,
                local_origem_pedido, local_destino_pedido, bagagem, modalidade, partilha, animal,
                necessidades_especiais, 0, GetTime(), GetDate(), DateLayout_text.getText().toString(),
                TimeLayout_text.getText().toString());
        Log.i("Pedido", pedido_viagem.toString());

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<Pedido_Viagem> call = baseDadosInterface.executeCriarPedidoViagem(pedido_viagem);

        call.enqueue(new Callback<Pedido_Viagem>() {
            @Override
            public void onResponse(Call<Pedido_Viagem> call, Response<Pedido_Viagem> response) {
                if (!response.isSuccessful()){
                    makeToastForMarcar("Erro a ir ao link");
                }
                else{
                    if(response.body() != null) {
                        //makeToastForMarcar("Penso eu que devia haver um novo pedido");
                        Log.i("Pedido", response.body().toString());
                    }else{
                        makeToastForMarcar("ocurreu um erro na criaçao da sua viagem!");
                    }
                }
            }

            @Override
            public void onFailure(Call<Pedido_Viagem> call, Throwable t) {
                Log.i("onFailure:", t.toString());
                makeToastForMarcar("Failure: "+ t.toString());
            }
        });

    }


    public void makeToastForMarcar(String msg){
        Toast.makeText(Marcar_viagem.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
