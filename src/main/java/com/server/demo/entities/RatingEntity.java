package com.server.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(timezone = "GMT+5")
    private java.util.Date timeOfReceipt;

    @ManyToOne
    private WaitersEntity waiters;


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
        return waiters;
    }

    public void setWaitersEntity(WaitersEntity waiters) {
        this.waiters = waiters;
    }
}
