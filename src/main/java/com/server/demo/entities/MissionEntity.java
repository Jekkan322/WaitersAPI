package com.server.demo.entities;


import com.fasterxml.jackson.annotation.*;
import com.server.demo.model.Mission;
import com.server.demo.model.MissionForCreate;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.RatingRepository;
import com.server.demo.repositories.WaitersMissionRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance
@DiscriminatorColumn(name="missionType")
@Table(name="mission")
public abstract class MissionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String missionName;
    private String missionDescription;

    private Long amountReward;
    private int requirementsForTheFirstAward;
    private Long requirementsAmount;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date deadlineTime;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date dateOfCreation;

    @OneToMany(mappedBy = "mission")
    Set<WaitersMissionEntity> waitersMission;

    abstract public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository,
                                      WaitersRepository waitersRepository,RatingRepository ratingRepository, DishOrderRepository dishOrderRepository);

    public MissionEntity(){

    }

    public MissionEntity(MissionForCreate missionForCreate) {
        this.missionName = missionForCreate.getMissionName();
        this.missionDescription = missionForCreate.getMissionDescription();
        this.amountReward = missionForCreate.getAmountReward();
        this.requirementsForTheFirstAward = missionForCreate.getRequirementsForTheFirstAward();
        this.requirementsAmount = missionForCreate.getRequirementsAmount();
        this.deadlineTime = missionForCreate.getDeadlineTime();
        this.dateOfCreation = missionForCreate.getDateOfCreation();
    }

    /*@JsonCreator
    MissionEntity(@JsonProperty("name") String missionName,@JsonProperty("description") String missionDescription,
                  @JsonProperty("prize") Long amountReward,@JsonProperty("personalPurpose") int requirementsForTheFirstAward,
                  @JsonProperty("purpose") Long requirementsAmount,@JsonProperty("deadline") Date deadlineTime,
                  @JsonProperty("creationDate") Date dateOfCreation) {
        this.missionName = missionName;
        this.missionDescription=missionDescription;
        this.amountReward=amountReward;
        this.requirementsForTheFirstAward=requirementsForTheFirstAward;
        this.requirementsAmount=requirementsAmount;
        this.deadlineTime=deadlineTime;
        this.dateOfCreation=dateOfCreation;
    }*/


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmountReward(Long amountReward) {
        this.amountReward = amountReward;
    }

    public Long getAmountReward() {
        return amountReward;
    }

    public Long getRequirementsAmount() {
        return requirementsAmount;
    }

    public void setRequirementsAmount(Long requirementsAmount) {
        this.requirementsAmount = requirementsAmount;
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


    public int getRequirementsForTheFirstAward() {
        return requirementsForTheFirstAward;
    }

    public void setRequirementsForTheFirstAward(int requirementsForTheFirstAward) {
        this.requirementsForTheFirstAward = requirementsForTheFirstAward;
    }

    public Set<WaitersMissionEntity> getWaitersMission() {
        return waitersMission;
    }

    public void setWaitersMission(Set<WaitersMissionEntity> waitersMission) {
        this.waitersMission = waitersMission;
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
}
