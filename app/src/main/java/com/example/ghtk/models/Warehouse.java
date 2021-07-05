package com.example.ghtk.models;

public class Warehouse {
    private int makho;
    private String tenkho, diachi, tinh, vido, kinhdo;

    public Warehouse(int makho, String tenkho, String diachi, String tinh, String vido, String kinhdo) {
        this.makho = makho;
        this.tenkho = tenkho;
        this.diachi = diachi;
        this.tinh = tinh;
        this.vido = vido;
        this.kinhdo = kinhdo;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "makho=" + makho +
                ", tenkho='" + tenkho + '\'' +
                ", diachi='" + diachi + '\'' +
                ", tinh='" + tinh + '\'' +
                ", vido='" + vido + '\'' +
                ", kinhdo='" + kinhdo + '\'' +
                '}';
    }

    public int getMakho() {
        return makho;
    }

    public void setMakho(int makho) {
        this.makho = makho;
    }

    public String getTenkho() {
        return tenkho;
    }

    public void setTenkho(String tenkho) {
        this.tenkho = tenkho;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getTinh() {
        return tinh;
    }

    public void setTinh(String tinh) {
        this.tinh = tinh;
    }

    public String getVido() {
        return vido;
    }

    public void setVido(String vido) {
        this.vido = vido;
    }

    public String getKinhdo() {
        return kinhdo;
    }

    public void setKinhdo(String kinhdo) {
        this.kinhdo = kinhdo;
    }

}
