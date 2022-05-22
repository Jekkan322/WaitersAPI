package com.server.demo.model;

import java.util.ArrayList;
import java.util.Date;

public class OrdersPlugin {

    private Long id;
    private String waiterId;
    private int fullSum;

    private java.util.Date openTime;

    private ArrayList<DishOrder> items;

    public OrdersPlugin() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public int getFullSum() {
        return fullSum;
    }

    public void setFullSum(int fullSum) {
        this.fullSum = fullSum;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }


    public ArrayList<DishOrder> getItems() {
        return items;
    }

    public void setItems(ArrayList<DishOrder> items) {
        this.items = items;
    }
}
