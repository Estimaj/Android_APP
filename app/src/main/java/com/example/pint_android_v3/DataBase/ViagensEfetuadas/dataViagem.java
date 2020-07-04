package com.example.pint_android_v3.DataBase.ViagensEfetuadas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataViagem {

    @SerializedName("id_viagem")
    @Expose
    private int id_viagem;

    @SerializedName("viagem")
    @Expose
    private viagem viagem;

    @SerializedName("Localidade_Origem")
    @Expose
    private Localidade_Origem Localidade_Origem;

    @SerializedName("Localidade_Destino")
    @Expose
    private Localidade_Destino Localidade_Destino;

    public int getId_viagem() {
        return id_viagem;
    }

    public viagem getViagemRegistada() {
        return viagem;
    }

    public Localidade_Origem getLocalidade_Origem() {
        return Localidade_Origem;
    }

    public Localidade_Destino getLocalidade_Destino() {
        return Localidade_Destino;
    }

    @Override
    public String toString() {
        return "dataViagem{" +
                "id_viagem=" + id_viagem +
                ", viagem=" + viagem +
                ", Localidade_Origem=" + Localidade_Origem +
                ", Localidade_Destino=" + Localidade_Destino +
                '}';
    }
}
