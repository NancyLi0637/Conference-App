package com.example.a207_demo.contactSystem;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SpeakerContactAttendeeActivity
 */
public class SpeakerContactAttendeeActivity extends ContactActivity implements View.OnClickListener{

    private ArrayList<ArrayList<String>> contactList;
    private ArrayList<String> userIDs;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_speaker);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_speaker, R.id.nav_contacts_attendee_for_speaker);
        createContactMenu();

        Button msgAll = findViewById(R.id.btn_speaker_msg_all);
        msgAll.setOnClickListener(this);
    }

    public void onClick(View view){
        Intent intent = new Intent(SpeakerContactAttendeeActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "speakerContactAttendee");
        intent.putExtra("eventTitle", "");
        intent.putExtra("userIDs", userIDs);
        startActivity(intent);
    }

    /**
     * createContactMenu
     */
    public void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.speaker_contact_recycler_view);
        ContactMsgAdapter contactMsgAdapter = new ContactMsgAdapter(this, contactList,
                getID(), R.drawable.icon_contact_gray);
        super.createContactMenu(recyclerView, contactMsgAdapter);
    }

    protected void initContacts() {
        super.initContacts();

        //find all events of this speaker
        userIDs = new ArrayList<>();
        ArrayList<String> eventIDs = getEventManager().getEventsFromSpeaker(getID());
        for(String eventID : eventIDs){
            //find all users of the events
            ArrayList<String> attendees = getEventManager().getAttendeesFromEvent(eventID);
            userIDs.addAll(attendees);
        }

        contactList = getUserManager().generateIDNameInfo(userIDs);
    }
}