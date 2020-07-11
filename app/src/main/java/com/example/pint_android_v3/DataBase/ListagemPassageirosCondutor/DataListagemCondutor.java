package com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataListagemCondutor {

    @SerializedName("id_pass")
    @Expose
    private int idPass;

    @SerializedName("cidadao")
    @Expose
    private CidadaoPassageiroInformaçao cidadao;

    public int getIdPass() {
        return idPass;
    }

    public CidadaoPassageiroInformaçao getCidadao() {
        return cidadao;
    }
}
