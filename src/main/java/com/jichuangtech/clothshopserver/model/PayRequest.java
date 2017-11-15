package com.jichuangtech.clothshopserver.model;

/**
 * Created by Bingo on 2017/11/13.
 */
public class PayRequest {
    public int totalAmount; //这个是元单位，因为小程序需要分为单位，所以需要*100
    public String orderSn;

    public int getTotalAmount() {
        return totalAmount * 100;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    @Override
    public String toString() {
        return "PayRequest{" +
                "totalAmount=" + totalAmount +
                ", orderSn='" + orderSn + '\'' +
                '}';
    }
}
