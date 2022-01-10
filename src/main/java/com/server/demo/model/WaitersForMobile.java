package com.server.demo.model;

import com.server.demo.entities.WaitersEntity;

public class WaitersForMobile {
    private String firstName;
    private String lastName;
    private String middleName;
    private int scores;
    private boolean isCurrentUser;

    public WaitersForMobile(){
    }

    public static WaitersForMobile toModel(WaitersEntity waitersEntity,Long id){
        WaitersForMobile model=new WaitersForMobile();
        model.setFirstName(waitersEntity.getFirstName());
        model.setLastName(waitersEntity.getLastName());
        model.setMiddleName(waitersEntity.getMiddleName());
        model.setScores(waitersEntity.getRating().intValue());
        if(waitersEntity.getId()==id){
            model.setCurrentUser(true);
        }
        else{
            model.setCurrentUser(false);
        }
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

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public boolean isCurrentUser() {
        return isCurrentUser;
    }

    public void setCurrentUser(boolean currentUser) {
        isCurrentUser = currentUser;
    }
}
