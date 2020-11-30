package com.example.a207_demo.eventSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.a207_demo.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.Settings;
import com.example.a207_demo.contactSystem.ContactActivity;
import com.example.a207_demo.messageSystem.AnnouncementActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    //Todo: access EventController
    private List<Event> eventList = new ArrayList<>();
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        createActionBar();
        createNavView();
        createEventMenu();

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
                Intent intent = new Intent(EventActivity.this, Settings.class);
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
        navigationView.setCheckedItem(R.id.nav_allevents);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_allevents:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_myEvents:
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(EventActivity.this, MyEventActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_contacts:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(EventActivity.this, ContactActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_announcements:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(EventActivity.this, AnnouncementActivity.class);
                        startActivity(intent);
                }

                return true;
            }
        });
    }

    public void createEventMenu(){
        initEvents();

        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        EventAdapter eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
    }

    public void initEvents(){
        //Todo: access Event Use case to generate event
        for (int i = 0; i < 50; i++){
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
