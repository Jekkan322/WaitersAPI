package com.server.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.WaitersMissionEntity;

import java.util.Date;

public class MissionForMobile {
    private String name;
    private String description;

    private Long prize;
    private boolean completed;
    private int purpose;
    private int progress;
    @JsonFormat(pattern="dd.MM.yyyy")
    private Date deadline;

    public static MissionForMobile toModel(MissionEntity missionEntity, WaitersMissionEntity waitersMissionEntity){
        MissionForMobile model= new MissionForMobile();
        model.setName(missionEntity.getName());
        model.setDescription(missionEntity.getDescription());
        model.setPrize(Math.round(1.0*missionEntity.getAmountReward()/(1.0*missionEntity.getRequirementsAmount()/missionEntity.getPersonalMissionAmount())));
        model.setPurpose(missionEntity.getPersonalMissionAmount());
        model.setProgress(waitersMissionEntity.getProgress());
        if(waitersMissionEntity.getProgress()>=missionEntity.getRequirementsAmount()){
            model.setCompleted(true);
        }
        else{
            model.setCompleted(false);
        }
        model.setDeadline(missionEntity.getDeadlineTime());
        return model;
    }


    public MissionForMobile(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrize() {
        return prize;
    }

    public void setPrize(Long prize) {
        this.prize = prize;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
