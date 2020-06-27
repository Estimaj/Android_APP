package com.example.pint_android_v3.DataBase;

public class Pedido_Viagem {

    private int id_tipo_pedido;

    private int cidadao_id_utilizador;

    private int local_origem_pedido;

    private int local_destino_pedido;

    private int bagagem_pedido;

    private int modalidade;

    private int partilha;

    private int animal;

    private int necessidadesespeciais_pedido;

    private int cancelar_pedido;

    private String horas_pedido;

    private String data_pedido;

    private String dia_viagem;

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
                ", horas_pedido='" + horas_pedido + '\n' +
                ", data_pedido='" + data_pedido + '\n' +
                ", dia_viagem='" + dia_viagem + '\n' +
                ", hora_recolha_viagem='" + hora_recolha_viagem + '\n' +
                '}';
    }
}
