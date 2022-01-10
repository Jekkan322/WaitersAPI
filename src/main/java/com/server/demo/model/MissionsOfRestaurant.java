package com.server.demo.model;

import java.util.Date;

public class MissionsOfRestaurant {
    private String mission;
    private int progress;
    private Date deadLine;

    public MissionsOfRestaurant(){

    }

    public MissionsOfRestaurant(String mission, int progress, Date deadLine) {
        this.mission = mission;
        this.progress = progress;
        this.deadLine = deadLine;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
}
