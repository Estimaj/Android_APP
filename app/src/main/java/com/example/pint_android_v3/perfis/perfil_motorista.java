package com.example.pint_android_v3.perfis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.menus.menu_municipe;

public class perfil_motorista extends AppCompatActivity {

    TextView Nome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_motorista);
        Button X = (Button) findViewById(R.id.button_back_arrow_black_perfil_motorista);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Back();
            }
        });
        Intent I = getIntent();
        Bundle b = I.getExtras();
        Nome = (TextView) findViewById(R.id.User_Name);
        if(b!=null)
        {
            String j =(String) b.get("Nome");
            Nome.setText(j);

        }
    }

    public void Go_Back()
    {
        Intent GO = new Intent(perfil_motorista.this, menu_municipe.class);
        startActivity(GO);

    }

}
