package com.example.pint_android_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.menus.menu_municipe;
import com.google.android.material.navigation.NavigationView;

public class pesquisar_utilizador extends barra_lateral_pro {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create pesquisa utilizador");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_utilizador);

        toolbar = findViewById(R.id.barra_lateral_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.barra_lateral_drawer_layout);
        navigationView = findViewById(R.id.nav_view_barra);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.openNavDrawer, R.string.closeNavDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




    }

    public void Go_Back()
    {
        Intent GO = new Intent(pesquisar_utilizador.this, menu_municipe.class);
        startActivity(GO);

    }





}
