package com.server.demo.model;

public class DataUpdate {
    private boolean personal;
    private boolean restaurant;
    private boolean leaderboard;

    public DataUpdate(){

    }

    public DataUpdate(boolean personal, boolean restaurant, boolean leaderboard) {
        this.personal = personal;
        this.restaurant = restaurant;
        this.leaderboard = leaderboard;
    }

    public boolean isPersonal() {
        return personal;
    }

    public void setPersonal(boolean personal) {
        this.personal = personal;
    }

    public boolean isRestaurant() {
        return restaurant;
    }

    public void setRestaurant(boolean restaurant) {
        this.restaurant = restaurant;
    }

    public boolean isLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(boolean leaderboard) {
        this.leaderboard = leaderboard;
    }
}
