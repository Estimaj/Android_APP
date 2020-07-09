package com.example.pint_android_v3.DataBase.Notificacoes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelNotificacoes {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private ArrayList<DataNotificacoes> dataNotificacoes;

    public String getSuccess() {
        return success;
    }

    public ArrayList<DataNotificacoes> getDataViagem() {
        return dataNotificacoes;
    }

    @Override
    public String toString() {
        return "Model_Viagens_Efetuadas{" +
                "success='" + success + '\'' +
                ", dataViagem=" + dataNotificacoes +
                '}';
    }







}
