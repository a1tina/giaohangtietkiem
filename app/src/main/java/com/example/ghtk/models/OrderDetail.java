package com.example.ghtk.models;

import java.util.List;

public class OrderDetail {
    private List<PackageInfo> dshanghoa;
    private ReceiveCustomer khnhan;
    private String diachidi, diachinhan, image;
    float chiphi;

    public OrderDetail(List<PackageInfo> dshanghoa, ReceiveCustomer khnhan, String diachidi, String diachinhan, String urlImage, float chiphi) {
        this.dshanghoa = dshanghoa;
        this.khnhan = khnhan;
        this.diachidi = diachidi;
        this.diachinhan = diachinhan;
        this.image = urlImage;
        this.chiphi = chiphi;
    }

    public String getUrlImage() {
        return image;
    }

    public void setUrlImage(String urlImage) {
        this.image = urlImage;
    }

    public List<PackageInfo> getDshanghoa() {
        return dshanghoa;
    }

    public void setDshanghoa(List<PackageInfo> dshanghoa) {
        this.dshanghoa = dshanghoa;
    }

    public ReceiveCustomer getKhnhan() {
        return khnhan;
    }

    public void setKhnhan(ReceiveCustomer khnhan) {
        this.khnhan = khnhan;
    }

    public String getDiachidi() {
        return diachidi;
    }

    public void setDiachidi(String diachidi) {
        this.diachidi = diachidi;
    }

    public String getDiachinhan() {
        return diachinhan;
    }

    public void setDiachinhan(String diachinhan) {
        this.diachinhan = diachinhan;
    }

    public float getChiphi() {
        return chiphi;
    }

    public void setChiphi(float chiphi) {
        this.chiphi = chiphi;
    }
}
