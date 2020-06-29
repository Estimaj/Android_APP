package com.example.pint_android_v3;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.menus.menu_motorista;
import com.example.pint_android_v3.menus.menu_municipe;
import com.example.pint_android_v3.perfis.perfil_cliente;
import com.example.pint_android_v3.perfis.perfil_motorista;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class barra_lateral_condutor extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private int user_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barra_lateral_pro);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id)
        {
            case R.id.LogOut_btn_menu:
                Intent logOut = new Intent(this, MainActivity_Login.class);
                logOut.putExtra("user_id", user_id);
                startActivity(logOut);
                break;
            case R.id.User_btn_menu:
                Intent perfilUser = new Intent(this, perfil_motorista.class);
                perfilUser.putExtra("user_id", user_id);
                startActivity(perfilUser);
                break;
            case R.id.Home_btn_menu:
                Intent homePage = new Intent(this, menu_motorista.class);
                homePage.putExtra("user_id", user_id);
                startActivity(homePage);
                break;
            //Criar activity Notificações
            //Default?
            //Trocar perfil_motorista por municipe



        }
        return false;

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }





    public void Bar_Settings(int id)
    {
        user_id = id;
        toolbar = findViewById(R.id.barra_lateral_toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);


        drawerLayout = findViewById(R.id.barra_lateral_drawer_layout);
        final int heightX = drawerLayout.getLayoutParams().height;
        navigationView = findViewById(R.id.nav_view_barra);

        //Vai buscar o menu do navigation drawer
        Menu menu = navigationView.getMenu();

        //Altera a cor do separador opções de viagem do navigation drawer
        MenuItem tools= menu.findItem(R.id.op_menu_cliente_barra);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        tools.setTitle(s);


        //Altera a cor do separador sair do navigation drawer
        tools= menu.findItem(R.id.sair_menu_cliente_barra);
        s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.TextAppearance44), 0, s.length(), 0);
        tools.setTitle(s);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, toolbar, R.string.openNavDrawer, R.string.closeNavDrawer)
        {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                drawerLayout.getLayoutParams().height = toolbar.getLayoutParams().height;
                drawerLayout.requestLayout();



            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerLayout.getLayoutParams().height = heightX;
                drawerLayout.bringToFront();
                drawerLayout.requestLayout();



            }

            public void onDrawerStateChanged(int newState)
            {
                drawerLayout.requestLayout();


            }


        };


        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.onDrawerClosed(drawerLayout);
        actionBarDrawerToggle.syncState(); //animação do hamburguer muda com isto
        navigationView.setNavigationItemSelectedListener(this);


    }




}
