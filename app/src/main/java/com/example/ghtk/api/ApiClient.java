package com.example.ghtk.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String REMOTE_BASE_URL = "https://logistic-backend.herokuapp.com/";
    public static final String LOCAL_BASE_URL = "http://192.168.1.6:9999";
    private static ApiClient mInstance;
    private Retrofit retrofit;

    private ApiClient(){
        Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        retrofit = new Retrofit.Builder()
                .baseUrl(REMOTE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
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

    public OrderApi getOrderApi(){
        return retrofit.create(OrderApi.class);
    }
}
