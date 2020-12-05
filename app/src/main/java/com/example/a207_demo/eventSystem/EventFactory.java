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
     *
     * @param title String title
     * @param roomID String roomID
     * @param speakerID ArrayList<String> speakerID
     * @param startTime String startTime
     * @param duration String duration
     * @param restriction String restriction
     * @param type String type
     * @return Event that is just created
     */
    public Event createEvent(String title, String roomID, ArrayList<String> speakerID, String startTime, String duration,
                             String restriction, String type) {
        switch (type) {
            case "TALK":
                return new Talk(title, roomID, speakerID, startTime, duration, restriction);
            case "DISCUSSION":
                return new Discussion(title, roomID, startTime, speakerID, duration, restriction);
            case "PARTY":
                return new Party(title, roomID, startTime, duration, restriction);
        }
        return null;
    }
}
