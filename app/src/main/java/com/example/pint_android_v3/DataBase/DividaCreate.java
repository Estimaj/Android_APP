package com.example.pint_android_v3.DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DividaCreate {

    @SerializedName("id_cidadao")
    @Expose
    private int Cidadao_id_utilizador;

    @SerializedName("valor")
    @Expose
    private String Valor_Coima;

    @SerializedName("estado_coima")
    @Expose
    private int Estado_Coima;

    public DividaCreate(int cidadao_id_utilizador, String valor_Coima) {
        this.Cidadao_id_utilizador = cidadao_id_utilizador;
        this.Valor_Coima = valor_Coima;
        this.Estado_Coima = 0;
    }
}
