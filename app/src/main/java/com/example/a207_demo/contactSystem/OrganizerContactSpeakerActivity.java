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

public class OrganizerContactSpeakerActivity extends ContactActivity implements View.OnClickListener {

    private ArrayList<ArrayList<String>> contactList = new ArrayList<>();
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
        super.init(this, R.id.nav_view_organizer, R.id.nav_contacts_speaker_for_organizer);

        Button msgAll = findViewById(R.id.btn_organizer_msg_all);
        msgAll.setText(R.string.msgAllSpeakers);
        msgAll.setOnClickListener(this);

        createContactMenu();
    }

    public void onClick(View view) {
        Intent intent = new Intent(OrganizerContactSpeakerActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "organizerContactSpeaker");
        intent.putExtra("eventTitle", "");
        intent.putExtra("userIDs", getSpeakerManager().getSpeakerIDs());
        startActivity(intent);
    }

    protected void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.organizer_contact_recycler_view);
        organizerContactAdapter = new OrganizerContactAdapter(this, contactList, getID(),
                "organizerContactSpeaker", R.drawable.speaker);
        super.createContactMenu(recyclerView, organizerContactAdapter);
    }

    protected void initContacts() {
        super.initContacts();
        ArrayList<String> IDs = getSpeakerManager().getSpeakerIDs();
        contactList = getUserManager().generateIDNameInfo(IDs);
    }
}