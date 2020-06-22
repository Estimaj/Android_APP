package com.example.pint_android_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pint_android_v3.menus.menu_motorista;
import com.example.pint_android_v3.menus.menu_municipe;

public class desambiguacao extends AppCompatActivity {

    Button login_mun;
    Button login_mot;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desambiguacao);
        TextView Mail = (EditText) findViewById(R.id.user_login_text_desambiguacao);
        TextView Password = (EditText) findViewById(R.id.user_password_text_desambiguacao);


        Intent I= getIntent();
        Bundle b = I.getExtras();

        if(b!=null)
        {
            id = (int) b.get("user_id");
            //Log.i("user_id:", ""+ id);
            String j =(String) b.get("User_Login");
            Mail.setText(j);
            j = (String) b.get("Password_Login");
            Password.setText(j);
        }

        login_mun = (Button) findViewById(R.id.entrar_desambiguacao);
        login_mun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu_Go_Cliente();
            }
        });
        login_mot = (Button) findViewById(R.id.Entrar_Motorista_desambiguacao);
        login_mot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Menu_Go_Motorista();
            }
        });
    }

    public void Menu_Go_Motorista()
    {
        Intent Menu_Intent = new Intent(desambiguacao.this, menu_motorista.class);
        Menu_Intent.putExtra("user_id", id);
        startActivity(Menu_Intent);
    }

    public void Menu_Go_Cliente()
    {
        Intent Menu_Intent_Municipe = new Intent(desambiguacao.this, menu_municipe.class);
        Menu_Intent_Municipe.putExtra("user_id", id);
        startActivity(Menu_Intent_Municipe);
    }

}
