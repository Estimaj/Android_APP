package com.example.pint_android_v3.viagens_marcadas;


import java.util.ArrayList;

public class viagens_marcadas_array_test {

    ArrayList<String> Local_Partida;

    ArrayList<String> Local_Chegada;

    ArrayList<String> Local_PartidaCoordenadas;

    ArrayList<String> Local_ChegadaCoordenadas;

    ArrayList<String> data;

    ArrayList<String> hora;

    ArrayList<Integer> idViagem;

    ArrayList<String> valorViagem;

    public viagens_marcadas_array_test(ArrayList<String> local_Partida, ArrayList<String> local_Chegada,  ArrayList<String> Local_PartidaCoordenadas,
                                        ArrayList<String> Local_ChegadaCoordenadas  , ArrayList<String> data, ArrayList<String> hora
            , ArrayList<Integer> idViagem,  ArrayList<String> valorViagem) {
        this.Local_Partida = local_Partida;
        this.Local_Chegada = local_Chegada;
        this.Local_ChegadaCoordenadas = Local_ChegadaCoordenadas;
        this.Local_PartidaCoordenadas = Local_PartidaCoordenadas;
        this.data = data;
        this.hora = hora;
        this.idViagem = idViagem;
        this.valorViagem = valorViagem;
    }


}