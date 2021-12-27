package com.server.demo.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class WaitersMissionKey implements Serializable {
    @Column(name="waiters_id")
    Long waitersId;

    @Column(name="mission_id")
    Long missionId;

    public WaitersMissionKey(){

    }

    public WaitersMissionKey(Long waitersId, Long missionId) {
        this.waitersId = waitersId;
        this.missionId = missionId;
    }
}
