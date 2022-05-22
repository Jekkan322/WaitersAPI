package com.server.demo.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.demo.model.DishOrder;
import com.server.demo.model.Orders;
import com.server.demo.model.OrdersForCreate;
import com.server.demo.model.OrdersPlugin;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.MenuRepository;
import com.server.demo.repositories.OrdersRepository;
import com.server.demo.repositories.WaitersRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.*;
import java.time.ZonedDateTime;
import java.util.logging.Logger;

@Entity
@Table(name="orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /*@ManyToOne
    private WaitersEntity waitersEntity;*/

    @ManyToOne
    private WaitersEntity waiters;


    private int orderPrice;
    private boolean orderStatus;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+5")
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



    public static OrdersEntity toNewEntity(OrdersPlugin orders, WaitersRepository waitersRepository, MenuRepository menuRepository, DishOrderRepository dishOrderRepository, OrdersRepository ordersRepository){
        OrdersEntity ordersEntity=new OrdersEntity();
        ordersEntity.setId(orders.getId());
        ordersEntity.setOrderPrice(orders.getFullSum());
        if(orders.getOpenTime()!=null){
            ordersEntity.setOrderTime(orders.getOpenTime());
        }
        else{
            ordersEntity.setOrderTime(Date.from(ZonedDateTime.now().toInstant()));
        }
        if(!waitersRepository.findByUUID(orders.getWaiterId()).isPresent()){
            WaitersEntity waitersEntity=new WaitersEntity();
            waitersEntity.setUUID(orders.getWaiterId());
            waitersRepository.save(waitersEntity);
        }
        ordersEntity.setWaitersEntity(waitersRepository.findByUUID(orders.getWaiterId()).get());
        Set<DishOrderEntity> dishOrderEntitySet=new HashSet<>();
        ordersRepository.save(ordersEntity);
        for(DishOrder dishOrder : orders.getItems()){
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
        return waiters;
    }

    public void setWaitersEntity(WaitersEntity waiters) {
        this.waiters = waiters;
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
