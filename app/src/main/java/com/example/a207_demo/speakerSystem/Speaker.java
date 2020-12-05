package com.example.a207_demo.speakerSystem;

import com.example.a207_demo.entities.User;

import java.io.Serializable;
import java.util.UUID;

/**
 * The entities.Speaker class, this creates instances of an entities.Speaker for a conference.
 */
public class Speaker extends User implements Serializable {
    private final String userID;

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
        this.userID = UUID.randomUUID().toString().split("-")[0];
    }

    /**
     * Constructor No.2 for the speaker, adding a parameter: ID of this speaker
     *
     * @param userName the userName of this speaker
     * @param email    the email of this speaker
     * @param password the password of this speaker
     * @param ID       the user ID of this speaker
     */
    public Speaker(String userName, String email, String password, String ID) {
        super(userName, email, password);
        setType("SPEAKER");
        this.userID = ID;
    }

    /**
     * Getter method to access this speaker's userID
     *
     * @return userID of this speaker
     */
    public String getUserID() {
        return this.userID;
    }
}