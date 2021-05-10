package com.example.pars.uni_prj.data;

import com.example.pars.uni_prj.http.ApiService;
import java.util.List;
import io.reactivex.Single;

public class MainViewModel {

    private ApiService apiService;

    public MainViewModel(ApiService apiService) {

        this.apiService = apiService;
    }

    public Single<List<items>> getProduct() {
        return apiService.getProducts();
    }
}
