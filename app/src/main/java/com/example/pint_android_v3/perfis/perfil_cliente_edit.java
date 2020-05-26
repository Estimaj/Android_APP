package com.example.pint_android_v3.perfis;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral_pro;
import com.google.android.material.navigation.NavigationView;

public class perfil_cliente_edit extends barra_lateral_pro {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_motorista_edit);
        Bar_Settings();
    }
}
