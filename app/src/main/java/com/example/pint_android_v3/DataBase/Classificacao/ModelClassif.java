package com.example.pint_android_v3.DataBase.Classificacao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelClassif {
    @SerializedName("id")
    @Expose
    private int Cidadao_id_utilizador;

    @SerializedName("id_viag")
    @Expose
    private int Id_Viagem;

    @SerializedName("classificacao")
    @Expose
    private int valorClassificacao;

    public ModelClassif(int cidadao_id_utilizador, int id_Viagem, int valor) {
        this.Cidadao_id_utilizador = cidadao_id_utilizador;
        this.Id_Viagem = id_Viagem;
        this.valorClassificacao = valor;
    }
}
