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
    private String announcement;


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
        ArrayList<String> event = getIntent().getStringArrayListExtra("event");
        eventID = event.get(0);
        eventTitle = event.get(1);

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
        switch (view.getId()){
            case R.id.btn_event_announce_speaker:
                    intent = new Intent(OrganizerEventContentActivity.this, SendAnnouncementActivity.class);
                    startActivityForResult(intent, 1);
                break;
            case R.id.btn_event_announce_attendee:
                intent = new Intent(OrganizerEventContentActivity.this, SendAnnouncementActivity.class);
                startActivityForResult(intent, 2);
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

    /**
     * onActivityResult
     * @param requestCode requestCode
     * @param resultCode resultCode
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            announcement = data.getStringExtra("announcement");
            ArrayList<String> userIDs = new ArrayList<>();
            switch(requestCode) {
                case 1:
                    userIDs = getEventManager().getSpeakersFromEvent(eventID);
                    break;
                case 2:
                    userIDs = getEventManager().getAttendeesFromEvent(eventID);
                    break;
            }
            boolean sent = getOrganizerManager().sendAnnouncement(userIDs, eventTitle, announcement);
            if(sent){
                super.writeUser();
                Toast.makeText(this, "Announcement SENT!", Toast.LENGTH_LONG).show();
            }
        }

    }


}