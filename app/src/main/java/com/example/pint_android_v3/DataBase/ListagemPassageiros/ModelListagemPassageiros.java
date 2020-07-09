package com.example.pint_android_v3.DataBase.ListagemPassageiros;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelListagemPassageiros {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private ArrayList<dataListagem> dataListagem;

    public String getSuccess() {
        return success;
    }

    public ArrayList<dataListagem> getDataListagem() {
        return dataListagem;
    }

    @Override
    public String toString() {
        return "ModelListagemPassageiros{" +
                "success='" + success + '\'' +
                ", dataViagem=" + dataListagem +
                '}';
    }


}
