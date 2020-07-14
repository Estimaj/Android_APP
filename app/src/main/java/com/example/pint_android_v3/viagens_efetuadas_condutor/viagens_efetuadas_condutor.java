package com.example.pint_android_v3.viagens_efetuadas_condutor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.pint_android_v3.DataBase.BaseDadosInterface;
import com.example.pint_android_v3.DataBase.ViagensInformacao.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.DataBase.ViagensInformacao.dataViagem;
import com.example.pint_android_v3.R;
import com.example.pint_android_v3.barra_lateral.barra_lateral_condutor;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class viagens_efetuadas_condutor extends barra_lateral_condutor {

    private String BASE_URL ="https://pintbackend.herokuapp.com";
    private int user_id;
    private ArrayList<dataViagem> informacaoViagem;

    ListView lView;
    ListAdapter lAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens_efetuadas_condutor);

        Intent X = getIntent();
        Bundle b = X.getExtras();
        if(b!=null){
            user_id = (int) b.get("user_id");
        }
        getInformationFromdb(user_id);

        Bar_Settings(user_id);
    }

    private void getInformationFromdb(int id) {
        if(id == 0) return;

        Retrofit retrofit;
        BaseDadosInterface baseDadosInterface;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        baseDadosInterface =  retrofit.create(BaseDadosInterface.class);

        Call<Model_Viagens_Efetuadas> call = baseDadosInterface.executeViagemEfetuadaMotorista(id);

        call.enqueue(new Callback<Model_Viagens_Efetuadas>() {
            @Override
            public void onResponse(Call<Model_Viagens_Efetuadas> call, Response<Model_Viagens_Efetuadas> response) {
                if (!response.isSuccessful()){
                    Log.i("Erro", "verificar o link na interface");
                }
                if (response.code() == 200) {
                    informacaoViagem = response.body().getDataViagem();
                    createAdapters();
                }
            }

            @Override
            public void onFailure(Call<Model_Viagens_Efetuadas> call, Throwable t) {
                Log.i("Failure:", t.toString());
            }
        });
    }

    private void createAdapters(){
        ArrayList<String> Local_Partida = new ArrayList<>();
        ArrayList<String> Local_Chegada = new ArrayList<>();
        ArrayList<String> Local_PartidaCoordenadas = new ArrayList<>();
        ArrayList<String> Local_ChegadaCoordenadas = new ArrayList<>();
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String> hora = new ArrayList<>();
        ArrayList<Integer> idViagem = new ArrayList<>();
        ArrayList<String> valorViagem = new ArrayList<>();
        ArrayList<Integer> bagagemPedido = new ArrayList<>();
        ArrayList<Integer> animalPedido = new ArrayList<>();
        ArrayList<Integer> necessidadesEspeciaisPedido = new ArrayList<>();

        for (int i = 0; i < informacaoViagem.size(); i++) {
            if(informacaoViagem.get(i).getViagem_efetuada() == 1) { //confirma se a viagem é efetuada ou não
                Local_Partida.add(informacaoViagem.get(i).getOrigemNome());
                Local_Chegada.add(informacaoViagem.get(i).getDestinoNome());
                Local_PartidaCoordenadas.add(informacaoViagem.get(i).getDestino_Coordenadas());
                Local_ChegadaCoordenadas.add(informacaoViagem.get(i).getOrigem_Coordenadas());

                data.add(informacaoViagem.get(i).getDia_viagem());
                hora.add(informacaoViagem.get(i).getHora_viagem());

                idViagem.add(informacaoViagem.get(i).getId_viagem());
                valorViagem.add(informacaoViagem.get(i).getValor_viagem());

                bagagemPedido.add(informacaoViagem.get(i).getBagagem_pedido());
                animalPedido.add(informacaoViagem.get(i).getAnimal());
                necessidadesEspeciaisPedido.add(informacaoViagem.get(i).getNecessidadesespeciais_pedido());
            }
        }
        viagens_efetuadas_condutor_array lItems = new viagens_efetuadas_condutor_array(
                Local_Partida, Local_Chegada,
                Local_PartidaCoordenadas, Local_ChegadaCoordenadas,
                data, hora, idViagem,
                valorViagem, bagagemPedido,
                animalPedido, necessidadesEspeciaisPedido
        );


        lView = findViewById(R.id.viagens_efetuadas_listview_condutor);
        lAdapter = new CustomListAdapter_efetuadas_condutor(
                viagens_efetuadas_condutor.this,
                user_id,
                lItems.data,
                lItems.Local_Chegada,
                lItems.Local_ChegadaCoordenadas,
                lItems.Local_Partida,
                lItems.Local_PartidaCoordenadas,
                lItems.hora,
                lItems.idViagem,
                lItems.valorViagem,
                lItems.bagagemPedido,
                lItems.animalPedido,
                lItems.necessidadesEspeciaisPedido);

        lView.setAdapter(lAdapter);
    }
}
