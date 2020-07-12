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

    @SerializedName("valor_a_pagar")
    @Expose
    private String ValorAPagarIndividual;

    @SerializedName("comparencia_viagem")
    @Expose
    private int Compareceu;

    public String getValorAPagarIndividual(){
        return ValorAPagarIndividual;
    }

    public int getIdPass() {
        return idPass;
    }

    public CidadaoPassageiroInformaçao getCidadao() {
        return cidadao;
    }

    public int getCompareceu() {
        return Compareceu;
    }
}
