package com.server.demo.entities;


import com.server.demo.repositories.AchievementsRepository;
import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="achievements")
@Inheritance
@DiscriminatorColumn(name="typeAchievement")
public abstract class AchievementsEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameAchievement;
    private String descriptionAchievement;
    private int requiredInitialAmount;
    private int increasingAmountWithNewLevel;
    private Long initialReward;
    private Long increasingRewardWithNewLevel;

    @OneToMany(mappedBy = "achievements")
    Set<WaitersAchievementsEntity> waitersAchievements;

    public AchievementsEntity(){

    }
    abstract public void  processOrder(OrdersEntity ordersEntity, WaitersAchievementsRepository waitersAchievementsRepository,WaitersRepository waitersRepository);



    public int getRequiredInitialAmount() {
        return requiredInitialAmount;
    }

    public void setRequiredInitialAmount(int requiredInitialAmount) {
        this.requiredInitialAmount = requiredInitialAmount;
    }

    public int getIncreasingAmountWithNewLevel() {
        return increasingAmountWithNewLevel;
    }

    public void setIncreasingAmountWithNewLevel(int increasingAmountWithNewLevel) {
        this.increasingAmountWithNewLevel = increasingAmountWithNewLevel;
    }

    public Long getInitialReward() {
        return initialReward;
    }

    public void setInitialReward(Long initialReward) {
        this.initialReward = initialReward;
    }

    public Long getIncreasingRewardWithNewLevel() {
        return increasingRewardWithNewLevel;
    }

    public void setIncreasingRewardWithNewLevel(Long increasingRewardWithNewLevel) {
        this.increasingRewardWithNewLevel = increasingRewardWithNewLevel;
    }

    public Set<WaitersAchievementsEntity> getWaitersAchievements() {
        return waitersAchievements;
    }

    public void setWaitersAchievements(Set<WaitersAchievementsEntity> waitersAchievements) {
        this.waitersAchievements = waitersAchievements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameAchievement() {
        return nameAchievement;
    }

    public void setNameAchievement(String nameAchievement) {
        this.nameAchievement = nameAchievement;
    }

    public String getDescriptionAchievement() {
        return descriptionAchievement;
    }

    public void setDescriptionAchievement(String descriptionAchievement) {
        this.descriptionAchievement = descriptionAchievement;
    }
}
