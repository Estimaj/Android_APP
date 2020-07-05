package com.example.pint_android_v3;

import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class pagamentoCondutor extends barra_lateral_pro {

    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarifa_condutor);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }

        Bar_Settings(user_id);




    }







}
