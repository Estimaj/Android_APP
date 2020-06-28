package com.example.pint_android_v3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.Model;
import com.example.pint_android_v3.DataBase.Pedido_Viagem;
import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class marcar_viagem extends barra_lateral_pro implements DatePickerDialog.OnDateSetListener{

    private String BASE_URL ="http://10.0.2.2:3000";

    private TextView DateLayout_text;
    private TextView TimeLayout_text;
    private Button btn_Marcar_Viagem;

    private Switch switch_pet;
    private Switch switch_necessidades_especiais;
    private Switch switch_bagagem;
    private RadioGroup radioGroup_partilha;

    Spinner spinner;

    private int id_user;
    int local_origem_pedido;
    int local_destino_pedido;
    //1-yes 0-no
    int animal;
    int necessidades_especiais;
    int bagagem;
    int partilha;
    int modalidade = 0; // 0-ida, 1-ida e volta


    //...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcar_viagem);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.Spinner_items,
                R.layout.color_spinner_layout
        );
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        //Log.i("adapter", adapter.getPosition().toString());
        String time = GetTime();
        String date = GetDate();
        Bar_Settings();

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


        Spinner coloredSpinner = findViewById(R.id.spinner_partida_marcar_viagem);
        coloredSpinner.setAdapter(adapter);

        //meti aqui os switchs todos
        switchsResumidos();

        spinner = findViewById(R.id.spinner_destino_marcar_viagem);



        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            id_user = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);


        }
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

        //falta implementar o radio!! e uma das options vai ter que sair xD

    }

    private void showDatePickerDialog() //temos que ter em atençao que nos n vamos sempre partir do mm ponto se o utilizador abrir e escolher uma data, quando abrir era porreiro ter essa data
    {
        //Log.i("passei" , "passei aqui.....");
        DatePickerDialog DPD = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH) + 1,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        DPD.show();

    }

    private void showTimePickerDialog()
    {
        Log.i("passei" , "passei aqui.....");
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

        String date = dayOfMonth + "/" + month + "/" +  year;
        DateLayout_text.setText(date);


    }

    public String GetDate()
    {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1; //luis? pq q aqui tb dava um mes a menos
        String x = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +
                "/" +
                 month +
                "/" +
                Calendar.getInstance().get(Calendar.YEAR);

        return x;


    }

    public String GetTime()
    {
        int hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + 1; //luis? pq q isto devolvia uma hora a menos?
        String x = hora + ":" + Calendar.getInstance().get(Calendar.MINUTE);
        //Log.i("valor do getTime", x);
        return x;

    }

    public void Click_Botao_marcar_alert()
    {
        makeToastForMarcar("not available yet");
        AlertDialog.Builder AlertMarcarViagem = new AlertDialog.Builder(marcar_viagem.this);
        //AlertMarcarViagem.setTitle("")
        AlertMarcarViagem.setMessage("Tem a certeza que pretende marcar a viagem?");
        AlertMarcarViagem.setPositiveButton("Marcar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makeToastForMarcar("Marcada");
            }
        });
        AlertMarcarViagem.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                makeToastForMarcar("Não marcada");
            }
        });
        AlertMarcarViagem.show();

    }

    public void Click_Botao_marcar()
    {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
// ...Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alertdialog_marcar_viagem_adapter, null);
        dialogBuilder.setView(dialogView);
        ImageView yes_btn = dialogView.findViewById(R.id.yes_btn_alert_dialog_adapter);
        ImageView no_btn = dialogView.findViewById(R.id.no_btn_alert_dialog_adapter);
        yes_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToastForMarcar("yes");
                criarPedido_viagem();
                startActivity(new Intent(marcar_viagem.this, menu_municipe.class));
            }
        });
        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeToastForMarcar("no");
                //v.setVisibility(View.GONE);
                dialogBuilder.setCancelable(true);

            }
        });


        dialogBuilder.create();
        dialogBuilder.show();



    }

    private void criarPedido_viagem(){
        //o cancelar é suposto estar a 0, falta o locals e partilha
        if(id_user == 0){
            Log.i("user_id_error", "user id = 0, func criarPedido_viagem, marcar_viagem.java");
            return;
        }
        Pedido_Viagem pedido_viagem = new Pedido_Viagem(1, id_user,
                0, 0, bagagem, modalidade, 0, animal,
                necessidades_especiais, 0, GetTime(), GetDate(), DateLayout_text.getText().toString(),
                TimeLayout_text.getText().toString());

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
                        makeToastForMarcar("Penso eu que devia haver um novo pedido");
                        response.body().toString();
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
        Toast.makeText(marcar_viagem.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
