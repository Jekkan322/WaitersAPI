package com.server.demo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="waiters")
public class WaitersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String middleName;
    private String position;
    private Long rating;

    @Basic
    @Temporal(TemporalType.DATE)
    private java.util.Date dateOfEntry;

    @OneToMany(mappedBy = "waiters")
    Set<WaitersAchievementsEntity > waitersAchievements;

    @OneToMany(mappedBy = "waiters")
    Set<WaitersMissionEntity > waitersMission;

    @OneToOne(mappedBy = "waiters")
    private User user;



    public void processOrder(OrdersEntity orderEntity){

    }

    public WaitersEntity(){

    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public Set<WaitersAchievementsEntity> getWaitersAchievements() {
        return waitersAchievements;
    }

    public void setWaitersAchievements(Set<WaitersAchievementsEntity> waitersAchievements) {
        this.waitersAchievements = waitersAchievements;
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

    public void setMiddleName(String patronymic) {
        this.middleName = patronymic;
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
