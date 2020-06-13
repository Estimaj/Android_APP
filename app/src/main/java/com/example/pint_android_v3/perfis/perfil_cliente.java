package com.example.pint_android_v3.perfis;

import android.content.Intent;
import android.os.Bundle;
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
    TextView Nome;
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
        Intent I = getIntent();
        Bundle b = I.getExtras();
        Nome = findViewById(R.id.User_Name);
        if(b!=null)
        {
            String j =(String) b.get("Nome");
            Nome.setText(j);

        }
        Bar_Settings();
    }

    public void Go_Back()
    {
        Intent GO = new Intent(perfil_cliente.this, menu_municipe.class);
        startActivity(GO);

    }

}