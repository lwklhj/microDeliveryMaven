package com.nyp.microdelivery.posting.entity;

public class User {
    public static String userID = "1", username="Hefei";

    public User() {
    }

    public User(String userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
