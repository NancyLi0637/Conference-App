package com.example.a207_demo.entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;

/**
 * Talk is a type of event
 */
public class Talk extends Event {

    private final ArrayList<String> speaker;

    /**
     * Talk
     * @param title title
     * @param roomID roomID
     * @param speakerID speakerID
     * @param startTime startTime
     * @param duration duration
     * @param restriction restriction
     */
    public Talk (String title, String roomID, ArrayList<String> speakerID, String startTime, String duration,
                 String restriction){
        super(title, roomID, startTime, duration, restriction);
        setType("TALK");
        this.speaker = speakerID;
    }

    /**
     * getSpeakers
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getSpeakers() {
        return this.speaker;
    }

    /**
     * toFullString

     * @return String
     */
    @Override
    public String toFullString(){
        return this.toString() + " in room " + this.getRoomID() + " with speaker: " + this.getSpeakers().get(0);}

}

