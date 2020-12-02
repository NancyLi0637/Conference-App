package com.example.a207_demo.eventSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a207_demo.R;
import com.example.a207_demo.contactSystem.ContactActivity;
import com.example.a207_demo.messageSystem.AnnouncementActivity;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.utility.Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SpeakerMyEventActivity extends AppCompatActivity {

    //Todo: generate event list by use case
    private List<Event> eventList = new ArrayList<>();
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_speaker);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        createActionBar();
        createNavView();
        createEventMenu();
    }

    private void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0,0);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void createNavView(){
        NavigationView navigationView = findViewById(R.id.nav_view_speaker);
        navigationView.setCheckedItem(R.id.nav_myEvents);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_myEvents_speaker:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(SpeakerMyEventActivity.this, SpeakerMyEventActivity.class));
                        break;
                    case R.id.nav_contacts_speaker:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(SpeakerMyEventActivity.this, ContactActivity.class));
                        break;
                    case R.id.nav_announcements_speaker:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(SpeakerMyEventActivity.this, AnnouncementActivity.class));
                        break;
                }

                return true;
            }
        });
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
                startActivity(new Intent(SpeakerMyEventActivity.this, Settings.class));
                break;
            case R.id.signOut:
                ActivityCollector.finishAll();
        }
        return true;
    }

    private void createEventMenu(){
        //Todo: generate Event list of this speaker

        for(int i = 0; i < 2; i++) {
            Event event1 = new Event("Event1", "BF101", "", "13:00", R.drawable.default_image);
            eventList.add(event1);
            Event event2 = new Event("Event2", "TH305", "", "13:00", R.drawable.default_image);
            eventList.add(event2);
            Event event3 = new Event("Event3", "RC104", "", "13:00", R.drawable.default_image);
            eventList.add(event3);
            Event event4 = new Event("Event4", "RC507", "", "13:00", R.drawable.default_image);
            eventList.add(event4);
            Event event5 = new Event("Event5", "SU302", "", "13:00", R.drawable.default_image);
            eventList.add(event5);
        }
    }


}