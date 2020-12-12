package com.example.a207_demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The entities.VIPUser class, this creates instances of entities.VIPUser to attend a conference.
 */
public class VIPUser extends Attendee implements Serializable {

    /**
     * Constructor No.1 for the VIPUser
     *
     * @param userName the userName of this VIPUser
     * @param email    the email of this VIPUser
     * @param password the password of this VIPUser
     */
    public VIPUser(String userName, String email, String password) {
        super(userName, email, password);
        setType("VIPUser");
        setUserID(UUID.randomUUID().toString().split("-")[0]);
    }

    /**
     * Constructor No.2 for the VIPUser, adding a parameter: ID of this VIPUser.
     *
     * @param userName the userName of this VIPUser
     * @param email    the email of this VIPUser
     * @param password the password of this VIPUser
     * @param ID       the user ID of this VIPUser
     */
    public VIPUser(String userName, String email, String password, String ID,
                   ArrayList<String> friendsID, ArrayList<String> announcements) {
        super(userName, email, password);
        setType("VIPUser");
        setUserID(ID);
        setFriendList(friendsID);
        setAnnouncements(announcements);
    }

}
