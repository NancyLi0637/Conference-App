package com.example.a207_demo.contactSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * OrganizerContactAttendeeActivity
 */
public class OrganizerContactAttendeeActivity extends ContactActivity implements View.OnClickListener{

    private ArrayList<ArrayList<String>> contactList;
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

        Button msgAll = findViewById(R.id.btn_msg_all);
        String msg = "Message All Attendees";
        msgAll.setText(msg);
        msgAll.setOnClickListener(this);

        createContactMenu();
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        Intent intent = new Intent(OrganizerContactAttendeeActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "attendeeContact");
        intent.putExtra("eventTitle", "");
        intent.putExtra("userIDs", getAttendeeManager().getAttendeeIDs());
        startActivity(intent);
    }

    protected void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.organizer_contact_recycler_view);
        organizerContactAdapter = new OrganizerContactAdapter(this, contactList, "attendeeContact");
        super.createContactMenu(recyclerView, organizerContactAdapter);
    }

    protected void initContacts() {
        super.initContacts();
        ArrayList<String> IDs = getAttendeeManager().getAttendeeIDs();
        contactList = getUserManager().generateIDNameInfo(IDs);
    }

}