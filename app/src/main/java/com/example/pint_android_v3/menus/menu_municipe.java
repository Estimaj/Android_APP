package com.example.pint_android_v3.menus;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral_pro;
import com.example.pint_android_v3.perfis.perfil_cliente;
import com.example.pint_android_v3.perfis.perfil_motorista;
import com.example.pint_android_v3.pesquisar_utilizador;
import com.example.pint_android_v3.viagens_efetuadas.viagens_efetuadas;
import com.example.pint_android_v3.viagens_marcadas.viagens_marcadas;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class menu_municipe extends barra_lateral_pro {

    TextView Nome;
    TextView Localidade;
    ImageView btn_Perfil;
    ImageView btn_Marcadas;
    ImageView btn_Efetuadas;
    ImageView btn_Pesquisar;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);
        Bar_Settings();


        Nome = (TextView) findViewById(R.id.user_Name_menu_cliente);
        Localidade = (TextView) findViewById(R.id.user_localidade_menu_cliente);
        btn_Perfil = (ImageView) findViewById(R.id.user_Inner_menu_cliente);
        btn_Marcadas = (ImageView) findViewById(R.id.bt_Viagens_Marcadas_menu_cliente);
        btn_Efetuadas = (ImageView) findViewById(R.id.btn_Viagens_Efetuadas_menu_cliente);
        btn_Pesquisar = (ImageView) findViewById(R.id.btn_Pesquisar_Utilizador_menu_cliente);
        btn_Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Perfil();
            }
        });
        btn_Efetuadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Viagens_Efetuadas();
            }
        });
        btn_Marcadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Viagens_Marcadas();
            }
        });
        btn_Pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicar_Pesquisar();
            }
        } );

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

    public void Clicar_Perfil()
    {
        Intent Perfil = new Intent(menu_municipe.this, perfil_cliente.class);
        Perfil.putExtra("Nome", Nome.getText());
        startActivity(Perfil);

    }
    public void Clicar_Viagens_Marcadas()
    {
        Intent Viagens = new Intent(menu_municipe.this, viagens_marcadas.class);
        startActivity(Viagens);

    }

    public void Clicar_Viagens_Efetuadas()
    {
        Intent Viagens = new Intent(menu_municipe.this, viagens_efetuadas.class);
        startActivity(Viagens);

    }

    public void Clicar_Pesquisar()
    {
        Intent Pesquisar = new Intent(menu_municipe.this, pesquisar_utilizador.class);
        startActivity(Pesquisar);

    }

}
