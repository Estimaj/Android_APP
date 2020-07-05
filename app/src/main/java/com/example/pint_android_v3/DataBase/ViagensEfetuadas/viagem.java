package com.example.pint_android_v3.DataBase.ViagensEfetuadas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class viagem {
    //"tipo_viagem":"soidavolta","dia_viagem":"27-01-20","hora_viagem":"10:00", id_viagem vai ser o mm que o anterior
    @SerializedName("id_viagem")
    @Expose
    private int id_viagem;

    @SerializedName("tipo_viagem")
    @Expose
    private String tipo_viagem;

    @SerializedName("dia_viagem")
    @Expose
    private String dia_viagem;

    @SerializedName("hora_viagem")
    @Expose
    private String hora_viagem;

    public int getId_viagem() {
        return id_viagem;
    }

    public String getTipo_viagem() {
        return tipo_viagem;
    }

    public String getDia_viagem() {
        return dia_viagem;
    }

    public String getHora_viagem() {
        return hora_viagem;
    }

    @Override
    public String toString() {
        return "viagem{"+ '\n' +
                "id_viagem=" + id_viagem + '\n' +
                ", tipo_viagem='" + tipo_viagem + '\n' +
                ", dia_viagem='" + dia_viagem + '\n' +
                ", hora_viagem='" + hora_viagem + '\n' +
                '}';
    }
}
