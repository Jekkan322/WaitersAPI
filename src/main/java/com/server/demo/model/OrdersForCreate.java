package com.server.demo.model;

import com.server.demo.entities.DishOrderEntity;
import com.server.demo.entities.OrdersEntity;
import com.server.demo.entities.WaitersEntity;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

public class OrdersForCreate {
    private Long id;

    private Long waitersId;

    private java.util.Date orderTime;

    private int orderPrice;

    Set<DishOrder> dishOrder;


    public OrdersForCreate(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWaitersId() {
        return waitersId;
    }

    public void setWaitersId(Long waitersId) {
        this.waitersId = waitersId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Set<DishOrder> getDishOrder() {
        return dishOrder;
    }

    public void setDishOrder(Set<DishOrder> dishOrder) {
        this.dishOrder = dishOrder;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
