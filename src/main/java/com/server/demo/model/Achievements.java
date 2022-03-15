package com.server.demo.model;

import com.server.demo.entities.WaitersAchievementsEntity;

public class Achievements {

    private String name;
    private String description;
    private int progress;
    private int level;
    private int purpose;
    private String ImageUrl;

    public Achievements(){

    }
    public static Achievements toModel(WaitersAchievementsEntity waitersAchievementsEntity){
        Achievements model=new Achievements();
        model.setName(waitersAchievementsEntity.getAchievements().getName());
        model.setDescription(waitersAchievementsEntity.getAchievements().getDescription());
        model.setLevel(waitersAchievementsEntity.getLevel());
        model.setProgress(waitersAchievementsEntity.getProgress());
        model.setImageUrl(waitersAchievementsEntity.getAchievements().getPictureURL());
        model.setPurpose(waitersAchievementsEntity.getAchievements().getRequiredInitialAmount()+(waitersAchievementsEntity.getLevel()*(waitersAchievementsEntity.getLevel()*waitersAchievementsEntity.getAchievements().getLevelIncreaseFactor())));
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

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
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

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
