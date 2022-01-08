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
    public static Achievements toModel(WaitersAchievementsEntity waitersAchievementsEntity){
        Achievements model=new Achievements();
        model.setNameAchievement(waitersAchievementsEntity.getAchievements().getNameAchievement());
        model.setDescriptionAchievement(waitersAchievementsEntity.getAchievements().getDescriptionAchievement());
        model.setLevel(waitersAchievementsEntity.getLevel());
        model.setProgressAchievement(waitersAchievementsEntity.getProgress());
        model.setPurpose(waitersAchievementsEntity.getAchievements().getRequiredInitialAmount()+(waitersAchievementsEntity.getLevel()+1)*waitersAchievementsEntity.getAchievements().getIncreasingAmountWithNewLevel());
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
