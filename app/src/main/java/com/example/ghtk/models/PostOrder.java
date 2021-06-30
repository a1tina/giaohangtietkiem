package com.example.ghtk.models;

import java.util.List;

public class PostOrder {
    private List<PackageInfo> dshanghoa;
    private Customer khnhan;
    private String diachidi, diachinhan;
    float chiphi;

    public PostOrder(List<PackageInfo> dshanghoa, Customer khnhan, String diachidi, String diachinhan, float chiphi) {
        this.dshanghoa = dshanghoa;
        this.khnhan = khnhan;
        this.diachidi = diachidi;
        this.diachinhan = diachinhan;
        this.chiphi = chiphi;
    }

    public List<PackageInfo> getDshanghoa() {
        return dshanghoa;
    }

    public void setDshanghoa(List<PackageInfo> dshanghoa) {
        this.dshanghoa = dshanghoa;
    }

    public Customer getKhnhan() {
        return khnhan;
    }

    public void setKhnhan(Customer khnhan) {
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
