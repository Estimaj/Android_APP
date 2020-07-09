package com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class dataListagemCondutor {

    @SerializedName("id_pass")
    @Expose
    private int idPass;

    @SerializedName("nome_utilizador")
    @Expose
    private String nomeUtilizador;

    @SerializedName("morada_utilizador")
    @Expose
    private String moradaUtilizador;



    public int getIdPass() {
        return idPass;
    }

    public String getNomeUtilizador() {
        return nomeUtilizador;
    }

    public String getMoradaUtilizador() {
        return moradaUtilizador;
    }

}
