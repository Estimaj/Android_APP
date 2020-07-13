package com.example.pint_android_v3;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.google.android.material.navigation.NavigationView;

public class pesquisar_utilizador extends barra_lateral_pro {//nao implementado!!

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create pesquisa utilizador");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_utilizador);

       //Bar_Settings();
    }
}
