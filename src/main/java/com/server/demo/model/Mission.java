package com.server.demo.model;

import com.server.demo.entities.MissionEntity;
import com.server.demo.entities.OrdersEntity;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class Mission {
    private Long id;

    private String missionName;
    private String missionDescription;
    private String missionType;

    private Long amountReward;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date deadlineTime;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date dateOfCreation;

    public static Mission toModel(MissionEntity missionEntity){
        Mission model= new Mission();
        model.setId(missionEntity.getId());
        model.setMissionName(missionEntity.getMissionName());
        model.setMissionDescription(missionEntity.getMissionName());
        model.setAmountReward(missionEntity.getAmountReward());
        model.setDeadlineTime(missionEntity.getDeadlineTime());
        model.setDateOfCreation(missionEntity.getDateOfCreation());
        return model;
    }

    public Mission(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDeadlineTime() {
        return deadlineTime;
    }

    public void setDeadlineTime(Date deadlineTime) {
        this.deadlineTime = deadlineTime;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getMissionType() {
        return missionType;
    }

    public void setMissionType(String missionType) {
        this.missionType = missionType;
    }
}
