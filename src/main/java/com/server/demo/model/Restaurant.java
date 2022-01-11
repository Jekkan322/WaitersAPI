package com.server.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private List<MissionsOfRestaurant> statistics;

    public Restaurant(){

    }

    public Restaurant(String name, List<MissionsOfRestaurant> statistics) {
        statistics=new ArrayList<>();
        this.name = name;
        this.statistics=statistics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MissionsOfRestaurant> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<MissionsOfRestaurant> statistics) {
        this.statistics = statistics;
    }
}
