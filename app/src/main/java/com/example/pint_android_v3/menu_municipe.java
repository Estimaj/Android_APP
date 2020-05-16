package com.example.pint_android_v3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class menu_municipe extends AppCompatActivity {

    TextView Nome;
    TextView Localidade;
    ImageView btn_Perfil;
    ImageView btn_Marcadas;
    ImageView btn_Efetuadas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("oncreate:", "on create Menu");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_cliente);
        Nome = (TextView) findViewById(R.id.user_Name);
        Localidade = (TextView) findViewById(R.id.user_localidade);
        btn_Perfil = (ImageView) findViewById(R.id.user_Inner);
        btn_Marcadas = (ImageView) findViewById(R.id.bt_Viagens_Marcadas);
        btn_Efetuadas = (ImageView) findViewById(R.id.btn_Viagens_Efetuadas);
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
        Intent Perfil = new Intent(menu_municipe.this, perfil_motorista.class);
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

}
