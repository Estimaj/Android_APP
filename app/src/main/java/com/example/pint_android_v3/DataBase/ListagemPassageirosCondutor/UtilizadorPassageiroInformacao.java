package com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtilizadorPassageiroInformacao {

    @SerializedName("id_utilizador")
    @Expose
    private int id_utilizador;

    @SerializedName("nome_utilizador")
    @Expose
    private String nome_utilizador;

    @SerializedName("morada_utilizador")
    @Expose
    private String moradaUtilizador;

    public int getId_utilizador() {
        return id_utilizador;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public String getMoradaUtilizador() {
        return moradaUtilizador;
    }
}
