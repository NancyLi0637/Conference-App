package com.example.a207_demo.speakerSystem;

import com.example.a207_demo.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The entities.Speaker class, this creates instances of an entities.Speaker for a conference.
 */
public class Speaker extends User implements Serializable {

    /**
     * Constructor No.1 for the speaker
     *
     * @param userName the userName of this speaker
     * @param email    the email of this speaker
     * @param password the password of this speaker
     */
    public Speaker(String userName, String email, String password) {
        super(userName, email, password);
        setType("SPEAKER");
       setUserID(UUID.randomUUID().toString().split("-")[0]);
    }

    /**
     * Constructor No.2 for the speaker, adding a parameter: ID of this speaker
     *
     * @param userName the userName of this speaker
     * @param email    the email of this speaker
     * @param password the password of this speaker
     * @param ID       the user ID of this speaker
     */
    public Speaker(String userName, String email, String password, String ID,
                   ArrayList<String> friendsID, ArrayList<String> announcements) {
        super(userName, email, password);
        setType("SPEAKER");
        setUserID(ID);
        setFriendList(friendsID);
        setAnnouncements(announcements);
    }

}