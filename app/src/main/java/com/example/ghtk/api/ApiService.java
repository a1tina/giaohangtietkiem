package com.example.ghtk.api;

import com.example.ghtk.Customer;
import com.example.ghtk.LoginResult;
import com.example.ghtk.models.Warehouse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
//    Gson gson = new GsonBuilder()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss")
//            .create();

    @FormUrlEncoded
    @POST("auth/loginwithgoogle")
    Call<LoginResult> loginwithGoogle(
            @Field("email") String email,
            @Field("name") String name,
            @Field("SDT") String SDT,
            @Field("DiaChi") String diaChi
    );


    @FormUrlEncoded
    @POST("auth/login")
    Call<LoginResult> postLogin(
            @Field("userName") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("auth/register")
    Call<LoginResult> createUser(
            @Field("userName") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("users/update")
    Call<Customer> updateProfile(
            @Header("x_authorization") String accessToken,
            @Field("name") String name,
            @Field("phone") String phone,
            @Field("adress") String address
    );

    @GET("users/profile")
    Call<Customer> getProfile(
            @Header("x_authorization") String accessToken
    );
    @GET("/warehouse")
    Call<List<Warehouse>> getWarehouse();

}
