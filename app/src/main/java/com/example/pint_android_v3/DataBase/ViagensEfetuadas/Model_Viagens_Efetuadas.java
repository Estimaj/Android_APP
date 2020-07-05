package com.example.pint_android_v3.DataBase.ViagensEfetuadas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Model_Viagens_Efetuadas {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private ArrayList<dataViagem> dataViagem;

    public String getSuccess() {
        return success;
    }

    public ArrayList<dataViagem> getDataViagem() {
        return dataViagem;
    }

    @Override
    public String toString() {
        return "Model_Viagens_Efetuadas{" +
                "success='" + success + '\'' +
                ", dataViagem=" + dataViagem +
                '}';
    }
}
