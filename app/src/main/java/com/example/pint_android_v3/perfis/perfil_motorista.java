package com.example.pint_android_v3.perfis;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;
import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.navigation.NavigationView;

public class perfil_motorista extends barra_lateral_condutor {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView Nome;
    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_motorista);

        Intent I = getIntent();
        Bundle b = I.getExtras();
        Nome = (TextView) findViewById(R.id.User_Name);
        if(b!=null)
        {
            user_id = (int) b.get("user_id");
        }
        Bar_Settings(user_id);
    }

    public void Go_Back()
    {
        Intent GO = new Intent(perfil_motorista.this, menu_municipe.class);
        startActivity(GO);

    }

}
