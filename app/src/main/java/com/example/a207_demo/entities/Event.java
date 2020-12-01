package com.example.a207_demo.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The entities.Event class, this is the conference scheduled by entities.Organizer for Attendees and Speakers.
 */
public abstract class Event implements Serializable {

    private final String title;
    private final String eventID;
    private final String roomID;
    private ArrayList<String> userIDs;
    private String startTime;
    private String duration;

    /**
     * The constructor No.1 for an event
     *
     * @param title     event title
     * @param roomID    which room the event will be held in
     * @param startTime event starting time
     */
    public Event(String title, String roomID, String startTime, String duration) {
        this.title = title;
        this.eventID = UUID.randomUUID().toString().split("-")[0];
        this.roomID = roomID;
        this.userIDs = new ArrayList<>();
        this.startTime = startTime;
        this.duration = duration;
    }


    /**
     * Get the value of timeSlot, i.e., startTime
     *
     * @return the value of timeSlot
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Try to add an attendee to a list of events
     *
     * @param attendeeID String value of an attendee's userID
     * @param events     a list of events
     * @return boolean true if we add attendee to the list
     */
    public boolean addAttendee(String attendeeID, List<Event> events) {
        if (this.userIDs.contains(attendeeID)) {
            return false;
        }
        for (Event event : events) {
            if (event.getAttendees().contains(attendeeID) &&
                    !((Integer.parseInt(event.getStartTime()) + Integer.parseInt(event.getDuration()) <= Integer.parseInt(this.startTime)) ||
                    Integer.parseInt(this.startTime) + Integer.parseInt(this.duration) <= Integer.parseInt(event.getStartTime()))) {
                return false;
            }
        }
        this.userIDs.add(attendeeID);
        return true;
    }

    /**
     * Try to remove an attendee from the instance variable: userIDs
     *
     * @param attendeeID attendeeID String
     * @return boolean true if person existed in attendee list
     */
    public boolean removeAttendee(String attendeeID) {
        if (userIDs.contains(attendeeID)) {
            return userIDs.remove(attendeeID);
        }
        return false;
    }


    /**
     * Return the ID String of this event
     *
     * @return the ID
     */
    public String getEventID() {
        return eventID;
    }

    /**
     * Return the title String of this event
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return the roomID String of this event
     *
     * @return the roomID
     */
    public String getRoomID() {
        return this.roomID;
    }

    public String getDuration(){
        return this.duration;
    }

    public abstract ArrayList<String> getSpeakers();


    /**
     * Returns all attendees for this event
     *
     * @return all attendees
     */
    public ArrayList<String> getAttendees() {
        return userIDs;
    }

    /**
     * Formats and returns the time slot
     *
     * @return String of the time formatted nicely
     */
    public String getFormattedStartTime() {
        int HourTime = Integer.parseInt(startTime.substring(8, 10));
        String Ending = String.format("%s", (HourTime >= 12) ? "PM" : "AM");
        return String.format("%s/%s/%s/%s%s", startTime.substring(0, 4), startTime.substring(4, 6),
                startTime.substring(6, 8), startTime.substring(8, 10), Ending);
    }

    /**
     * Returns a formatted string with the title and start time of this event
     *
     * @return a formatted string
     */
    @Override
    public String toString() {
        return this.title + " at " + this.getFormattedStartTime();
    }

    /**
     * Returns a formatted string with more data
     *
     * @return a formatted string with more data
     */
    public abstract String toFullString();
}

