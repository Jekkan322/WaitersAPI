package com.server.demo.model;

public class Restaurant {
    private String name;
    private MissionsOfRestaurant statistics;

    public Restaurant(){

    }

    public Restaurant(String name, MissionsOfRestaurant statistics) {
        this.name = name;
        this.statistics=statistics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MissionsOfRestaurant getStatistics() {
        return statistics;
    }

    public void setStatistics(MissionsOfRestaurant statistics) {
        this.statistics = statistics;
    }
}
