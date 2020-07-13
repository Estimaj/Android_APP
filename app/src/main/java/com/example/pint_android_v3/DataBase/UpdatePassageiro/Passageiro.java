package com.example.pint_android_v3.DataBase.UpdatePassageiro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passageiro {

    @SerializedName("id")
    @Expose
    private Integer cidadao_id_utilizador;

    @SerializedName("id_viag")
    @Expose
    private Integer id_viagem;

    @SerializedName("pagou_viagem")
    @Expose
    private int pagou_viagem;

    @SerializedName("comparencia")
    @Expose
    private int comparencia_viagem;

    @SerializedName("valor_a_pagar")
    @Expose
    private String valor_a_pagar;


    public void setCidadao_id_utilizador(int cidadao_id_utilizador) {
        this.cidadao_id_utilizador = cidadao_id_utilizador;
    }

    public void setId_viagem(Integer id_viagem) {
        this.id_viagem = id_viagem;
    }

    public void setPagou_viagem(int pagou_viagem) {
        this.pagou_viagem = pagou_viagem;
    }

    public void setComparencia_viagem(int comparencia_viagem) {
        this.comparencia_viagem = comparencia_viagem;
    }


    public int getCidadao_id_utilizador() {
        return cidadao_id_utilizador;
    }

    public int getId_viagem() {
        return id_viagem;
    }

    public int getPagou_viagem() {
        return pagou_viagem;
    }

    public int getComparencia_viagem() {
        return comparencia_viagem;
    }

    public String getValor_a_pagar() {
        return valor_a_pagar;
    }
}
