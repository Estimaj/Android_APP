package com.example.pint_android_v3.DataBase;

import com.example.pint_android_v3.DataBase.DadosUtilizador.Model;

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
    Call<Model> executeGetUser(@Path("id") String id);

    @POST("/muv/pedidos/criar")
    Call<Pedido_Viagem> executeCriarPedidoViagem(@Body Pedido_Viagem pedido_viagem);

}
