package com.example.ghtk.api;

import com.google.android.gms.common.api.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://logistic-backend.herokuapp.com/";
    private static ApiClient mInstance;
    private Retrofit retrofit;

    private ApiClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized ApiClient getInstance(){
        if(mInstance == null){
            mInstance = new ApiClient();
        }
        return mInstance;
    }
    public ApiService getApi(){
        return retrofit.create(ApiService.class);
    }
}
