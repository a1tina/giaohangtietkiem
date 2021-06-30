package com.example.ghtk;

import java.io.Serializable;

public class User implements Serializable {

    private int madn, makh;
    private String username, password, refreshtoken, customerName;


    public User(int madn, int makh, String username, String password, String refreshtoken, String customerName) {
        this.madn = madn;
        this.makh = makh;
        this.username = username;
        this.password = password;
        this.refreshtoken = refreshtoken;
        this.customerName = customerName;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "User{" +
                "madn=" + madn +
                ", makh=" + makh +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", refreshtoken='" + refreshtoken + '\'' +
                '}';
    }

    public int getMadn() {
        return madn;
    }

    public void setMadn(int madn) {
        this.madn = madn;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
