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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Marcar_viagem extends barra_lateral_pro implements DatePickerDialog.OnDateSetListener{

    private String BASE_URL ="https://pintbackend.herokuapp.com";

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showDatePickerDialog() //temos que ter em atençao que nos n vamos sempre partir do mm ponto se o utilizador abrir e escolher uma data, quando abrir era porreiro ter essa data
    {
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
        String monthString = ""+month, dayString = ""+dayOfMonth;
        if(month < 10){
            monthString = "0"+monthString;
        }
        if (dayOfMonth < 10){
            dayString = "0"+ dayString;
        }
        String date = year + "-" +  monthString + "-" + dayString;
        DateLayout_text.setText(date);
    }

    public String GetDate()
    {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        String monthString = ""+ month, dayString = ""+Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if(month < 10){
            monthString = "0" + monthString;
        }
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < 10){
            dayString = "0"+ dayString;
        }

        String x =
                Calendar.getInstance().get(Calendar.YEAR) +
                "-" +
                 monthString +
                "-" +
                dayString;

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
                //se o id e a hora estivirem bem o pedido segue
                if(criarPedido_viagem()){
                    //mudar de intent
                    Intent Viagens = new Intent(Marcar_viagem.this, menu_municipe.class);
                    Viagens.putExtra("user_id", user_id);
                    startActivity(Viagens);
                }
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

    private boolean criarPedido_viagem(){
        //Confirmação de dados para exetuar o pedido
        if(user_id == 0){
            Log.i("user_id_error", "user id = 0, func criarPedido_viagem, marcar_viagem.java");
            return false;
        }
        if(local_destino_pedido == local_origem_pedido){
            makeToastForMarcar("Não é possivel ter destinos semelhantes");
            return false;
        }
        String currentTime = GetTime();
        String currentDate = GetDate();
        try {
            if (!VerificarHoraCurrent(currentTime, currentDate)){
                makeToastForMarcar("Impossivel marcar para esse dia");
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Pedido_Viagem pedido_viagem = new Pedido_Viagem(1, user_id,
                local_origem_pedido, local_destino_pedido, bagagem, modalidade, partilha, animal,
                necessidades_especiais, 0, "" + currentTime, currentDate, DateLayout_text.getText().toString(),
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
                    Log.i("Pedido", response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Pedido_Viagem> call, Throwable t) {
                Log.i("onFailure:", t.toString());
                makeToastForMarcar("Failure: "+ t.toString());
            }
        });

        return true;
    }

    private boolean VerificarHoraCurrent(String currentTime, String currentDate) throws ParseException {
        SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String diaViagem = DateLayout_text.getText().toString();

        Date Current = dayFormatter.parse(currentDate);
        Date Viagem = dayFormatter.parse(diaViagem);

        if (Current.before(Viagem)) {
            if((Current.getDay() + 1) == Viagem.getDay()){
                Date horaCurrent = hourFormatter.parse(currentTime);
                Date horamax = hourFormatter.parse("17:00");
                if (horaCurrent.before(horamax)){
                    return true;
                }
            }
            else
                return true;
        }

        return false;
    }

    public void makeToastForMarcar(String msg){
        Toast.makeText(Marcar_viagem.this, msg,
                Toast.LENGTH_LONG).show();
    }
}

