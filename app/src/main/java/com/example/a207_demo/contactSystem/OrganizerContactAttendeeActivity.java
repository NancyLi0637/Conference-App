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

/**
 * OrganizerContactAttendeeActivity
 */
public class OrganizerContactAttendeeActivity extends ContactActivity implements View.OnClickListener{

    private ArrayList<ArrayList<String>> contactList;
    private ArrayList<String> IDs;
    private OrganizerContactAdapter organizerContactAdapter;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_contacts_attendee_for_organizer);
        createContactMenu();

        Button msgAll = findViewById(R.id.btn_organizer_msg_all);
        msgAll.setText(R.string.msgAllAttendees);
        msgAll.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        Intent intent = new Intent(OrganizerContactAttendeeActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "organizerContactAttendee");
        intent.putExtra("eventTitle", "");
        intent.putExtra("userIDs", IDs);
        startActivity(intent);
    }

    protected void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.organizer_contact_recycler_view);
        organizerContactAdapter = new OrganizerContactAdapter(this, contactList, "organizerContactAttendee");
        super.createContactMenu(recyclerView, organizerContactAdapter);
    }

    protected void initContacts() {
        super.initContacts();
        IDs = getAttendeeManager().getAttendeeIDs();
        contactList = getUserManager().generateIDNameInfo(IDs);
    }

}