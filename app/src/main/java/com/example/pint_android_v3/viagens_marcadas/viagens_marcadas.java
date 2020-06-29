package com.example.pint_android_v3.viagens_marcadas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.menus.menu_municipe;

public class viagens_marcadas extends AppCompatActivity {

    private int id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_marcadas);


        Intent I = getIntent();
        Bundle b = I.getExtras();
        if(b!=null){
            id_user = (int) b.get("user_id");
        }
    }


        }
