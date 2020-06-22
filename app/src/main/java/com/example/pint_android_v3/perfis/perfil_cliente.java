package com.example.pint_android_v3.perfis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral_pro;
import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.navigation.NavigationView;

public class perfil_cliente extends barra_lateral_pro {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TextView Nome, Origem, Idade, Telefone, Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_motorista);
        /*Button X = (Button) findViewById(R.id.button_back_arrow_black_perfil_motorista);
        X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go_Back();
            }
        });*/
        Nome = findViewById(R.id.User_Name);
        Origem = findViewById(R.id.textView_user_location_information_perfil_motorista);
        Idade = findViewById(R.id.textView_user_idade_information_perfil_motorista);
        Telefone = findViewById(R.id.textView_user_telefone_information_perfil_motorista);
        Email= findViewById(R.id.textView_user_email_information_perfil_motorista);

        Intent I = getIntent();
        Bundle b = I.getExtras();

        if(b!=null)
        {
            Log.i("---", "-----------------");
            String j =(String) b.get("Nome");
            Nome.setText(j);
            Log.i("---", ""+ j);
            j =(String) b.get("Origem");
            Origem.setText(j);
            j =(String) b.get("Idade");
            Idade.setText(j);
            j =(String) b.get("Telefone");
            Telefone.setText(j);
            j =(String) b.get("Email");
            Email.setText(j);
        }
        Bar_Settings();
    }

    public void Go_Back()
    {
        Intent GO = new Intent(perfil_cliente.this, menu_municipe.class);
        startActivity(GO);
    }

}