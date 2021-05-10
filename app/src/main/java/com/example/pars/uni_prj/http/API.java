package com.example.pars.uni_prj.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {

    public static final String BASE_URL = "http://192.168.1.3/uni/";
    public static Retrofit myRetrofit = null;

    public static Retrofit getAPI() {

        if (myRetrofit == null) {

            myRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return myRetrofit;

    }

}
