package com.example.a207_demo.entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The entities.User class, this is used as a superclass for Attendees, Speakers and Organizers.
 * It is abstract because it does not actually contain users.
 */
public abstract class User implements Serializable {

    private String userName;
    private String email;
    private String password;
    private String type;
    private String userID;
    private ArrayList<String> friendList;
    private ArrayList<String> announcements;

    /**
     * Constructor for the user
     *
     * @param email    the email of the user
     * @param userName the userName of the user
     * @param password the password of the user
     */
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    /**
     * Set the value of userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * Set the type of this user
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the value of password
     *
     * @param password new value of email
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the value of userID
     *
     * @param userID the userID of the user
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * setFriendList
     * @param friendList ArrayList<String> friendList
     */
    public void setFriendList(ArrayList<String> friendList) {
        this.friendList = friendList;
    }

    /**
     * setAnnouncements
     * @param announcements ArrayList<String> announcements
     */
    public void setAnnouncements(ArrayList<String> announcements) {
        this.announcements = announcements;
    }

    /**
     * Add a friend to this user's friendList
     *
     * @param userId the userID of the new friend to add
     */
    public void addFriend(String userId) {
        if (!friendList.contains(userId)) friendList.add(userId);
    }

    /**
     * addAnnouncement
     *
     * @param announcement String announcement
     */
    public void addAnnouncement(String announcement) {
        this.announcements.add(announcement);
    }

    /**
     * Get the value of userName
     *
     * @return the value of lastName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Get the type of this user
     *
     * @return the type of this user
     */
    public String getType() {
        return type;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the password of this user
     *
     * @return the value of password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * AGet this user's ID
     *
     * @return userID for this user
     */
    public String getUserID() {
        return this.userID;
    }


    /**
     * Getter method for this user's friendList
     *
     * @return A copy of the friendList of this user
     */
    public ArrayList<String> getFriendList() {

        return friendList;
    }


    /**
     * getAnnouncements
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getAnnouncements() {
        return this.announcements;
    }

    ;

    /**
     * Override the toString method
     *
     * @return String representation of this user
     */
    @Override
    public String toString() {
        return String.format("entities.User: %s", this.userName);
    }
}