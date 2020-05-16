package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class viagens_marcadas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_marcadas);
        Button X = (Button) findViewById(R.id.button_back_arrow_black_viagens_marcadas);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Back();
            }
        });
    }

    public void Go_Back()
    {
        Intent GO = new Intent(viagens_marcadas.this, menu_municipe.class);
        startActivity(GO);

    }
}
