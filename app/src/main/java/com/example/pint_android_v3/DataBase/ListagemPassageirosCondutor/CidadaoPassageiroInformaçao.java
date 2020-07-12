package com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CidadaoPassageiroInformaçao {

    @SerializedName("Id_Utilizador")
    @Expose
    private int Id_Utilizador;

    @SerializedName("utilizador")
    @Expose
    private UtilizadorPassageiroInformacao utilizador;

    public int getId_Utilizador() {
        return Id_Utilizador;
    }


    public UtilizadorPassageiroInformacao getUtilizador() {
        return utilizador;
    }
}
