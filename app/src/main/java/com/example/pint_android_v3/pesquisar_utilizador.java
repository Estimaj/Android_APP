package com.example.pint_android_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class pesquisar_utilizador extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create pesquisa utilizador");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_utilizador);
        Button X = (Button) findViewById(R.id.button_back_arrow_black_pesquisar_utilizador);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Back();
            }
        });

    }

    public void Go_Back()
    {
        Intent GO = new Intent(pesquisar_utilizador.this, menu_municipe.class);
        startActivity(GO);

    }





}
