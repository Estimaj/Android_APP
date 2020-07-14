package com.example.pint_android_v3.classificar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

public class classificar_condutor extends barra_lateral_pro {//nao implementado!!

    private int user_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificar_condutor);
        final RatingBar ratingBar = findViewById(R.id.rating_classificar_condutor);
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "Rating is :" + ratingBar.getRating();
                Toast.makeText(classificar_condutor.this, rating, Toast.LENGTH_LONG).show();
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
        }
        Bar_Settings(user_id);
}}
