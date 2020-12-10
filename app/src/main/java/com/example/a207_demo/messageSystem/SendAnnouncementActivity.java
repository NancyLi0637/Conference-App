package com.example.a207_demo.messageSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.contactSystem.OrganizerContactAttendeeActivity;
import com.example.a207_demo.contactSystem.OrganizerContactSpeakerActivity;
import com.example.a207_demo.contactSystem.SpeakerContactAttendeeActivity;
import com.example.a207_demo.entities.Organizer;
import com.example.a207_demo.eventSystem.OrganizerEventContentActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventContentActivity;
import com.example.a207_demo.utility.CleanArchActivity;

import java.util.ArrayList;

public class SendAnnouncementActivity extends CleanArchActivity implements View.OnClickListener{
    private Intent intent;

    private String announcement;
    private ArrayList<String> userIDs;
    private String eventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_send);

        init();
    }

    private void init(){
        super.reset();
        super.readUser();
        setUpInfo();

        Button back = findViewById(R.id.announcement_back_event);
        Button send = findViewById(R.id.send_announcement);

        back.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.send_announcement:
                loadData();
                if(announcement.equals("")){
                    Toast.makeText(this, "Announcement can't be empty!", Toast.LENGTH_LONG).show();
                }else if(sendAnnouncement()){
                    super.writeUser();
                    Toast.makeText(this, "Announcement SENT!", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Some errors have occurred!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.announcement_back_event:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    private void loadData(){
        EditText announce = findViewById(R.id.announcement_to_send);
        announcement = announce.getText().toString();
    }

    private void setUpInfo(){
        String from = getIntent().getStringExtra("class");
        eventTitle = getIntent().getStringExtra("eventTitle");
        userIDs = getIntent().getStringArrayListExtra("userIDs");

        if(from.equals("eventContent")){
            intent = new Intent(SendAnnouncementActivity.this, OrganizerEventContentActivity.class);
        }else if(from.equals("organizerContactAttendee")){
            intent = new Intent(SendAnnouncementActivity.this, OrganizerContactAttendeeActivity.class);
        }else if(from.equals("organizerContactSpeaker")){
            intent = new Intent(SendAnnouncementActivity.this, OrganizerContactSpeakerActivity.class);
        }else if(from.equals("speakerEventContent")){
            intent = new Intent(SendAnnouncementActivity.this, SpeakerMyEventContentActivity.class);
        }else if(from.equals("speakerContactAttendee")){
            intent = new Intent(SendAnnouncementActivity.this, SpeakerContactAttendeeActivity.class);
        }
    }

    private boolean sendAnnouncement(){
        return getUserManager().sendAnnouncement(userIDs, eventTitle, announcement);
    }
}