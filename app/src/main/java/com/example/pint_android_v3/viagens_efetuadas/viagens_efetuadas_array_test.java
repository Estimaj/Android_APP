package com.example.pint_android_v3.viagens_efetuadas;

import java.util.ArrayList;

public class viagens_efetuadas_array_test {

    ArrayList<String> Local_Partida;

    ArrayList<String> Local_Chegada;

    //ArrayList<String> distancia;

    //ArrayList<String> tempo;

    ArrayList<String> data;

    ArrayList<String> hora;

    public viagens_efetuadas_array_test(ArrayList<String> local_Partida, ArrayList<String> local_Chegada
            , ArrayList<String> data, ArrayList<String> hora) {
        Local_Partida = local_Partida;
        Local_Chegada = local_Chegada;
        //this.distancia = distancia;
        //this.tempo = tempo;
        this.data = data;
        this.hora = hora;
    }
}
