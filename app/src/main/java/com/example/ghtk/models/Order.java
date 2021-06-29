package com.example.ghtk.models;

public class Order {
    private String madonhang, nguoinhan, SDT, diachinhan, nguoigui;
    private int trangthai;

    public Order(String madonhang, String nguoinhan, String SDT, String diachinhan, String nguoigui, int trangthai) {
        this.madonhang = madonhang;
        this.nguoinhan = nguoinhan;
        this.SDT = SDT;
        this.diachinhan = diachinhan;
        this.nguoigui = nguoigui;
        this.trangthai = trangthai;
    }

    public String getNguoigui() {
        return nguoigui;
    }

    public void setNguoigui(String nguoigui) {
        this.nguoigui = nguoigui;
    }

    public String getMadonhang() {
        return madonhang;
    }

    public void setMadonhang(String madonhang) {
        this.madonhang = madonhang;
    }

    public String getNguoinhan() {
        return nguoinhan;
    }

    public void setNguoinhan(String nguoinhan) {
        this.nguoinhan = nguoinhan;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
