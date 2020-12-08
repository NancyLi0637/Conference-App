package com.example.a207_demo.eventSystem;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;
import com.example.a207_demo.utility.ActivityCollector;


import java.util.ArrayList;

/**
 * OrganizerEventContentActivity
 */
public class OrganizerEventContentActivity extends EventContentActivity{
    private Intent intent;

    private String eventID;
    private String eventTitle;


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
     */
    protected void init(){
        super.init();
        eventID = getEventID();
        eventTitle = getEventTitle();

        Button eventCancel = findViewById(R.id.btn_cancel_event);
        Button announceSpeaker = findViewById(R.id.btn_event_announce_speaker);
        Button announceAttendee = findViewById(R.id.btn_event_announce_attendee);
        eventCancel.setOnClickListener(this);
        announceSpeaker.setOnClickListener(this);
        announceAttendee.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        intent = new Intent(OrganizerEventContentActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "eventContent");
        intent.putExtra("eventTitle", eventTitle);
        switch (view.getId()){
            case R.id.btn_event_announce_speaker:
                intent.putExtra("userIDs", getEventManager().getSpeakersFromEvent(eventID));
                startActivity(intent);
                break;
            case R.id.btn_event_announce_attendee:
                intent.putExtra("userIDs", getEventManager().getAttendeesFromEvent(eventID));
                startActivity(intent);
                break;
            case R.id.btn_cancel_event:
                if(cancelEvent()){
                    Toast.makeText(this, "Event is cancelled!", Toast.LENGTH_LONG).show();
                    super.writeEvent();
                    startActivity(new Intent(OrganizerEventContentActivity.this, OrganizerEventActivity.class));
                }else{
                    Toast.makeText(this, "Some error occurred!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    /**
     * cancelEvent
     */
    private boolean cancelEvent(){
        return getEventManager().cancelEvent(eventID);
    }

}