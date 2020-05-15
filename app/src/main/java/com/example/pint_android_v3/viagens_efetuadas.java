package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viagens_efetuadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_efetuadas);
        Button X = (Button) findViewById(R.id.button_back_arrow_black_viagens_efetuadas);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Back();
            }
        });
    }

    public void Go_Back()
    {
        Intent GO = new Intent(viagens_efetuadas.this, menu_municipe.class);
        startActivity(GO);

    }
}
