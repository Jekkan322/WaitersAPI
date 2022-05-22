package com.server.demo.entities;

import com.server.demo.model.DishOrder;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.MenuRepository;
import com.server.demo.repositories.OrdersRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="dishOrder")
public class DishOrderEntity {
    @EmbeddedId
    private DishOrderKey id;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name="menu_id")
    private MenuEntity menu;

    @ManyToOne
    @MapsId("ordersId")
    @JoinColumn(name="orders_id")
    private OrdersEntity orders;

    private int amountDishes;
    @Column(columnDefinition="bool default false")
    private boolean goList;


    public static DishOrderEntity toEntity(DishOrder dishOrder, MenuRepository menuRepository, OrdersEntity ordersEntity, DishOrderRepository dishOrderRepository){
        DishOrderEntity dishOrderEntity = new DishOrderEntity();
        dishOrderEntity.setId(new DishOrderKey(dishOrder.getMenuIndex(),ordersEntity.getId()));
        dishOrderEntity.setOrders(ordersEntity);
        dishOrderEntity.setMenu(menuRepository.findById(dishOrder.getMenuIndex()).get());
        dishOrderEntity.setAmountDishes(dishOrder.getAmountDishes());
        dishOrderEntity.setGoList(dishOrder.isGoList());
        dishOrderRepository.save(dishOrderEntity);
        return dishOrderEntity;
    }

    public DishOrderEntity(){

    }

    public DishOrderKey getId() {
        return id;
    }

    public void setId(DishOrderKey id) {
        this.id = id;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }

    public int getAmountDishes() {
        return amountDishes;
    }

    public void setAmountDishes(int amountDishes) {
        this.amountDishes = amountDishes;
    }

    public boolean isGoList() {
        return goList;
    }

    public void setGoList(boolean goList) {
        this.goList = goList;
    }
}
