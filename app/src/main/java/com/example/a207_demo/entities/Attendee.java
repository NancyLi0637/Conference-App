package com.example.a207_demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * The entities.Attendee class, this creates instances of entities.Attendee to attend a conference.
 */
public class Attendee extends User implements Serializable {

    private ArrayList<String> announcements;

    /**
     * Constructor No.1 for the Attendee
     *
     * @param userName the userName of this Attendee
     * @param email    the email of this Attendee
     * @param password the password of this Attendee
     */
    public Attendee(String userName, String email, String password) {
        super(userName, email, password);
        setType("ATTENDEE");
        setUserID(UUID.randomUUID().toString().split("-")[0]);
    }

    /**
     * Constructor No.2 for the Attendee, adding a parameter: ID of this Attendee
     *
     * @param userName the userName of this Attendee
     * @param email    the email of this Attendee
     * @param password the password of this Attendee
     * @param ID       the user ID of this Attendee
     * @param announcements inbox of annoucements of this speaker
     */
    public Attendee(String userName, String email, String password, String ID,
                    ArrayList<String> announcements) {
        super(userName, email, password);
        setType("ATTENDEE");
        setUserID(ID);
        this.announcements = announcements;
    }

    public void setAnnouncements(ArrayList<String> announcements){
        this.announcements = announcements;
    }

    public void addAnnouncement(String announcement){
        this.announcements.add(announcement);
    }

    public ArrayList<String> getAnnouncements(){return this.announcements;}

}
