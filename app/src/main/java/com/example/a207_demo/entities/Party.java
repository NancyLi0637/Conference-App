package com.example.a207_demo.entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;

/**
 * Party is a type of event
 */
public class Party extends Event {
    private final ArrayList<String> speaker = new ArrayList<>();

    public Party(String title, String roomID, String startTime, String duration, String restriction) {
        super(title, roomID, startTime, duration, restriction);
        setType("PARTY");
    }

    /**
     * getSpeakers
     *
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getSpeakers() {
        return this.speaker;
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
