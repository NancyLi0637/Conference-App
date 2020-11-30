package com.example.a207_demo.eventSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a207_demo.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.Settings;
import com.example.a207_demo.contactSystem.ContactActivity;
import com.example.a207_demo.messageSystem.AnnouncementActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MyEventActivity extends AppCompatActivity {

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
                Intent intent = new Intent(MyEventActivity.this, Settings.class);
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
        navigationView.setCheckedItem(R.id.nav_myEvents);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_allevents:
                        mDrawerLayout.closeDrawers();
                        Intent intent = new Intent(MyEventActivity.this, EventActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_myEvents:
                        mDrawerLayout.closeDrawers();
                        break;
                    case R.id.nav_contacts:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(MyEventActivity.this, ContactActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_announcements:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(MyEventActivity.this, AnnouncementActivity.class);
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });
    }

}