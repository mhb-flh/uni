package com.example.pars.uni_prj.data;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private String apiResposnse;

    @SerializedName("name")
    private String apiName;

    @SerializedName("username")
    private String apiUsername;


    public String getApiResposnse() {
        return apiResposnse;
    }

    public String getApiName() {
        return apiName;
    }

    public String getApiUsername() {
        return apiUsername;
    }
}
