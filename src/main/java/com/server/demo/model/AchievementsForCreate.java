package com.server.demo.model;

import com.server.demo.entities.AchievementsEntity;
import com.server.demo.entities.WaitersAchievementsEntity;

public class AchievementsForCreate {
    private String name;
    private String description;
    private int requiredInitialAmount;
    private int levelIncreaseFactor;
    private String pictureURL;

    private String achievementsType;

    public AchievementsForCreate() {
    }

    public static AchievementsForCreate toModel(AchievementsEntity achievementsEntity){
        AchievementsForCreate model=new AchievementsForCreate();
        model.setName(achievementsEntity.getName());
        model.setDescription(achievementsEntity.getDescription());
        model.setLevelIncreaseFactor(achievementsEntity.getLevelIncreaseFactor());
        model.setPictureURL(achievementsEntity.getPictureURL());
        return model;
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

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getAchievementsType() {
        return achievementsType;
    }

    public void setAchievementsType(String achievementsType) {
        this.achievementsType = achievementsType;
    }
}
