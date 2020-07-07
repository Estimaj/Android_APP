package com.example.pint_android_v3.servico_decorrer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.mapas.mais_info_mapa_condutor;
import com.example.pint_android_v3.viagens_efetuadas_condutor.CustomListAdapter_efetuadas_condutor;
import com.example.pint_android_v3.viagens_efetuadas_condutor.viagens_efetuadas_condutor;
import com.example.pint_android_v3.viagens_efetuadas_condutor.viagens_efetuadas_condutor_array;

public class servico_a_decorrer extends barra_lateral_condutor {

    private int user_id;
    ListView lView;
    ListAdapter lAdapter;
    ServicoDecorrerArray lItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sevico_a_decorrer);

        lItems = new ServicoDecorrerArray();
        lView = (ListView)findViewById(R.id.serviços_decorrer_listview);
        lAdapter = new CustomAdapterServicoDecorrer(servico_a_decorrer.this,
                lItems.data,
                lItems.Local_Chegada, lItems.Local_Partida,lItems.hora);

        lView.setAdapter(lAdapter);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            //Log.i("id_user", ""+ id_user);

        }
        Bar_Settings(user_id);




    }

    public void Mais_Info_Condutor_Servico(View view)
    {
        Intent GO = new Intent(servico_a_decorrer.this, mais_info_mapa_condutor.class);
        GO.putExtra("user_id", user_id);
        startActivity(GO);


    }
}
