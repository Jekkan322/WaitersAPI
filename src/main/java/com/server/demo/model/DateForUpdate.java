package com.server.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DateForUpdate {
    @JsonFormat(timezone = "GMT+5")
    private Date personal;
    @JsonFormat(timezone = "GMT+5")
    private Date restaurant;
    @JsonFormat(timezone = "GMT+5")
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
