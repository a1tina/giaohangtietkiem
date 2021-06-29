package com.example.ghtk.models;

public class PackageInfo {
    private String tensp, urlImage;
    private float cannang;
    private int soluong;

    public PackageInfo(String tensp, String urlImage, float cannang, int soluong) {
        this.tensp = tensp;
        this.urlImage = urlImage;
        this.cannang = cannang;
        this.soluong = soluong;
    }

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

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
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
