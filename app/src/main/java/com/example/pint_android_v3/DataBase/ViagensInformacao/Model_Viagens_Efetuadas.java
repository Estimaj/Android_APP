package com.example.pint_android_v3.DataBase.ViagensInformacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

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
        return "Model_Viagens_Efetuadas{" + '\n' +
                "success='" + success + '\n' +
                ", dataViagem=" + dataViagem + '\n' +
                '}';
    }
}
