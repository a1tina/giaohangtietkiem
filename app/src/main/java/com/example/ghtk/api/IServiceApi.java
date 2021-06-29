package com.example.ghtk.api;

import com.example.ghtk.models.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface IServiceApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    IServiceApi apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:9999/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IServiceApi.class);
    @Headers("x_authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJuYW1lIjoibGllbUBnbWFpbC5jb20ifSwiaWF0IjoxNjI0ODcxNTkwLCJleHAiOjE2MjQ5NTc5OTB9.Nt9Knzy_7dLCRdDMCdi7l3T80jC7PAqn2safmETXfAQ")
    @GET("order/send")
    Call<List<Order>> GetOrderByIdSender();

    @Headers("x_authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJuYW1lIjoibGllbUBnbWFpbC5jb20ifSwiaWF0IjoxNjI0ODcxNTkwLCJleHAiOjE2MjQ5NTc5OTB9.Nt9Knzy_7dLCRdDMCdi7l3T80jC7PAqn2safmETXfAQ")
    @GET("order/receive")
    Call<List<Order>> GetOrderByIdReceiver();

    @Multipart
    @POST
    Call<String> PostCreateOrder(@Part("dshanghoa") RequestBody list,
                                 @Part("khnhan") RequestBody customer,
                                 @Part("diachinhan") RequestBody receive_address,
                                 @Part("chiphi") RequestBody fee,
                                 @Part("diachidi") RequestBody send_address,
                                 @Part("image")MultipartBody.Part image);
}
