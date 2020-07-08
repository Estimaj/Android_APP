package com.example.pint_android_v3.DataBase;

import com.example.pint_android_v3.DataBase.DadosUtilizador.Model_User_Information;
import com.example.pint_android_v3.DataBase.ViagensEfetuadas.Model_Viagens_Efetuadas;
import com.example.pint_android_v3.DataBase.ViagensMarcadas.ModelViagensMarcadas;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

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

  /*  @GET("muv/viagem/listaCidadao/{id}")
    Call<ModelViagensMarcadas> executeViagemMarcada(@Path("id") int id);*/

}
