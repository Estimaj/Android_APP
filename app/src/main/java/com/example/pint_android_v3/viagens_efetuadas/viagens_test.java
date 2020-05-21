package com.example.pint_android_v3.viagens_efetuadas;

public class viagens_test {

    private String Local_Partida;
    private String Local_Chegada;
    private String data;
    private String distancia;
    private String tempo;

    public viagens_test(String Local_Partida, String Local_Chegada, String data, String distancia, String tempo)
    {
        this.data = data;
        this.distancia = distancia;
        this.Local_Chegada = Local_Chegada;
        this.Local_Partida = Local_Partida;
        this.tempo = tempo;

    }

    public String getLocal_Partida() {
        return Local_Partida;
    }

    public String getLocal_Chegada() {
        return Local_Chegada;
    }

    public String getData() {
        return data;
    }

    public String getDistancia() {
        return distancia;
    }

    public String getTempo() {
        return tempo;
    }
}
