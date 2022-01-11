package com.server.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MissionsOfRestaurant {
    private String name;
    private int progress;


    @JsonFormat(pattern="dd.MM.yyyy")
    private Date deadLine;
    private int purpose;

    public MissionsOfRestaurant(){

    }

    public MissionsOfRestaurant(String name, int progress, Date deadLine,int purpose) {
        this.name = name;
        this.progress = progress;
        this.deadLine = deadLine;
        this.purpose=purpose;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }
}
