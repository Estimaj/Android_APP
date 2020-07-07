package com.example.pint_android_v3.DataBase.ViagensEfetuadas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class dataViagem {

    @SerializedName("cidadao_id_utilizador")
    @Expose
    private int cidadao_id_utilizador;

    @SerializedName("origem")
    @Expose
    private String origemNome;

    @SerializedName("Origem_Coordenadas")
    @Expose
    private String Origem_Coordenadas;

    @SerializedName("destino")
    @Expose
    private String destinoNome;

    @SerializedName("Destino_Coordenadas")
    @Expose
    private String Destino_Coordenadas;

    @SerializedName("id_viagem")
    @Expose
    private int id_viagem;

    @SerializedName("dia_viagem")
    @Expose
    private String dia_viagem;

    @SerializedName("hora_viagem")
    @Expose
    private String hora_viagem;

    @SerializedName("valor_viagem")
    @Expose
    private String valor_viagem;

    @SerializedName("viagem_efetuada")
    @Expose
    private int viagem_efetuada;

    public int getCidadao_id_utilizador() {
        return cidadao_id_utilizador;
    }

    public String getOrigemNome() {
        return origemNome;
    }

    public String getOrigem_Coordenadas() {
        return Origem_Coordenadas;
    }

    public String getDestinoNome() {
        return destinoNome;
    }

    public String getDestino_Coordenadas() {
        return Destino_Coordenadas;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public String getDia_viagem() {
        return dia_viagem;
    }

    public String getHora_viagem() {
        return hora_viagem;
    }

    public String getValor_viagem() {
        return valor_viagem;
    }

    public int getViagem_efetuada() {
        return viagem_efetuada;
    }
}
