package com.server.demo.model;

import com.server.demo.entities.AchievementsEntity;
import com.server.demo.entities.WaitersAchievementsEntity;
import com.server.demo.entities.WaitersEntity;

public class Achievements {

    private String nameAchievement;
    private String descriptionAchievement;
    private int progressAchievement;
    private int level;
    private int purpose;

    public Achievements(){

    }
    public static Achievements toModel(AchievementsEntity achievementsEntity, WaitersAchievementsEntity waitersAchievementsEntity){
        Achievements model=new Achievements();
        model.setNameAchievement(achievementsEntity.getNameAchievement());
        model.setDescriptionAchievement(achievementsEntity.getDescriptionAchievement());
        model.setLevel(waitersAchievementsEntity.getLevel());
        model.setProgressAchievement(waitersAchievementsEntity.getProgress());
        model.setPurpose(achievementsEntity.getRequiredInitialAmount()+(waitersAchievementsEntity.getLevel()+1)*achievementsEntity.getIncreasingAmountWithNewLevel());
        return model;
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

    public int getProgressAchievement() {
        return progressAchievement;
    }

    public void setProgressAchievement(int progressAchievement) {
        this.progressAchievement = progressAchievement;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPurpose() {
        return purpose;
    }

    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }
}
