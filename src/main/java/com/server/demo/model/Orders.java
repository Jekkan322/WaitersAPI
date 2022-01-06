package com.server.demo.model;

import com.server.demo.entities.OrdersEntity;
import com.server.demo.entities.WaitersEntity;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Orders{
    private Long id;

    private Long waitersEntityId;

    private java.util.Date orderTime;

    private boolean orderStatus;

    private int orderPrice;

    public Orders(){

    }

    public static Orders toModel(OrdersEntity ordersEntity){
        Orders model= new Orders();
        model.setId(ordersEntity.getId());
        model.setWaitersEntityId(ordersEntity.getWaitersEntity().getId());
        model.setOrderTime(ordersEntity.getOrderTime());
        model.setOrderStatus(ordersEntity.isOrderStatus());
        model.setOrderPrice(ordersEntity.getOrderPrice());
        return model;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWaitersEntityId() {
        return waitersEntityId;
    }

    public void setWaitersEntityId(Long waitersEntityId) {
        this.waitersEntityId = waitersEntityId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }
}
