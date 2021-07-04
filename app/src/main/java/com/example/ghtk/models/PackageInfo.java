package com.example.ghtk.models;

public class PackageInfo {
    private String tensp;
    private float cannang;
    private int soluong;

    public PackageInfo(String tensp, float cannang, int soluong) {
        this.tensp = tensp;
        this.cannang = cannang;
        this.soluong = soluong;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public Float getCannang() {
        return cannang;
    }

    public void setCannang(Float cannang) {
        this.cannang = cannang;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
