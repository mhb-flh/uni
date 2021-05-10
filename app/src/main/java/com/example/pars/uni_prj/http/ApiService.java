package com.example.pars.uni_prj.http;


import com.example.pars.uni_prj.data.items;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiService {
        @GET("4ce06e28-efd7-4d49-a2bc-0e33bcfdecff")
        Single<List<items>> getProducts();
    }
