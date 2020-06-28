package com.example.pint_android_v3.DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pedido_Viagem {

    @SerializedName("id_tipo_pedido")
    @Expose
    private int id_tipo_pedido;

    @SerializedName("cidadao")
    @Expose
    private int cidadao_id_utilizador;

    @SerializedName("l_origem")
    @Expose
    private int local_origem_pedido;

    @SerializedName("l_destino")
    @Expose
    private int local_destino_pedido;

    @SerializedName("bagagem")
    @Expose
    private int bagagem_pedido;

    @SerializedName("modalidade")
    @Expose
    private int modalidade;

    @SerializedName("partilha")
    @Expose
    private int partilha;

    @SerializedName("animal")
    @Expose
    private int animal;

    @SerializedName("nespeciais")
    @Expose
    private int necessidadesespeciais_pedido;

    @SerializedName("cancele")
    @Expose
    private int cancelar_pedido;

    @SerializedName("horas_pedido")
    @Expose
    private String horas_pedido;

    @SerializedName("data_pedido")
    @Expose
    private String data_pedido;

    @SerializedName("dia_viagem")
    @Expose
    private String dia_viagem;

    @SerializedName("hora_recolha")
    @Expose
    private String hora_recolha_viagem;

    public Pedido_Viagem(int id_tipo_pedido, int cidadao_id_utilizador, int local_origem_pedido, int local_destino_pedido, int bagagem_pedido, int modalidade, int partilha, int animal, int necessidadesespeciais_pedido, int cancelar_pedido, String horas_pedido, String data_pedido, String dia_viagem, String hora_recolha_viagem) {
        this.id_tipo_pedido = id_tipo_pedido;
        this.cidadao_id_utilizador = cidadao_id_utilizador;
        this.local_origem_pedido = local_origem_pedido;
        this.local_destino_pedido = local_destino_pedido;
        this.bagagem_pedido = bagagem_pedido;
        this.modalidade = modalidade;
        this.partilha = partilha;
        this.animal = animal;
        this.necessidadesespeciais_pedido = necessidadesespeciais_pedido;
        this.cancelar_pedido = cancelar_pedido;
        this.horas_pedido = horas_pedido;
        this.data_pedido = data_pedido;
        this.dia_viagem = dia_viagem;
        this.hora_recolha_viagem = hora_recolha_viagem;
    }

    @Override
    public String toString() {
        return "pedido_viagem{" +'\n' +
                "id_tipo_pedido=" + id_tipo_pedido +'\n' +
                ", cidadao_id_utilizador=" + cidadao_id_utilizador +'\n' +
                ", local_origem_pedido=" + local_origem_pedido +'\n' +
                ", local_destino_pedido=" + local_destino_pedido +'\n' +
                ", bagagem_pedido=" + bagagem_pedido +'\n' +
                ", modalidade=" + modalidade +'\n' +
                ", partilha=" + partilha +'\n' +
                ", animal=" + animal +'\n' +
                ", necessidadesespeciais_pedido=" + necessidadesespeciais_pedido +'\n' +
                ", cancelar_pedido=" + cancelar_pedido +'\n' +
                ", horas_pedido=" + horas_pedido + '\n' +
                ", data_pedido=" + data_pedido + '\n' +
                ", dia_viagem=" + dia_viagem + '\n' +
                ", hora_recolha_viagem=" + hora_recolha_viagem + '\n' +
                '}';
    }
}
