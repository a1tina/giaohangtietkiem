package com.example.ghtk.models;

public class OrderItem {
    private String orderId, name, phone, address;
    private int state;
    public OrderItem(String orderId, String name, String phone, String address, int state) {
        this.orderId = orderId;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
