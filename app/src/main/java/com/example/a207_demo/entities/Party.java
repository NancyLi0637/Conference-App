package com.example.a207_demo.entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;

/**
 * Party is a type of event
 */
public class Party extends Event {

    /**
     * Party
     * @param title title
     * @param roomID roomID
     * @param startTime startTime
     * @param duration duration
     * @param restriction restriction
     */
    public Party(String title, String roomID, String startTime, String duration, String restriction, int capacity) {
        super(title, roomID, startTime, duration, restriction, capacity);
        setType("PARTY");
        setSpeakerUserIDs(new ArrayList<String>());
    }

    /**
     * PARTY
     * @param title title
     * @paramt eventID eventID
     * @param roomID roomID
     * @param startTime startTime
     * @param duration duration
     * @param restriction restriction
     */
    public Party (String title, String eventID, String roomID, String startTime, String duration,
                  String restriction, int capacity, ArrayList<String> attendeeID){
        super(title, eventID, roomID, startTime, duration, restriction, capacity);
        setType("PARTY");
        setSpeakerUserIDs(new ArrayList<String>());
        setAttendeeUserIDs(attendeeID);
    }

    /**
     * toFullString
     *
     * @return String
     */
    @Override
    public String toFullString() {
        return this.toString() + " in room " + this.getRoomID();
    }
}
