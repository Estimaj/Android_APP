package com.example.pint_android_v3;

public class Get_user {
    private int id_utilizador;
    private int tipo_utilizador;
    private String nome_utilizador;
    private String email_utilizador;
    private String password_utilizador;
    private String password2_utilizador;
    private String data_nascimento_utilizador;
    private String morada_utilizador;
    private String telefone_utilizador;
    private String cc_utilizador;
    private String nif_utilizador;

    public int getId_utilizador() {
        return id_utilizador;
    }

    public int getTipo_utilizador() {
        return tipo_utilizador;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public String getEmail_utilizador() {
        return email_utilizador;
    }

    public String getPassword_utilizador() {
        return password_utilizador;
    }

    public String getPassword2_utilizador() {
        return password2_utilizador;
    }

    public String getData_nascimento_utilizador() {
        return data_nascimento_utilizador;
    }

    public String getMorada_utilizador() {
        return morada_utilizador;
    }

    public String getTelefone_utilizador() {
        return telefone_utilizador;
    }

    public String getCc_utilizador() {
        return cc_utilizador;
    }

    public String getNif_utilizador() {
        return nif_utilizador;
    }

    @Override
    public String toString() {
        return "Get_user{" + '\n' +
                "id_utilizador=" + id_utilizador + '\n' +
                ", tipo_utilizador=" + tipo_utilizador + '\n' +
                ", nome_utilizador=" + nome_utilizador + '\n' +
                ", email_utilizador=" + email_utilizador + '\n' +
                ", password_utilizador=" + password_utilizador + '\n' +
                ", password2_utilizador=" + password2_utilizador + '\n' +
                ", data_nascimento_utilizador='" + data_nascimento_utilizador + '\n' +
                ", morada_utilizador='" + morada_utilizador + '\n' +
                ", telefone_utilizador='" + telefone_utilizador + '\n' +
                ", cc_utilizador='" + cc_utilizador + '\n' +
                ", nif_utilizador='" + nif_utilizador + '\n' +
                '}';
    }
}
