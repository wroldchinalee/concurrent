package com.lwq.concurrent.disruptor;

/**
 * Created by Administrator on 2019/5/22.
 */
public class TradeTransaction {
    private String id;
    private double price;

    public TradeTransaction() {
    }

    public TradeTransaction(String id, double price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
