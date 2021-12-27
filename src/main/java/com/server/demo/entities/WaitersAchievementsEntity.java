package com.server.demo.entities;


import javax.persistence.*;

@Entity
@Table(name="WaitersAchievements")
public class WaitersAchievementsEntity {
    @EmbeddedId
    private WaitersAchievementsKey id;


    @ManyToOne
    @MapsId("waitersId")
    @JoinColumn(name="waiters_id")
    private WaitersEntity waiters;

    @ManyToOne
    @MapsId("achievementsId")
    @JoinColumn(name="achievements_id")
    private AchievementsEntity achievements;

    private int progress;

    private int level;

    public WaitersAchievementsEntity(){

    }

    public WaitersAchievementsKey getId() {
        return id;
    }

    public void setId(WaitersAchievementsKey id) {
        this.id = id;
    }

    public WaitersEntity getWaiters() {
        return waiters;
    }

    public void setWaiters(WaitersEntity waiters) {
        this.waiters = waiters;
    }

    public AchievementsEntity getAchievements() {
        return achievements;
    }

    public void setAchievements(AchievementsEntity achievements) {
        this.achievements = achievements;
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
}
