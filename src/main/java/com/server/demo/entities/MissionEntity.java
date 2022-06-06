package com.server.demo.entities;


import com.server.demo.model.MissionForCreate;
import com.server.demo.repositories.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Inheritance
@DiscriminatorColumn(name="type")
@Table(name="mission")
public abstract class MissionEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    private Long amountReward;
    private int personalMissionAmount;
    private Long requirementsAmount;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date deadlineTime;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date dateOfCreation;

    @OneToMany(mappedBy = "mission",cascade = CascadeType.REMOVE)
    Set<WaitersMissionEntity> waitersMission;

    abstract public void processOrder(OrdersEntity ordersEntity, WaitersMissionRepository waitersMissionRepository,
                                      WaitersRepository waitersRepository,RatingRepository ratingRepository, DishOrderRepository dishOrderRepository);

    /*abstract public Integer calcProgress(Date date, OrdersRepository ordersRepository,DishOrderRepository dishOrderRepository);*/

    abstract public Integer calcProgress(OrdersRepository ordersRepository,DishOrderRepository dishOrderRepository);

    abstract public String getType();

    public MissionEntity(){

    }

    public MissionEntity(MissionForCreate missionForCreate) {
        this.name = missionForCreate.getMissionName();
        this.description = missionForCreate.getMissionDescription();
        this.amountReward = missionForCreate.getAmountReward();
        this.personalMissionAmount = missionForCreate.getRequirementsForTheFirstAward();
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


    public int getPersonalMissionAmount() {
        return personalMissionAmount;
    }

    public void setPersonalMissionAmount(int personalMissionAmount) {
        this.personalMissionAmount = personalMissionAmount;
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
