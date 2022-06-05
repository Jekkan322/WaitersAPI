package com.server.demo.entities;


import com.server.demo.model.AchievementsForCreate;
import com.server.demo.repositories.DishOrderRepository;
import com.server.demo.repositories.WaitersAchievementsRepository;
import com.server.demo.repositories.WaitersRepository;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="achievements")
@Inheritance
@DiscriminatorColumn(name="type")
public abstract class AchievementsEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private int requiredInitialAmount;
    private int levelIncreaseFactor;
    private String pictureURL;
    /*private Long initialReward;
    private Long increasingRewardWithNewLevel;*/

    @OneToMany(mappedBy = "achievements")
    Set<WaitersAchievementsEntity> waitersAchievements;

    public AchievementsEntity(AchievementsForCreate achievementsForCreate) {
        this.name = achievementsForCreate.getName();
        this.description = achievementsForCreate.getDescription();
        this.requiredInitialAmount = achievementsForCreate.getRequiredInitialAmount();
        this.levelIncreaseFactor = achievementsForCreate.getLevelIncreaseFactor();
        this.pictureURL = achievementsForCreate.getPictureURL();
    }

    public AchievementsEntity(){

    }
    abstract public void  processOrder(OrdersEntity ordersEntity, WaitersAchievementsRepository waitersAchievementsRepository,
                                       WaitersRepository waitersRepository, DishOrderRepository dishOrderRepository);

    abstract public String getType();



    public int getRequiredInitialAmount() {
        return requiredInitialAmount;
    }

    public void setRequiredInitialAmount(int requiredInitialAmount) {
        this.requiredInitialAmount = requiredInitialAmount;
    }

    public int getLevelIncreaseFactor() {
        return levelIncreaseFactor;
    }

    public void setLevelIncreaseFactor(int levelIncreaseFactor) {
        this.levelIncreaseFactor = levelIncreaseFactor;
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
