package com.example.pint_android_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class menu_municipe extends AppCompatActivity {

    TextView Nome;
    TextView Localidade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);
        Nome = (TextView) findViewById(R.id.user_Name);
        Localidade = (TextView) findViewById(R.id.user_localidade);

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
