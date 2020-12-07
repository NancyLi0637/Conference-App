package com.example.a207_demo.entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;

/**
 * Talk is a type of event
 */
public class Talk extends Event {

    /**
     * Talk
     * @param title title
     * @param roomID roomID
     * @param speakerID speakerID
     * @param startTime startTime
     * @param duration duration
     * @param restriction restriction
     */
    public Talk (String title, String roomID, String startTime, String duration, String restriction,
                 int capacity, ArrayList<String> speakerID){
        super(title, roomID, startTime, duration, restriction, capacity);
        setType("TALK");
        setSpeakerUserIDs(speakerID);
    }

    /**
     * Talk
     * @param title title
     * @paramt eventID eventID
     * @param roomID roomID
     * @param speakerID speakerID
     * @param startTime startTime
     * @param duration duration
     * @param restriction restriction
     */
    public Talk (String title, String eventID, String roomID, String startTime, String duration,
                 String restriction, int capacity, ArrayList<String> speakerID){
        super(title, eventID, roomID, startTime, duration, restriction, capacity);
        setType("TALK");
        setSpeakerUserIDs(speakerID);
    }

    /**
     * toFullString

     * @return String
     */
    @Override
    public String toFullString(){
        return this.toString() + " in room " + this.getRoomID() + " with speaker: " + this.getSpeakers().get(0);}

}

