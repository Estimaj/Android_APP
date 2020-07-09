package com.example.pint_android_v3.DataBase.DividaUtilizador;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDividaUtilizador {
    @SerializedName("id_divida")
    @Expose
    private int id_divida;

    @SerializedName("Cidadao_id_utilizador")
    @Expose
    private int Cidadao_id_utilizador;

    @SerializedName("Valor_Coima")
    @Expose
    private String Valor_Coima;

    @SerializedName("Estado_Coima")
    @Expose
    private int Estado_Coima;

    @SerializedName("Comprovativo")
    @Expose
    private String Comprovativo;

    public int getId_divida() {
        return id_divida;
    }

    public int getCidadao_id_utilizador() {
        return Cidadao_id_utilizador;
    }

    public String getValor_Coima() {
        return Valor_Coima;
    }

    public int getEstado_Coima() {
        return Estado_Coima;
    }

    public String getComprovativo() {
        return Comprovativo;
    }

    @Override
    public String toString() {
        return "DataDividaUtilizador{" + '\n' +
                "id_divida=" + id_divida + '\n' +
                ", Cidadao_id_utilizador=" + Cidadao_id_utilizador + '\n' +
                ", Valor_Coima='" + Valor_Coima + '\n' +
                ", Estado_Coima=" + Estado_Coima + '\n' +
                ", Comprovativo='" + Comprovativo + '\n' +
                '}';
    }
}
