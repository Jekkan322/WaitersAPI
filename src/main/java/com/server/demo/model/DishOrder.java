package com.server.demo.model;

import com.server.demo.entities.MenuEntity;

import javax.persistence.JoinColumn;

public class DishOrder {

    private Long menuId;

    private int amountDishes;

    private Boolean goList;

    public DishOrder(){

    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

    public void setGoList(Boolean goList) {
        this.goList = goList;
    }
}
