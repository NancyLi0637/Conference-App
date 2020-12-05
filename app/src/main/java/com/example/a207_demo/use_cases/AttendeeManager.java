package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.*;
import com.example.a207_demo.eventSystem.EventManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The use_cases.AttendeeManager class, this is the use case class to manage the Attendees for this conference.
 */
public class AttendeeManager extends UserManager implements Serializable {

    private List<Attendee> attendees;

    /**
     * Creates an use_cases.AttendeeManager with lists of Events for an attendee that is empty
     */
    public AttendeeManager() {
        attendees = new ArrayList<>();
    }

    /**
     * Creates An attendee and adds it to the map and lists
     */
    public void createAttendee(String userName, String email, String password) {
        Attendee attendee = new Attendee(userName, email, password);
        this.attendees.add(attendee);
        UserManager.users.add(attendee);
    }

    /**
     * Creates an attendee and adds it to the map and list. But this is for loading the file.
     *
     * @param userName The user name of the attendee
     * @param email    The email of the attendee
     * @param password The password of the attendee
     * @param ID       The unique id of the attendee
     */
    public void loadAttendee(String userName, String email, String password, String ID) {
        Attendee attendee = new Attendee(userName, email, password, ID);
        this.attendees.add(attendee);
        UserManager.users.add(attendee);
    }


    /**
     * Getter method for all attendees
     *
     * @return list of attendees
     */
    public List<Attendee> getAttendees() {
        return attendees;
    }

    /**
     * Try to sign an entities.Attendee up for an event.
     * @param eventManager an eventManager
     * @param userID String userID
     * @param eventID String eventID
     * @param roomManager a roomManager
     * @return boolean value, return true iff signed up successfully
     */
//    public boolean signUp(EventManager eventManager, String userID, String eventID, RoomManager roomManager) {
//        return eventManager.addAttendeeToEvent(userID, eventID, roomManager);
//    }

    /**
     * Try to cancel an entities.Attendee from an event.
     *
     * @param eventManager an eventManager
     * @param userID       String userIDv
     * @param eventID      String eventID
     * @param roomManager  a roomManager
     * @return boolean value, return true iff canceled successfully
     */
    public boolean cancel(EventManager eventManager, String userID, String eventID, RoomManager roomManager) {
        return eventManager.removeAttendeeFromEvent(userID, eventID, roomManager);
    }

    /**
     * Reset the attendees: no user exists
     */
    public void reset() {
        attendees = new ArrayList<>();
    }

}