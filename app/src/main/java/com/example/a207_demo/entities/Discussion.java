package com.example.a207_demo.entities;

import com.example.a207_demo.eventSystem.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Discussion is a type of event
 */
public class Discussion extends Event {
    private ArrayList<String> speakers;

    /**
     * Discussion
     *
     * @param title       title
     * @param roomName      roomID
     * @param startTime   startTime
     * @param speakers    speakers
     * @param duration    duration
     * @param restriction restriction
     */
    public Discussion(String title, String roomName, String startTime, ArrayList<String> speakers, String duration,
                      String restriction) {
        super(title, roomName, startTime, duration, restriction);
        setType("DISCUSSION");
        this.speakers = speakers;
    }

    /**
     * getSpeakers
     *
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getSpeakers() {
        return this.speakers;
    }

    /**
     * speakersToString
     *
     * @return StringBuilder
     */
    public StringBuilder speakersToString() {
        StringBuilder totalString = new StringBuilder();
        for (String s : speakers) {
            totalString.append(s);
        }
        return totalString;
    }

    /**
     * toFullString
     *
     * @return String
     */
    @Override
    public String toFullString() {
        return this.toString() + " in room " + this.getRoomName() + " with speaker: " + this.speakersToString();
    }
}
