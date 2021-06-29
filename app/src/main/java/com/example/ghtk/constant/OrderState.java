package com.example.ghtk.constant;

public class OrderState {
    public static final int WAITING = 0; // Chờ lấy
    public static final int TAKEN = 1; // Đã lấy
    public static final int DELIVERING = 2; // Đang giao
    public static final int DELIVERY_SUCCESS = 3; // Giao thành công
    public static final int RETURNING = 4;        //Đang duyệt hoàn
    public static final int RETURNING_SUCCESS = 5; // Hoàn thành công
}
