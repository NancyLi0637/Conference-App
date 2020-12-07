package com.example.a207_demo.eventSystem;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.roomSystem.Room;
import com.example.a207_demo.utility.ActivityCollector;


import java.util.ArrayList;

/**
 * OrganizerEventContentActivity
 */
public class OrganizerEventContentActivity extends EventContentActivity{

    private String eventToRemove;

    /**
     * onCreate
     * @param savedInstanceState Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * fillContent
     * @param eventTitle String eventTitle
     * @param eventContent String eventContent
     */
    protected void fillContent(String eventTitle, String eventContent){
        super.fillContent(eventTitle, eventContent);
        eventToRemove = eventTitle;
        Button eventCancel = findViewById(R.id.btn_cancel_event);
        eventCancel.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        // Todo: Organizer cancel an event
        cancelEvent(eventToRemove);
    }

    /**
     * cancelEvent
     * @param eventTitle String eventTitle
     */
    public void cancelEvent(String eventTitle){
        Event event = getEventManager().getEventFromTitle(eventTitle);
        ArrayList<String> attendees = event.getAttendees();

        for (String attendee: attendees){
            removeAttendeeFromEvent(attendee, eventTitle);
        }
        // remove the event
        getEventManager().removeEvent(event);
    }

    /**
     * remove Attendee From Event
     *
     * @param userID      userID String object
     * @param eventTitle  eventTitle String object
     */
    public void removeAttendeeFromEvent(String userID, String eventTitle) {
        Event event = getEventManager().getEventFromTitle(eventTitle);
        if (event != null) {
            // decrease number of people in the room
            Room room = getRoomManager().getRoomFromID(event.getRoomID());
            room.decreaseCurrentNum();

            // remove the attendee
            event.removeAttendee(userID);
        }
    }

}