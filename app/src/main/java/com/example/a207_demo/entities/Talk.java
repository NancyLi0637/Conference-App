package com.example.a207_demo.entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;

public class Talk extends Event {

    private final ArrayList<String> speaker;
    public Talk (String title, String roomID, ArrayList<String> speakerID, String startTime, String duration,
                 String restriction){
        super(title, roomID, startTime, duration, restriction);
        setType("TALK");
        this.speaker = speakerID;
    }

    @Override
    public ArrayList<String> getSpeakers() {
        return this.speaker;
    }

    @Override
    public String toFullString(){
        return this.toString() + " in room " + this.getRoomID() + " with speaker: " + this.getSpeakers().get(0);}

}

