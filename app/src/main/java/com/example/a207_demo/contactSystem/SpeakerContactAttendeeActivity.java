package com.example.a207_demo.contactSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

public class SpeakerContactAttendeeActivity extends ContactActivity {

    //Todo: access Contact Controller
    private List<Contact> contactList = new ArrayList<>();

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_speaker);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_speaker, R.id.nav_contacts_attendee_for_speaker);
        createContactMenu();
    }

    public void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.speaker_contact_recycler_view);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactList, getID());
        super.createContactMenu(recyclerView, contactAdapter);
    }

    protected void initContacts() {
        //Todo: access Contact Use case to generate contacts
        Contact contact1 = new Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact1);
        Contact contact2 = new Contact("Maggie Ma", R.drawable.maggie);
        contactList.add(contact2);
        Contact contact3 = new Contact("Shawn Kong", R.drawable.shawn);
        contactList.add(contact3);
        Contact contact4 = new Contact("Tony Huang", R.drawable.tony);
        contactList.add(contact4);
        Contact contact5 = new Contact("Hardy Gu", R.drawable.hardy);
        contactList.add(contact5);
        Contact contact6 = new Contact("Bruce Ma", R.drawable.bruce);
        contactList.add(contact6);
        Contact contact7 = new Contact("Steve Wu", R.drawable.steve);
        contactList.add(contact7);
        Contact contact8 = new Contact("Jenny Su", R.drawable.jenny);
        contactList.add(contact8);
        Contact contact9 = new Contact("Maggie Ma", R.drawable.maggie);
        contactList.add(contact9);
        Contact contact10 = new Contact("Shawn Kong", R.drawable.shawn);
        contactList.add(contact10);
        Contact contact11 = new Contact("Tony Huang", R.drawable.tony);
        contactList.add(contact11);
        Contact contact12 = new Contact("Hardy Gu", R.drawable.hardy);
        contactList.add(contact12);
        Contact contact13 = new Contact("Bruce Ma", R.drawable.bruce);
        contactList.add(contact13);
        Contact contact14 = new Contact("Steve Wu", R.drawable.steve);
        contactList.add(contact14);
    }
}