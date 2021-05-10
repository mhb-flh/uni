package com.example.pars.uni_prj.http;

import com.example.pars.uni_prj.data.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @POST("register.php")
    Call<User> regCall(@Query("name") String Name, @Query("username") String UserName, @Query("email") String email, @Query("password") String Password);

    @GET("login.php")
    Call<User> loginCall(@Query("username") String UserName,@Query("email") String email, @Query("password") String Password);

}
