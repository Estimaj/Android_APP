package com.example.pint_android_v3.quem_vai_consigo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.ModelListagemPassageirosCondutor;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_pro;
import com.example.pint_android_v3.pagamentoCondutor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class quem_vai_consigo_condutor extends barra_lateral_pro {

    private String BASE_URL ="http://10.0.2.2:3000";
    private int user_id;
    private int idViagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quem_vai_consigo_condutor);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
            idViagem = (int) b.get("idViagem");
            povoarViagemComPassageiros();
        }

        Bar_Settings(user_id);

    }

    private void povoarViagemComPassageiros() {
        //baseDeDadosPassageiros
        ArrayList<String> nomePassageiro = new ArrayList<>();
        ArrayList<String> localidadePassageiro = new ArrayList<>();
        try {
            Retrofit retrofit;
            BaseDadosInterface baseDadosInterface;

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

            Call<ModelListagemPassageirosCondutor> call = baseDadosInterface.executeGetListagem(user_id);

            call.enqueue(new Callback<ModelListagemPassageirosCondutor>() {
                @Override
                public void onResponse(Call<ModelListagemPassageirosCondutor> call, Response<ModelListagemPassageirosCondutor> response) {
                    if (!response.isSuccessful()) {
                        Log.i("Erro", "L69 quem_vai_consigo_condutor");
                    }
                    for (int i = 0; i < response.body().getDataListagemCondutor().size(); i++) {
                        nomePassageiro.add(response.body().getDataListagemCondutor().get(i).getNomeUtilizador());
                        localidadePassageiro.add(response.body().getDataListagemCondutor().get(i).getMoradaUtilizador());
                    }
                    adaptarFixer(nomePassageiro, localidadePassageiro);
                }

                @Override
                public void onFailure(Call<ModelListagemPassageirosCondutor> call, Throwable t) {
                    Log.i("Failure:", t.toString());
                    //makeToastFordesambiguacao("Failure: "+ t.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void adaptarFixer(ArrayList<String> nomePassageiro, ArrayList<String> localidadePassageiro) {
        TextView nomePassageiroTextView;
        TextView localPassageiroTextView;
        int contador=0;
/*
        switch (nomePassageiro.size()){ //preenche os contaners certos com informaçao
            case 4:
                contador++;
                nomePassageiroTextView = findViewById(R.id.tripulante_nome_4_quem_vai_consigo2_condutor);
                localPassageiroTextView = findViewById(R.id.tripulante_localidade_4_quem_vai_consigo2_condutor);
                nomePassageiroTextView.setText(nomePassageiro.get(4));
                localPassageiroTextView.setText(localidadePassageiro.get(4));
            case 3:
                contador++;
                nomePassageiroTextView = findViewById(R.id.tripulante_nome_3_quem_vai_consigo_condutor);
                localPassageiroTextView = findViewById(R.id.tripulante_localidade_3_quem_vai_consigo_condutor);
                nomePassageiroTextView.setText(nomePassageiro.get(3));
                localPassageiroTextView.setText(localidadePassageiro.get(3));
            case 2:
                contador++;
                nomePassageiroTextView = findViewById(R.id.tripulante_nome_2_quem_vai_consigo2_condutor);
                localPassageiroTextView = findViewById(R.id.tripulante_localidade_2_quem_vai_consigo2_condutor);
                nomePassageiroTextView.setText(nomePassageiro.get(2));
                localPassageiroTextView.setText(localidadePassageiro.get(2));
            case 1:
                contador++;
                nomePassageiroTextView = findViewById(R.id.tripulante_nome_quem_vai_consigo_condutor_1);
                localPassageiroTextView = findViewById(R.id.tripulante_localidade_1_quem_vai_consigo_condutor);
                nomePassageiroTextView.setText(nomePassageiro.get(1));
                localPassageiroTextView.setText(localidadePassageiro.get(1));
                break;
        }
        ImageView contaner;
        switch (contador){ //para apagar os que n tem informaçao
            case 0:
                contaner = findViewById(R.id.contentor1_quem_vai_consigo_condutor);
                contaner.setVisibility(View.GONE);
            case 1:
                contaner = findViewById(R.id.contentor4_quem_vai_consigo2_condutor);
                contaner.setVisibility(View.GONE);
            case 2:
                contaner = findViewById(R.id.contentor3_quem_vai_consigo_condutor);
                contaner.setVisibility(View.GONE);
            case 3:
                contaner = findViewById(R.id.contentor2_quem_vai_consigo2_condutor);
                contaner.setVisibility(View.GONE);
            default:
                break;
        }

 */
    }

    public void Click_Pagamento(View view)
    {
        Intent pagamento = new Intent( quem_vai_consigo_condutor.this ,pagamentoCondutor.class);
        pagamento.putExtra("user_id", user_id);
        pagamento.putExtra("idViagem", idViagem);
        startActivity(pagamento);

    }


}
