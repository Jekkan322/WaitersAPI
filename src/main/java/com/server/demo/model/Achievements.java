package com.server.demo.model;

import com.server.demo.entities.AchievementsEntity;

public class Achievements {

    private String nameAchievement;
    private String descriptionAchievement;
    private int requiredAmount;

    public Achievements(){

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

    public int getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(int requiredAmount) {
        this.requiredAmount = requiredAmount;
    }
}
