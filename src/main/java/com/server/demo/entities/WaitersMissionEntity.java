package com.server.demo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="waitersMission")
public class WaitersMissionEntity {
    @EmbeddedId
    private WaitersMissionKey id;

    @ManyToOne
    @MapsId("waitersId")
    @JoinColumn(name="waiters_id")
    private WaitersEntity waiters;

    @ManyToOne
    @MapsId("missionId")
    @JoinColumn(name="mission_id")
    private MissionEntity mission;

    private int progress;




    public WaitersMissionEntity(){

    }

    public WaitersMissionKey getId() {
        return id;
    }

    public void setId(WaitersMissionKey id) {
        this.id = id;
    }

    public WaitersEntity getWaiters() {
        return waiters;
    }

    public void setWaiters(WaitersEntity waiters) {
        this.waiters = waiters;
    }

    public MissionEntity getMission() {
        return mission;
    }

    public void setMission(MissionEntity mission) {
        this.mission = mission;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}
