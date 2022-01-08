package com.server.demo.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="rating")
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long rating;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date timeOfReceipt;

    @ManyToOne
    private WaitersEntity waitersEntity;


    public RatingEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public Date getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public void setTimeOfReceipt(Date timeOfReceipt) {
        this.timeOfReceipt = timeOfReceipt;
    }

    public WaitersEntity getWaitersEntity() {
        return waitersEntity;
    }

    public void setWaitersEntity(WaitersEntity waitersEntity) {
        this.waitersEntity = waitersEntity;
    }
}
