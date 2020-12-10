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
        ContactMsgAdapter contactMsgAdapter = new ContactMsgAdapter(this, contactList, getID());
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
        //Todo: access Contact Use case to generate contacts
//        Contact contact1 = new Contact("Jenny Su", R.drawable.jenny);
//        contactList.add(contact1);
//        Contact contact2 = new Contact("Maggie Ma", R.drawable.maggie);
//        contactList.add(contact2);
//        Contact contact3 = new Contact("Shawn Kong", R.drawable.shawn);
//        contactList.add(contact3);
//        Contact contact4 = new Contact("Tony Huang", R.drawable.tony);
//        contactList.add(contact4);
//        Contact contact5 = new Contact("Hardy Gu", R.drawable.hardy);
//        contactList.add(contact5);
//        Contact contact6 = new Contact("Bruce Ma", R.drawable.bruce);
//        contactList.add(contact6);
//        Contact contact7 = new Contact("Steve Wu", R.drawable.steve);
//        contactList.add(contact7);
//        Contact contact8 = new Contact("Jenny Su", R.drawable.jenny);
//        contactList.add(contact8);
//        Contact contact9 = new Contact("Maggie Ma", R.drawable.maggie);
//        contactList.add(contact9);
//        Contact contact10 = new Contact("Shawn Kong", R.drawable.shawn);
//        contactList.add(contact10);
//        Contact contact11 = new Contact("Tony Huang", R.drawable.tony);
//        contactList.add(contact11);
//        Contact contact12 = new Contact("Hardy Gu", R.drawable.hardy);
//        contactList.add(contact12);
//        Contact contact13 = new Contact("Bruce Ma", R.drawable.bruce);
//        contactList.add(contact13);
//        Contact contact14 = new Contact("Steve Wu", R.drawable.steve);
//        contactList.add(contact14);
    }
}
