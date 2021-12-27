package com.server.demo.model;

import com.server.demo.entities.WaitersAchievementsEntity;
import com.server.demo.entities.WaitersEntity;

import javax.persistence.Basic;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

public class Waiters {
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;

    private String position;
    private Long rating;

    private java.util.Date dateOfEntry;

    public static Waiters toModel(WaitersEntity waitersEntity){
        Waiters model=new Waiters();
        model.setId(waitersEntity.getId());
        model.setFirstName(waitersEntity.getFirstName());
        model.setLastName(waitersEntity.getLastName());
        model.setMiddleName(waitersEntity.getMiddleName());
        model.setFullName(waitersEntity.getLastName()+ " " + waitersEntity.getFirstName()+ " " + waitersEntity.getMiddleName());
        model.setPosition(waitersEntity.getPosition());
        model.setRating(waitersEntity.getRating());
        model.setDateOfEntry(waitersEntity.getDateOfEntry());
        return model;
    }

    public Waiters() {
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
