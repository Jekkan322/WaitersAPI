package com.server.demo.model;

import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.WaitersMissionEntity;

public class MissionForMobile {
    private String missionName;
    private String missionDescription;

    private Long amountReward;
    private boolean completed;
    private Long requirementsAmount;
    private int progress;

    public static MissionForMobile toModel(MissionEntity missionEntity, WaitersMissionEntity waitersMissionEntity){
        MissionForMobile model= new MissionForMobile();
        model.setMissionName(missionEntity.getMissionName());
        model.setMissionDescription(missionEntity.getMissionDescription());
        model.setAmountReward(missionEntity.getAmountReward());
        model.setRequirementsAmount(missionEntity.getRequirementsAmount());
        model.setProgress(waitersMissionEntity.getProgress());
        if(waitersMissionEntity.getProgress()>=missionEntity.getRequirementsAmount()){
            model.setCompleted(true);
        }
        else{
            model.setCompleted(false);
        }
        return model;
    }


    public MissionForMobile(){

    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionDescription() {
        return missionDescription;
    }

    public void setMissionDescription(String missionDescription) {
        this.missionDescription = missionDescription;
    }

    public Long getAmountReward() {
        return amountReward;
    }

    public void setAmountReward(Long amountReward) {
        this.amountReward = amountReward;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getRequirementsAmount() {
        return requirementsAmount;
    }

    public void setRequirementsAmount(Long requirementsAmount) {
        this.requirementsAmount = requirementsAmount;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
