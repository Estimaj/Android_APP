package com.example.pint_android_v3.DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DividaCreate {

    @SerializedName("Cidadao_id_utilizador")
    @Expose
    private int Cidadao_id_utilizador;

    @SerializedName("Valor_Coima")
    @Expose
    private String Valor_Coima;

    @SerializedName("Estado_Coima")
    @Expose
    private int Estado_Coima;

    public DividaCreate(int cidadao_id_utilizador, String valor_Coima) {
        this.Cidadao_id_utilizador = cidadao_id_utilizador;
        this.Valor_Coima = valor_Coima;
        this.Estado_Coima = 0;
    }
}
