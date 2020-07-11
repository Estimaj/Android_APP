package com.example.pint_android_v3.DataBase.ListagemCidadao;

import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.DataListagemCondutor;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ModelListagemCidadao {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private ArrayList<DataListagemCondutor> dataListagemCondutor;

    public String getSuccess() {
        return success;
    }

    public ArrayList<DataListagemCondutor> getDataListagemCondutor() {
        return dataListagemCondutor;
    }

    @Override
    public String toString() {
        return "ModelListagemPassageiros{" + '\n' +
                "success='" + success + '\n' +
                ", dataViagem=" + dataListagemCondutor + '\n' +
                '}';
    }






}
