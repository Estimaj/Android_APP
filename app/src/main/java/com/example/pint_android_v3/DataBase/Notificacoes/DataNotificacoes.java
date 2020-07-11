package com.example.pint_android_v3.DataBase.Notificacoes;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataNotificacoes {

    @SerializedName("nome")
    @Expose
    private String nome;

    @SerializedName("id_utilizador")
    @Expose
    private int id_utilizador;

    @SerializedName("id_viagem")
    @Expose
    private int id_viagem;

    @SerializedName("dia_viagem")
    @Expose
    private String dia_viagem;

    @SerializedName("tipo_notif")
    @Expose
    private int tipo_notif;

    public String getNome() {
        return nome;
    }

    public int getId_utilizador() {
        return id_utilizador;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public String getDia_viagem() {
        return dia_viagem;
    }

    public int getTipo_notif() {
        return tipo_notif;
    }
}
