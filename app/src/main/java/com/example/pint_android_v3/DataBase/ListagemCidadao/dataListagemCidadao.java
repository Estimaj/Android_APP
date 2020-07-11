package com.example.pint_android_v3.DataBase.ListagemCidadao;

import com.example.pint_android_v3.DataBase.ListagemPassageirosCondutor.CidadaoPassageiroInformaçao;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class dataListagemCidadao {

    @SerializedName("id_pass")
    @Expose
    private int idPass;

    @SerializedName("morada_utilizador")
    @Expose
    private String morada_utilizador;

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("id_motorista")
    @Expose
    private String id_motorista;

    public String getId_motorista() {
        return id_motorista;
    }

    public int getIdPass() {
        return idPass;
    }

    public String getMorada_utilizador() {
        return morada_utilizador;
    }

    public String getNome() {
        return nome;
    }
}
