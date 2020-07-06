package com.example.pint_android_v3.DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    private int tipo;
    @SerializedName("id_user")
    @Expose
    private int id; //id_user
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
