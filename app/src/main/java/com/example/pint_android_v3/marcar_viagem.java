package com.example.pint_android_v3;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class marcar_viagem extends barra_lateral_pro implements DatePickerDialog.OnDateSetListener{


    private TextView DateLayout_text;
    private Button btn_Marcar_Viagem;
    private TextView TimeLayout_text;


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
        String time = GetTime();
        String date = GetDate();
        Bar_Settings();

        DateLayout_text = findViewById(R.id.agenda_date_picker_text_marcar_viagem);
        btn_Marcar_Viagem = findViewById(R.id.btn_marcar_viagem_marcar_viagem);
        TimeLayout_text = findViewById(R.id.agenda_time_picker_text_marcar_viagem);
        DateLayout_text.setText(date);
        TimeLayout_text.setText(time);


        TimeLayout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        btn_Marcar_Viagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Click_Botao_marcar();
            }
        });


        DateLayout_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        Spinner coloredSpinner = findViewById(R.id.spinner_partida_marcar_viagem);
        coloredSpinner.setAdapter(adapter);
        //coloredSpinner.setOnItemSelectedListener();
    }

    private void showDatePickerDialog()
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
                        TimeLayout_text.setText(hourOfDay + ":" + minute);
                    }
                },
                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
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
        String x = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +
                "/" +
                Calendar.getInstance().get(Calendar.MONTH) +
                "/" +
                Calendar.getInstance().get(Calendar.YEAR);

        return x;


    }

    public String GetTime()
    {
        String x = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + ":" + Calendar.getInstance().get(Calendar.MINUTE);
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


    public void makeToastForMarcar(String msg){
        Toast.makeText(marcar_viagem.this, msg,
                Toast.LENGTH_LONG).show();
    }
}
