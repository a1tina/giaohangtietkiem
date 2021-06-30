package com.example.ghtk.constant;

public class OrderState {
    public static final int WAITING = 1; // Chờ lấy
    public static final int TAKEN = 2; // Đã lấy
    public static final int DELIVERING = 3; // Đang giao
    public static final int DELIVERY_SUCCESS = 4; // Giao thành công
    public static final int RETURNING = 5;        //Đang duyệt hoàn
    public static final int RETURNING_SUCCESS = 6; // Hoàn thành công
}
