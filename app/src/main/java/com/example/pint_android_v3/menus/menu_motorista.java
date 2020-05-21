package com.example.pint_android_v3.menus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pint_android_v3.R;

import java.util.Locale;

public class menu_motorista extends AppCompatActivity {

    TextView Nome;
    TextView Localidade;
    ImageView btn_Perfil;
    ImageView btn_Efetuadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_motorista);
        Nome = (TextView) findViewById(R.id.activity_menu_motorista_user_Name);
        Localidade = (TextView) findViewById(R.id.activity_menu_motorista_user_localidade);
        btn_Perfil = (ImageView) findViewById(R.id.activity_menu_motorista_user_Inner);
        btn_Efetuadas = (ImageView) findViewById(R.id.activity_menu_motorista_btn_Viagens_Efetuadas);

        Intent X = getIntent();
        Bundle b = X.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("Nome");
            Nome.setText(j);
            j = (String) b.get("Localidade");
            Localidade.setText(j);
        }
    }



}