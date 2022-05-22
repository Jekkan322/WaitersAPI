package com.server.demo.model;

import com.server.demo.entities.AchievementsEntity;
import com.server.demo.entities.MissionEntity;

public class AchievementsForWeb {
    private Long id;
    private String name;
    private String description;
    private int requiredInitialAmount;
    private int levelIncreaseFactor;

    public AchievementsForWeb() {
    }

    public AchievementsForWeb(Long id, String name, String description, int requiredInitialAmount, int levelIncreaseFactor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.requiredInitialAmount = requiredInitialAmount;
        this.levelIncreaseFactor = levelIncreaseFactor;
    }

    public static AchievementsForWeb toModel(AchievementsEntity achievementsEntity){
        AchievementsForWeb model = new AchievementsForWeb();
        model.setId(achievementsEntity.getId());
        model.setName(achievementsEntity.getName());
        model.setDescription(achievementsEntity.getDescription());
        model.setRequiredInitialAmount(achievementsEntity.getRequiredInitialAmount());
        model.setLevelIncreaseFactor(achievementsEntity.getLevelIncreaseFactor());
        return model;
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
