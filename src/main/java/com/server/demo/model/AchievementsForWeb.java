package com.server.demo.model;

import com.server.demo.entities.AchievementsEntity;
import com.server.demo.entities.MissionEntity;

public class AchievementsForWeb {
    private Long id;
    private String name;
    private String description;
    private int requiredInitialAmount;
    private int levelIncreaseFactor;
    private String url;
    private String achievementsType;

    public AchievementsForWeb() {
    }

    public AchievementsForWeb(Long id, String name, String description, int requiredInitialAmount, int levelIncreaseFactor, String url, String achievementsType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requiredInitialAmount = requiredInitialAmount;
        this.levelIncreaseFactor = levelIncreaseFactor;
        this.url = url;
        this.achievementsType = achievementsType;
    }

    public static AchievementsForWeb toModel(AchievementsEntity achievementsEntity){
        AchievementsForWeb model = new AchievementsForWeb();
        model.setId(achievementsEntity.getId());
        model.setName(achievementsEntity.getName());
        model.setDescription(achievementsEntity.getDescription());
        model.setRequiredInitialAmount(achievementsEntity.getRequiredInitialAmount());
        model.setLevelIncreaseFactor(achievementsEntity.getLevelIncreaseFactor());
        model.setAchievementsType(achievementsEntity.getType());
        model.setUrl(achievementsEntity.getPictureURL());
        return model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAchievementsType() {
        return achievementsType;
    }

    public void setAchievementsType(String achievementsType) {
        this.achievementsType = achievementsType;
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
}
