package com.example.a207_demo.eventSystem;

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
    private String restriction;
    private String type;

    private int imageId;

    /**
     * The constructor No.1 for an event
     *
     * @param title       event title
     * @param roomID      which room the event will be held in
     * @param startTime   event starting time
     * @param duration    duration of the event
     * @param restriction event restriction
     */
    public Event(String title, String roomID, String startTime, String duration, String restriction) {
        this.title = title;
        this.eventID = UUID.randomUUID().toString().split("-")[0];
        this.roomID = roomID;
        this.userIDs = new ArrayList<>();
        this.startTime = startTime;
        this.duration = duration;
        this.restriction = restriction;
        System.out.println(eventID);
    }

    /**
     * Set the type of this event
     *
     * @param type String, the type of this event
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * setImageId
     * @param imageId imageId
     */
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Return the type of this event
     *
     * @return String, the type of this event
     */
    public String getType() {
        return this.type;
    }

    /**
     * getImageId
     * @return imageId
     */
    public int getImageId() {
        return this.imageId;
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

    /**
     * get Duration for the event
     * @return String: duration
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * Abstract method: get speakers for the event
     * @return list of String
     */
    public abstract ArrayList<String> getSpeakers();

    /**
     * Returns all attendees for this event
     *
     * @return all attendees
     */
    public ArrayList<String> getAttendees() {
        return userIDs;
    }

    public String getRestriction() {
        return restriction;
    }


    /**
     * Formats and returns the time slot
     *
     * @return String of the time formatted nicely
     */
    public String getFormattedStartTime() {
        int HourTime = Integer.parseInt(startTime.substring(11, 13));
        String Ending = String.format("%s", (HourTime >= 12) ? "PM" : "AM");
        return String.format("%s/%s/%s/%s%s", startTime.substring(0, 4), startTime.substring(5, 7),
                startTime.substring(8, 10), startTime.substring(11, 13), Ending);
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
                    timeConflict(event.getStartTime(), event.getDuration())){
                return false;
            }
        }
        this.userIDs.add(attendeeID);
        return true;
    }

    public boolean timeConflict(String startTime, String duration){
        int thisTime = convertStartTimeToNum(this.startTime);
        int checkTime = convertStartTimeToNum(startTime);
        int thisDuration = Integer.parseInt(this.duration);
        int checkDuration = Integer.parseInt(duration);

        boolean conflict1 = thisTime <= checkTime && checkTime <= thisTime + thisDuration;
        boolean conflict2 = checkTime <= thisTime && thisTime <= checkTime + checkDuration;
        return conflict1 || conflict2;
    }

    private int convertStartTimeToNum(String startTime){
        String newTime = startTime.substring(0, 4) + startTime.substring(5, 7) +
                startTime.substring(8, 10) + startTime.substring(11, 13);
        return Integer.parseInt(newTime);
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

