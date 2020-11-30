package com.example.a207_demo.contactSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.ActivityCollector;
import com.example.a207_demo.Settings;
import com.example.a207_demo.eventSystem.EventActivity;
import com.example.a207_demo.R;
import com.example.a207_demo.eventSystem.MyEventActivity;
import com.example.a207_demo.messageSystem.AnnouncementActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    //Todo: access Contact Controller
    private List<Contact> contactList = new ArrayList<>();
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        createActionBar();
        createNavView();
        createContactMenu();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                Intent intent = new Intent(ContactActivity.this, Settings.class);
                startActivity(intent);
                break;
            case R.id.signOut:
                ActivityCollector.finishAll();
        }
        return true;
    }

    public void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0,0);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void createNavView(){
        //Todo: image sources for nav items
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_contacts);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_allevents:
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(ContactActivity.this, EventActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_myEvents:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(ContactActivity.this, MyEventActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_contacts:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_announcements:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(ContactActivity.this, AnnouncementActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });
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
