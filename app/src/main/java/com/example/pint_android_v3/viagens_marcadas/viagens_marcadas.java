package com.example.pint_android_v3.viagens_marcadas;

import android.content.Intent;
import android.os.Bundle;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

public class viagens_marcadas extends barra_lateral_pro {

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
        Bar_Settings(id_user);
    }
}
