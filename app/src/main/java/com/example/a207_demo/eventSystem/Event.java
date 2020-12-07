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
    private String startTime;
    private String duration;
    private String restriction;
    private String type;
    private int capacity;
    private int imageId;

    private ArrayList<String> attendeeUserIDs;
    private ArrayList<String> speakerUserIDs;

    /**
     * The constructor No.1 for an event
     *
     * @param title       event title
     * @param roomID      which room the event will be held in
     * @param startTime   event starting time
     * @param duration    duration of the event
     * @param restriction event restriction
     */
    public Event(String title, String roomID, String startTime, String duration, String restriction, int capacity) {
        this.title = title;
        this.eventID = UUID.randomUUID().toString().split("-")[0];
        this.roomID = roomID;
        this.startTime = startTime;
        this.duration = duration;
        this.restriction = restriction;
        this.capacity = capacity;
        this.attendeeUserIDs = new ArrayList<>();
    }

    /**
     * The constructor No.2 for an event
     *
     * @param title       event title
     * @param roomID      which room the event will be held in
     * @param startTime   event starting time
     * @param duration    duration of the event
     * @param restriction event restriction
     */
    public Event(String title, String eventID, String roomID, String startTime, String duration, String restriction, int capacity) {
        this.title = title;
        this.eventID = eventID;
        this.roomID = roomID;
        this.startTime = startTime;
        this.duration = duration;
        this.restriction = restriction;
        this.capacity = capacity;
        this.attendeeUserIDs = new ArrayList<>();
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
     * set ids of attendees attending this event
     * @param attendeeUserIDs userIDs of attendees
     */
    public void setAttendeeUserIDs(ArrayList<String> attendeeUserIDs) {
        this.attendeeUserIDs = attendeeUserIDs;
    }

    /**
     * set ids of speakers attending this event
     * @param speakerUserIDs userIDs of attendees
     */
    public void setSpeakerUserIDs(ArrayList<String> speakerUserIDs){
        this.speakerUserIDs = speakerUserIDs;
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
     * Return the ID String of this event
     *
     * @return the ID
     */
    public String getEventID() {
        return eventID;
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
     * Get the value of timeSlot, i.e., startTime
     *
     * @return the value of timeSlot
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * get Duration for the event
     * @return String: duration
     */
    public String getDuration() {
        return this.duration;
    }

    /**
     * Get the restriction of this event.
     * @return Stirng, the restriction of this event
     */
    public String getRestriction() {
        return restriction;
    }

    /**
     * Get the type of this event
     *
     * @return String, the type of this event
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the capacity of this event
     * @return int, the capacity of this event
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * Get the current number of attendees of this event
     * @return
     */
    public int getCurrentNum(){
        return this.attendeeUserIDs.size();
    }


    /**
     * getImageId
     * @return imageId
     */
    public int getImageId() {
        return this.imageId;
    }

    /**
     * Returns all attendees for this event
     *
     * @return all attendees
     */
    public ArrayList<String> getAttendees() {
        return attendeeUserIDs;
    }


    /**
     * Get speakers for the event
     * @return list of String
     */
    public ArrayList<String> getSpeakers(){
        return this.speakerUserIDs;
    };


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
    public void addAttendee(String attendeeID, List<Event> events) {
        this.attendeeUserIDs.add(attendeeID);
    }

    /**
     * Try to add an attendee to a list of events
     *
     * @param speakerID String value of an attendee's userID
     * @param events     a list of events
     * @return boolean true if we add attendee to the list
     */
    public boolean addSpeaker(String speakerID, List<Event> events) {
        if (this.speakerUserIDs.contains(speakerID)) {
            return false;
        }
        for (Event event : events) {
            if (event.getAttendees().contains(speakerID) &&
                    (timeConflict(event.getStartTime(), event.getDuration()))){
                return false;
            }
        }
        this.attendeeUserIDs.add(speakerID);
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
    public void removeAttendee(String attendeeID) {
        attendeeUserIDs.remove(attendeeID);
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

