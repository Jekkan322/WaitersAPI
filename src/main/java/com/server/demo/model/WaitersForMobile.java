package com.server.demo.model;

import com.server.demo.entities.WaitersEntity;

public class WaitersForMobile {
    private String firstName;
    private String lastName;
    private String middleName;
    private Long rating;

    public WaitersForMobile(){
    }

    public static WaitersForMobile toModel(WaitersEntity waitersEntity){
        WaitersForMobile model=new WaitersForMobile();
        model.setFirstName(waitersEntity.getFirstName());
        model.setLastName(waitersEntity.getLastName());
        model.setMiddleName(waitersEntity.getMiddleName());
        model.setRating(waitersEntity.getRating());
        return model;
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

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
