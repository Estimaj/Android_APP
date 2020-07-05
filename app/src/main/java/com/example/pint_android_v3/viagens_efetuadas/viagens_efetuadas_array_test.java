package com.example.pint_android_v3.viagens_efetuadas;

import java.util.ArrayList;
import java.util.List;

public class viagens_efetuadas_array_test {

    String[] Local_Partida;

    String[] Local_Chegada;

    String[] distancia;

    String[] tempo;

    String[] data;

    String[] hora;

    public viagens_efetuadas_array_test(String[] local_Partida, String[] local_Chegada, String[] distancia, String[] tempo, String[] data, String[] hora) {
        Local_Partida = local_Partida;
        Local_Chegada = local_Chegada;
        this.distancia = distancia;
        this.tempo = tempo;
        this.data = data;
        this.hora = hora;
    }
}
