package com.example.a207_demo.entities;

import java.util.ArrayList;

public class Party extends Event{
    private final ArrayList<String> speaker = new ArrayList<>();

    public Party(String title, String roomID, String startTime, String duration){
        super(title, roomID, startTime, duration);
    }

    @Override
    public ArrayList<String> getSpeakers() {
        return this.speaker;
    }

    @Override
    public String toFullString(){
        return this.toString() + " in room " + this.getRoomID();}
}
