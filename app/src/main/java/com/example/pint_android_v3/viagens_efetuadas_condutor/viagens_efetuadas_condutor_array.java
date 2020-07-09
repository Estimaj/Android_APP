package com.example.pint_android_v3.viagens_efetuadas_condutor;

import java.util.ArrayList;

public class viagens_efetuadas_condutor_array {

    ArrayList<String> Local_Partida;

    ArrayList<String> Local_Chegada;

    ArrayList<String> Local_PartidaCoordenadas;

    ArrayList<String> Local_ChegadaCoordenadas;

    ArrayList<String> data;

    ArrayList<String> hora;

    ArrayList<Integer> idViagem;

    ArrayList<String> valorViagem;

    ArrayList<Integer> bagagemPedido;

    ArrayList<Integer> animalPedido;

    ArrayList<Integer> necessidadesEspeciaisPedido;

    public viagens_efetuadas_condutor_array(ArrayList<String> local_Partida, ArrayList<String> local_Chegada, ArrayList<String> Local_PartidaCoordenadas,
            ArrayList<String> Local_ChegadaCoordenadas  , ArrayList<String> data, ArrayList<String> hora
            , ArrayList<Integer> idViagem, ArrayList<String> valorViagem, ArrayList<Integer> bagagemPedido,
            ArrayList<Integer> animalPedido, ArrayList<Integer> necessidadesEspeciaisPedido) {
        this.Local_Partida = local_Partida;
        this.Local_Chegada = local_Chegada;
        this.Local_ChegadaCoordenadas = Local_ChegadaCoordenadas;
        this.Local_PartidaCoordenadas = Local_PartidaCoordenadas;
        this.data = data;
        this.hora = hora;
        this.idViagem = idViagem;
        this.valorViagem = valorViagem;
        this.bagagemPedido = bagagemPedido;
        this.animalPedido = animalPedido;
        this.necessidadesEspeciaisPedido = necessidadesEspeciaisPedido;
    }
}
