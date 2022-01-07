package com.server.demo.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.demo.model.DishOrder;
import com.server.demo.model.Orders;
import com.server.demo.model.OrdersForCreate;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.MenuRepository;
import com.server.demo.repositories.OrdersRepository;
import com.server.demo.repositories.WaitersRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

@Entity
@Table(name="orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private WaitersEntity waitersEntity;


    private int orderPrice;
    private boolean orderStatus;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date orderTime;

    @OneToMany(mappedBy = "orders")
    Set<DishOrderEntity> dishOrder;



    public static OrdersEntity toEntity(OrdersForCreate orders, WaitersRepository waitersRepository, MenuRepository menuRepository, DishOrderRepository dishOrderRepository, OrdersRepository ordersRepository){
        OrdersEntity ordersEntity=new OrdersEntity();
        ordersEntity.setId(orders.getId());
        ordersEntity.setOrderPrice(orders.getOrderPrice());
        if(orders.getOrderTime()!=null){
            ordersEntity.setOrderTime(orders.getOrderTime());
        }
        else{
            ordersEntity.setOrderTime(Date.from(ZonedDateTime.now().toInstant()));
        }
        ordersEntity.setWaitersEntity(waitersRepository.findById(orders.getWaitersId()).get());
        Set<DishOrderEntity> dishOrderEntitySet=new HashSet<>();
        ordersRepository.save(ordersEntity);
        for(DishOrder dishOrder : orders.getDishOrder()){
            dishOrderEntitySet.add(DishOrderEntity.toEntity(dishOrder,menuRepository,ordersEntity,dishOrderRepository));
        }
        ordersEntity.setDishOrder(dishOrderEntitySet);

        return  ordersEntity;
    }


    public OrdersEntity(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WaitersEntity getWaitersEntity() {
        return waitersEntity;
    }

    public void setWaitersEntity(WaitersEntity waitersEntity) {
        this.waitersEntity = waitersEntity;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Set<DishOrderEntity> getDishOrder() {
        return dishOrder;
    }

    public void setDishOrder(Set<DishOrderEntity> dishOrder) {
        this.dishOrder = dishOrder;
    }
}
