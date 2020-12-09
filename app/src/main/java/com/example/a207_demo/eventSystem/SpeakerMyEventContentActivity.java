package com.example.a207_demo.eventSystem;

import android.content.Intent;
import android.graphics.fonts.SystemFonts;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * SpeakerEventContentActivity
 */
public class SpeakerMyEventContentActivity extends EventContentActivity{

    private String eventTitle;
    private String eventID;
    private Intent intent;

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_content_speaker);
        ActivityCollector.addActivity(this);

        init();
    }

    protected void init(){
        super.init();
        eventTitle = getEventTitle();
        eventID = getEventID();

        Button announceAttendee = findViewById(R.id.btn_speaker_announce_attendee);
        announceAttendee.setOnClickListener(this);
    }

    public void onClick(View view){
        intent = new Intent(SpeakerMyEventContentActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "speakerEventContent");
        intent.putExtra("eventTitle", eventTitle);
        intent.putExtra("userIDs", getEventManager().getAttendeesFromEvent(eventID));
        startActivity(intent);
    }

}