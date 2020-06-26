package com.example.pint_android_v3.DataBase;

public class Post {

    private int tipo;
    private int id;
    private String message;

    public int getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Post{" + '\n' +
                "tipo=" + tipo + '\n' +
                ", id=" + id + '\n' +
                ", message='" + message + '\n' +
                '}';
    }
}
