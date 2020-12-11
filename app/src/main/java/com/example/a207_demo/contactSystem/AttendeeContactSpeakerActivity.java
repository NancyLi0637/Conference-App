package com.example.a207_demo.contactSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

/**
 * AttendeeContactSpeakerActivity
 */
public class AttendeeContactSpeakerActivity extends ContactActivity{

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
        setContentView(R.layout.activity_contact_attendee_speaker);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_attendee, R.id.nav_contacts_speaker_for_attendee);
        createContactMenu();
    }

    /**
     * createContactMenu
     */
    public void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.attendee_contact_speaker_recycler_view);
        ContactMsgAdapter contactMsgAdapter = new ContactMsgAdapter(this, contactList,
                getID(), R.drawable.speaker);
        super.createContactMenu(recyclerView, contactMsgAdapter);
    }

    /**
     * initContacts
     */
    protected void initContacts() {
        super.initContacts();

        //find all events of this attendee
        userIDs = new ArrayList<>();
        ArrayList<String> eventIDs = getEventManager().getEventsFromAttendee(getID());
        for(String eventID : eventIDs){
            ArrayList<String> speakers = getEventManager().getSpeakersFromEvent(eventID);
            userIDs.addAll(speakers);
        }
        contactList = getUserManager().generateIDNameInfo(userIDs);
    }
}
