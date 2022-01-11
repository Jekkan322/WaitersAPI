package com.server.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class MissionForCreate {

    private String missionName;
    private String missionDescription;

    private Long amountReward;
    private int requirementsForTheFirstAward;
    private Long requirementsAmount;

    @JsonFormat(pattern="dd.MM.yyyy")
    private java.util.Date deadlineTime;
    @JsonFormat(pattern="dd.MM.yyyy")
    private java.util.Date dateOfCreation;

    private String missionType;

    public MissionForCreate(){

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

    public int getRequirementsForTheFirstAward() {
        return requirementsForTheFirstAward;
    }

    public void setRequirementsForTheFirstAward(int requirementsForTheFirstAward) {
        this.requirementsForTheFirstAward = requirementsForTheFirstAward;
    }

    public Long getRequirementsAmount() {
        return requirementsAmount;
    }

    public void setRequirementsAmount(Long requirementsAmount) {
        this.requirementsAmount = requirementsAmount;
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
