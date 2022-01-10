package com.server.demo.model;

import java.util.Date;

public class DateForUpdate {
    private Date personal;
    private Date restaurant;
    private Date leaderboard;

    public DateForUpdate(){

    }


    public Date getPersonal() {
        return personal;
    }

    public void setPersonal(Date personal) {
        this.personal = personal;
    }

    public Date getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Date restaurant) {
        this.restaurant = restaurant;
    }

    public Date getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(Date leaderboard) {
        this.leaderboard = leaderboard;
    }
}
