package com.example.ghtk.api;

import com.example.ghtk.models.Order;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IServiceApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    IServiceApi apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.5:9999")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(IServiceApi.class);

    @Headers("x_authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJuYW1lIjoibGllbTFAZ21haWwuY29tIn0sImlhdCI6MTYyNTAyOTgwNSwiZXhwIjoxNjI1MTE2MjA1fQ.BJD2g-M5EnCKZbgGogsyQDqW8waRWCJvnppF6ST0uyI")
    @GET("/order/send")
    Call<List<Order>> GetOrderByIdSender();

    @Headers("x_authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJuYW1lIjoibGllbTFAZ21haWwuY29tIn0sImlhdCI6MTYyNTAyOTgwNSwiZXhwIjoxNjI1MTE2MjA1fQ.BJD2g-M5EnCKZbgGogsyQDqW8waRWCJvnppF6ST0uyI")
    @GET("/order/receive")
    Call<List<Order>> GetOrderByIdReceiver();

//    @Headers("x_authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJuYW1lIjoibGllbTFAZ21haWwuY29tIn0sImlhdCI6MTYyNTAyMTYyNCwiZXhwIjoxNjI1MTA4MDI0fQ.RbQGJYOSRFYlfE5OFg36fpBrDHmyf-Y_rT249b3ccH8")
//    @POST("/order/create")
//    @Multipart
//    Call<String> PostCreateOrder(@Part("dshanghoa") RequestBody list,
//                                 @Part("khnhan") RequestBody customer,
//                                 @Part("diachinhan") RequestBody receive_address,
//                                 @Part("chiphi") RequestBody fee,
//                                 @Part("diachidi") RequestBody send_address);

    @Headers("x_authorization:eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXlsb2FkIjp7InVzZXJuYW1lIjoibGllbTFAZ21haWwuY29tIn0sImlhdCI6MTYyNTAyOTgwNSwiZXhwIjoxNjI1MTE2MjA1fQ.BJD2g-M5EnCKZbgGogsyQDqW8waRWCJvnppF6ST0uyI")
    @POST("/order/create")
    Call<String> PostCreateOrder(@Body RequestBody body);
}
