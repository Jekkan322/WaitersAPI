package com.server.demo.model;

public class Statistics {
    private int revenue;
    private int goList;
    private int orders;
    private int rating;
    private String firstName;
    private String lastName;
    private int averageCheque;


    public Statistics(){

    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getGoList() {
        return goList;
    }

    public void setGoList(int goList) {
        this.goList = goList;
    }

    public int getAverageCheque() {
        return averageCheque;
    }

    public void setAverageCheque(int averageCheque) {
        this.averageCheque = averageCheque;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
