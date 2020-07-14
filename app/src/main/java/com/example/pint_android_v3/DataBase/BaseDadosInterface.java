package com.example.pint_android_v3.DataBase;

import com.example.pint_android_v3.DataBase.Classificacao.ModelClassif;
import com.example.pint_android_v3.DataBase.DadosUtilizador.Model_User_Information;
import com.example.pint_android_v3.DataBase.DividaUtilizador.ModelDividaUtilizador;
import com.example.pint_android_v3.DataBase.ListagemCidadao.ModelListagemCidadao;
import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.ModelListagemPassageirosCondutor;
import com.example.pint_android_v3.DataBase.Notificacoes.ModelNotificacoes;
import com.example.pint_android_v3.DataBase.UpdatePassageiro.Passageiro;
import com.example.pint_android_v3.DataBase.ViagemUnica.ModelViagemUnica;
import com.example.pint_android_v3.DataBase.ViagensInformacao.Model_Viagens_Efetuadas;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseDadosInterface {

    @POST("/muv/login")
    Call<Post> executeLogin(@Body HashMap<String, String> map);

    @GET("/muv/get/{id}")
    Call<Model_User_Information> executeGetUser(@Path("id") int id);

    @POST("/muv/pedidos/criarmobile")
    Call<Pedido_Viagem> executeCriarPedidoViagem(@Body Pedido_Viagem pedido_viagem);

    @GET("muv/viagem/listaCidadao/{id}")
    Call<Model_Viagens_Efetuadas> executeViagemEfetuada(@Path("id") int id);

    @GET("muv/viagem/listaMotorista/{id}")
    Call<Model_Viagens_Efetuadas> executeViagemEfetuadaMotorista(@Path("id") int id);

    @GET("/muv/dividas/getcidadaodivida/{id}")
    Call<ModelDividaUtilizador> executeGetUserDivida(@Path("id") int id);

    @GET("/muv/notificacao/listaNotif/{id}")
    Call<ModelNotificacoes> executeGetNotificacoes(@Path("id") int id);

    @GET("/muv/passageiros/get/{id}")
    Call<ModelListagemPassageirosCondutor> executeGetListagem(@Path("id") int id_viagem);

    @GET("/muv/passageiros/getpassquery/{id}")
    Call<ModelListagemCidadao> executeGetListagemQuery(@Path("id") int id_viagem);

    @GET("muv/viagem/getOne/{id}")
    Call<ModelViagemUnica> executeViagemUnica(@Path("id") int id);

    @POST("/muv/passageiros/update")
    Call<Passageiro> executeUpdatePassageiro(@Body Passageiro passageiros);

    @POST("/muv/dividas/create")
    Call<DividaCreate> executeCriarDividaPassageiro(@Body DividaCreate divida);

    @POST("/muv/passageiros/updateClassificacao")
    Call<ModelClassif> executeMudarClassificacao(@Body ModelClassif classificacao);

    @POST("/muv/pedidos/updateCanceleViagem")
    Call<Passageiro> executeUpdatePedidoDeletePassageiro(@Body HashMap<String, Integer> map);
}
