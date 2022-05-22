package com.server.demo.model;

public class WaitersPlugin {

    public String gUID;
    public String firstName;
    public String lastName;

    public WaitersPlugin(String gUID, String firstName, String lastName) {
        this.gUID = gUID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public WaitersPlugin() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getgUID() {
        return gUID;
    }

    public void setgUID(String gUID) {
        this.gUID = gUID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
