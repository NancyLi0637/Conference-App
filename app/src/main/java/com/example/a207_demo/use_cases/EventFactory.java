package com.example.a207_demo.use_cases;



import com.example.a207_demo.entities.*;

import java.util.ArrayList;

public class EventFactory {
        public Event createEvent(String title, String roomID, ArrayList<String> speakerID, String startTime, String duration, String type){
            if (type.equals("TALK")){
                return new Talk(title, roomID, speakerID, startTime, duration);
            }
            else if (type.equals("DISCUSSION")){
                return new Discussion(title, roomID, startTime, speakerID, duration);
            }
            else{
                return new Party(title, roomID, startTime, duration);
            }
        }
    }
