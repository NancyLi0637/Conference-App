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
    private ArrayList<String> friendList;

    /**
     * Constructor for the user
     *
     * @param email    the email of the user
     * @param userName the userName of the user
     * @param password the password of the user
     */
    public User(String email, String userName, String password) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.friendList = new ArrayList<String>();
    }

    /**
     * Getter method for this user's friendList
     *
     * @return A copy of the friendList of this user
     */
    public ArrayList<String> friendListGetter() {
        return (ArrayList) friendList.clone();
    }

    /**
     * Add a friend to this user's friendList
     *
     * @param userId the userID of the new friend to add
     */
    public void friendListSetter(String userId) {
        friendList.add(userId);
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
     * Abstract method to get this user's ID, all subclasses should implement this method
     *
     * @return userID for this user
     */
    public abstract String getUserID();

    /**
     * Set the type of this user
     */
    public void setType(String type) {
        this.type = type;
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
     * Set the value of userName
     */
    public void setUsername(String userName) {
        this.userName = userName;
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
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * Override the toString method
     *
     * @return String representation of this user
     */
    @Override
    public String toString() {
        return String.format("entities.User: %s", this.userName);
    }
}