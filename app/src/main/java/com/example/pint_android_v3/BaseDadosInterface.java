package com.example.pint_android_v3;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BaseDadosInterface {

    @POST("/muv/login")
    Call<Post> executeLogin(@Body HashMap<String, String> map);
}
