package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class desambiguacao extends AppCompatActivity {

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desambiguacao);
        TextView Mail = (EditText) findViewById(R.id.user_login_text_desambiguacao);
        TextView Password = (EditText) findViewById(R.id.user_password_text_desambiguacao);
        login = (Button) findViewById(R.id.entrar_desambiguacao);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu_Go();
            }
        });

        Intent I= getIntent();
        Bundle b = I.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("User_Login");
            Mail.setText(j);
            j = (String) b.get("Password_Login");
            Password.setText(j);
        }
    }

    public void Menu_Go()
    {
        Intent Menu_Intent_Municipe = new Intent(desambiguacao.this, menu_municipe.class);
        Menu_Intent_Municipe.putExtra("Localidade", "Viseu");
        Menu_Intent_Municipe.putExtra("Nome", "Luis");
        startActivity(Menu_Intent_Municipe);


    }
}
