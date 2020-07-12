package com.example.pint_android_v3.DataBase.Classificacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelClassif {
    @SerializedName("cidadao_id_utilizador")
    @Expose
    private int Cidadao_id_utilizador;

    @SerializedName("id_viagem")
    @Expose
    private String Id_Viagem;

    @SerializedName("valor")
    @Expose
    private int valor;

    public ModelClassif(int cidadao_id_utilizador, String id_Viagem, int valor) {
        Cidadao_id_utilizador = cidadao_id_utilizador;
        Id_Viagem = id_Viagem;
        this.valor = valor;
    }
}
