package com.example.pint_android_v3;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("success")
    @Expose
    private String success;

    @SerializedName("get_user")
    @Expose
    private Get_user get_user;

    public String getSuccess() {
        return success;
    }

    public Get_user getGet_user() {
        return get_user;
    }
}
