package com.example.pint_android_v3.DataBase.ViagensEfetuadas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Localidade_Destino {
    @SerializedName("id_localidade")
    @Expose
    private int id_localidade;

    @SerializedName("nome_localidade")
    @Expose
    private String nome_localidade;

    @SerializedName("pontos_paragem")
    @Expose
    private String pontos_paragem;

    public int getId_localidade() {
        return id_localidade;
    }

    public String getNome_localidade() {
        return nome_localidade;
    }

    public String getPontos_paragem() {
        return pontos_paragem;
    }
}
