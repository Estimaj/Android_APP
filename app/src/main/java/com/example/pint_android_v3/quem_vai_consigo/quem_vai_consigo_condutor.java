package com.example.pint_android_v3.quem_vai_consigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.pagamentoCondutor;

public class quem_vai_consigo_condutor extends barra_lateral_pro {

    private int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_vai_consigo_condutor);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }

        Bar_Settings(user_id);


    }

    public void Click_Pagamento(View view)
    {
        Intent pagamento = new Intent( quem_vai_consigo_condutor.this ,pagamentoCondutor.class);
        pagamento.putExtra("user_id", user_id);
        startActivity(pagamento);



    }


}
