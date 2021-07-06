package com.example.ghtk.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ghtk.Customer;
import com.example.ghtk.LoginResult;
import com.example.ghtk.models.Warehouse;

public class SharedPrefManager {
    public static  final String SHARED_PREF_NAME = "my_shared_pref";

    private static SharedPrefManager mInstance;
    private Context mCtx;
    private SharedPrefManager(Context mCtx){
        this.mCtx = mCtx;
    }

    public static synchronized SharedPrefManager getInstance(Context mCtx){
        if(mInstance == null){
            mInstance = new SharedPrefManager(mCtx);
        }
        return mInstance;
    }

    public void saveUser(LoginResult user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("msg", user.getMsg());
        editor.putString("accessToken", user.getAccessToken());
        editor.putInt("makh", user.getMakh());
        editor.putInt("madn", user.getMadn());
        editor.putString("username", user.getUsername());
        editor.putString("password", user.getPassword());
        editor.putString("refreshtoken", user.getRefreshtoken());
        editor.putString("customerName", user.getCustomerName());
        editor.apply();
    }


    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("makh", -1) != -1;
    }

    public LoginResult getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new LoginResult(
                sharedPreferences.getString("msg", null),
                sharedPreferences.getString("accessToken", null),
                sharedPreferences.getInt("madn", -1),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getInt("makh", -1),
                sharedPreferences.getString("refreshtoken", null),
                sharedPreferences.getString("customerName", null)
        );
    }

    public void saveProfile(Customer customer){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("MaKH", customer.getMaKH());
        editor.putString("TenKH", customer.getTenKH());
        editor.putString("SDT", customer.getSDT());
        editor.putString("DiaChi", customer.getDiaChi());
        editor.putInt("madn", customer.getMadn());
        editor.putString("username", customer.getUsername());
        editor.putString("password", customer.getPassword());
        editor.putInt("makh", customer.getMakh());
        editor.putString("refreshtoken", customer.getRefreshtoken());

        editor.apply();
    }

    public Customer getProfile(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Customer(
                sharedPreferences.getInt("MaKH", -1),
                sharedPreferences.getString("TenKH", null),
                sharedPreferences.getString("SDT", null),
                sharedPreferences.getString("DiaChi", null),
                sharedPreferences.getInt("madn", -1),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getInt("makh", -1),
                sharedPreferences.getString("refreshToken", null)
        );
    }

    public Warehouse getWarehouseInfo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Warehouse(
                sharedPreferences.getInt("makho", -1),
                sharedPreferences.getString("tenkho", null),
                sharedPreferences.getString("diachi", null),
                sharedPreferences.getString("tinh", null),
                sharedPreferences.getString("vido", null),
                sharedPreferences.getString("kinhdo", null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

    }
}
