package com.server.demo.model;

public class DishOrder {

    private Long menuIndex;

    private int amountDishes;

    private Boolean goList;

    public DishOrder(){

    }

    public DishOrder(Long menuIndex, int amountDishes, Boolean goList) {
        this.menuIndex = menuIndex;
        this.amountDishes = amountDishes;
        this.goList = goList;
    }

    public Long getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(Long menuIndex) {
        this.menuIndex = menuIndex;
    }

    public Boolean getGoList() {
        return goList;
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
