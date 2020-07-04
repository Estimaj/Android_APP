package com.example.pint_android_v3.DataBase.ViagensEfetuadas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model_Viagens_Efetuadas {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private List<dataViagem> dataViagem;

    public String getSuccess() {
        return success;
    }

    public List<dataViagem> getDataViagem() {
        return dataViagem;
    }
}
