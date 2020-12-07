package com.example.a207_demo.eventSystem;


import com.example.a207_demo.entities.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contains the createEvent method to create an event according to the type
 */
public class EventFactory implements Serializable {
    /**
     * create an event according to the type
     * @param type String type
     * @param title String title
     * @param roomID String roomID
     * @param startTime String startTime
     * @param duration String duration
     * @param restriction String restriction
     * @param capacity int capacity
     * @param speakerID ArrayList<String> speakerID
     * @return Event that is just created
     */
    public Event createEvent(String type, String title, String roomID, String startTime,
                             String duration, String restriction, int capacity, ArrayList<String> speakerID) {
        switch (type) {
            case "TALK":
                return new Talk(title, roomID, startTime, duration, restriction, capacity, speakerID);
            case "DISCUSSION":
                return new Discussion(title, roomID, startTime, duration, restriction, capacity, speakerID);
            case "PARTY":
                return new Party(title, roomID, startTime, duration, restriction, capacity);
        }
        return null;
    }

    /**
     * create an event according to the type
     *@param type String type
     * @param title String title
     * @param eventID String eventID
     * @param roomID String roomID
     * @param startTime String startTime
     * @param duration String duration
     * @param restriction String restriction
     * @param capacity int capacity
     * @param speakerID ArrayList<String> speakerID
     * @return Event that is just created
     */
    public Event createEvent(String type, String title, String eventID, String roomID,
                             String startTime, String duration, String restriction, int capacity,
                             ArrayList<String> speakerID) {
        switch (type) {
            case "TALK":
                return new Talk(title, eventID, roomID, startTime, duration, restriction, capacity, speakerID);
            case "DISCUSSION":
                return new Discussion(title, eventID, roomID, startTime, duration, restriction, capacity, speakerID);
            case "PARTY":
                return new Party(title, eventID, roomID, startTime, duration, restriction, capacity);
        }
        return null;
    }
}
