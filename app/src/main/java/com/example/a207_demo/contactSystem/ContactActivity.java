package com.example.a207_demo.contactSystem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends SetUpActivity {

    //Todo: access Contact Controller
    private List<Contact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        super.createActionBar();
        super.createNavView(this, R.id.nav_contacts);
        createContactMenu();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void createContactMenu(){
        initContacts();

        RecyclerView recyclerView = findViewById(R.id.contact_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        ContactAdapter contactAdapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(contactAdapter);
    }

    public void initContacts(){
        //Todo: access Event Use case to generate event
        for (int i = 0; i < 50; i++){
            Contact contact1 = new Contact("Jenny Su", R.drawable.jenny);
            contactList.add(contact1);
            Contact contact2 = new Contact("Maggie Ma",  R.drawable.maggie);
            contactList.add(contact2);
            Contact contact3 = new Contact("Shawn Kong",  R.drawable.shawn);
            contactList.add(contact3);
            Contact contact4 = new Contact("Tony Huang",  R.drawable.tony);
            contactList.add(contact4);
            Contact contact5 = new Contact("Hardy Gu",  R.drawable.hardy);
            contactList.add(contact5);
            Contact contact6 = new Contact("Bruce Ma",  R.drawable.bruce);
            contactList.add(contact6);
            Contact contact7 = new Contact("Steve Wu",  R.drawable.steve);
            contactList.add(contact7);

        }
    }

}
