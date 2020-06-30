package com.example.pint_android_v3.classificar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;

public class classificar_viagem extends barra_lateral_pro {


    private int user_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classificar_viagem);
        final RatingBar ratingBar = findViewById(R.id.rating_classificar_viagem);
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rating = "Rating is :" + ratingBar.getRating();
                Toast.makeText(classificar_viagem.this, rating, Toast.LENGTH_LONG).show();
            }
        });

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }
        Bar_Settings(user_id);

    }


}
