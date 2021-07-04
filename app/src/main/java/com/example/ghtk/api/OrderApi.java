package com.example.ghtk.api;

import com.example.ghtk.models.Order;
import com.example.ghtk.models.OrderDetail;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface OrderApi {


    @GET("/order/send")
    Call<List<Order>> GetOrderByIdSender(@Header("x_authorization") String accessToken);

    @GET("/order/receive")
    Call<List<Order>> GetOrderByIdReceiver(@Header("x_authorization") String accessToken);


    @GET("/order/{id}")
    Call<OrderDetail> GetOrderDetailById(@Header("x_authorization") String accessToken, @Path("id") int id);

    @Multipart
    @POST("/order/create")
    Call<String> PostCreateOrder(@Header("x_authorization") String accessToken,
                                 @Part MultipartBody.Part list,
                                 @Part MultipartBody.Part customer,
                                 @Part MultipartBody.Part receive_address,
                                 @Part MultipartBody.Part fee,
                                 @Part MultipartBody.Part send_address,
                                 @Part MultipartBody.Part image);
}