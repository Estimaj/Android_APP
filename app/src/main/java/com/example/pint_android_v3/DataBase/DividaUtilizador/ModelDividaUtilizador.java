package com.example.pint_android_v3.DataBase.DividaUtilizador;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class ModelDividaUtilizador {
    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("data")
    @Expose
    private ArrayList<DataDividaUtilizador> dataDividaUtilizadors;

    public String getSuccess() {
        return success;
    }

    public ArrayList<DataDividaUtilizador> getDataDividaUtilizadors() {
        return dataDividaUtilizadors;
    }

    @Override
    public String toString() {
        return "ModelDividaUtilizador{" + '\n' +
                "success='" + success + '\n' +
                ", dataDividaUtilizadors=" + dataDividaUtilizadors + '\n' +
                '}';
    }
}
