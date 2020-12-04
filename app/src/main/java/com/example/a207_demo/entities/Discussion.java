package com.example.a207_demo.entities;
import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;
import java.util.List;

public class Discussion extends Event {
    private ArrayList<String> speakers;

    public Discussion(String title, String roomID, String startTime, ArrayList<String> speakers, String duration,
                      String restriction){
        super(title, roomID, startTime, duration, restriction);
        setType("DISCUSSION");
        this.speakers = speakers;
    }

    @Override
    public ArrayList<String> getSpeakers(){
        return this.speakers;
    }

    public StringBuilder speakersToString(){
        StringBuilder totalString = new StringBuilder();
        for (String s : speakers){
            totalString.append(s);
        }
        return totalString;
    }

    @Override
    public String toFullString(){
        return this.toString() + " in room " + this.getRoomID() + " with speaker: " + this.speakersToString();}
}
