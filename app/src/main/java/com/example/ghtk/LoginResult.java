package com.example.ghtk;

public class LoginResult {
    private String msg, accessToken;
    private int madn;
    private String username, password;
    private int makh;
    private String refreshtoken, customerName;

    public LoginResult(String msg, String accessToken, int madn, String username, String password, int makh, String refreshtoken, String customerName) {
        this.msg = msg;
        this.accessToken = accessToken;
        this.madn = madn;
        this.username = username;
        this.password = password;
        this.makh = makh;
        this.refreshtoken = refreshtoken;
        this.customerName = customerName;
    }





    public LoginResult getUser(){
        LoginResult loginResult = new LoginResult(msg, accessToken, madn, username,  password, makh, refreshtoken, customerName);
        return loginResult;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "LoginResult{" +
                "msg='" + msg + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", refreshtoken='" + refreshtoken + '\'' +
                ", madn=" + madn +
                ", makh=" + makh +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
}
