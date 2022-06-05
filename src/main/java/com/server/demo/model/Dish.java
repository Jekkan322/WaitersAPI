package com.server.demo.model;

public class Dish {
    private int id;
    private String name;
    private int price;
    private boolean goList;

    public Dish(int id, String name, int price, boolean goList) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.goList = goList;
    }

    public boolean isGoList() {
        return goList;
    }

    public void setGoList(boolean goList) {
        this.goList = goList;
    }

    public Dish() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
