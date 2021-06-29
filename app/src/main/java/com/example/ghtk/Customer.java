package com.example.ghtk;

public class Customer {
    private int MaKH;
    private String TenKH, SDT, DiaChi;
    private int madn;
    private String username, password;
    private int makh;
    private String refreshtoken;

    public Customer(int maKH, String tenKH, String SDT, String diaChi, int madn, String username, String password, int makh, String refreshtoken) {
        this.MaKH = maKH;
        this.TenKH = tenKH;
        this.SDT = SDT;
        this.DiaChi = diaChi;
        this.madn = madn;
        this.username = username;
        this.password = password;
        this.makh = makh;
        this.refreshtoken = refreshtoken;
    }

    public Customer getProfile(){
        Customer customer = new Customer(MaKH, TenKH, SDT, DiaChi, madn, username, password, makh, refreshtoken);
        return customer;
    }

    public int getMaKH() {
        return MaKH;
    }

    public void setMaKH(int maKH) {
        MaKH = maKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }


    public int getMadn() {
        return madn;
    }

    public void setMadn(int madn) {
        this.madn = madn;
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

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getRefreshtoken() {
        return refreshtoken;
    }

    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }
}
