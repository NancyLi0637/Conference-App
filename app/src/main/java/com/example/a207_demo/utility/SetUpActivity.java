package com.example.a207_demo.utility;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a207_demo.R;
import com.example.a207_demo.contactSystem.AttendeeContactAttendeeActivity;
import com.example.a207_demo.contactSystem.AttendeeContactSpeakerActivity;
import com.example.a207_demo.contactSystem.ContactActivity;
import com.example.a207_demo.contactSystem.SpeakerContactAttendeeActivity;
import com.example.a207_demo.eventSystem.AttendeeEventActivity;
import com.example.a207_demo.eventSystem.AttendeeMyEventActivity;
import com.example.a207_demo.eventSystem.OrganizerEventActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.messageSystem.AnnouncementActivity;
import com.example.a207_demo.messageSystem.SpeakerAnnouncementActivity;
import com.example.a207_demo.roomSystem.RoomActivity;
import com.google.android.material.navigation.NavigationView;

/**
 * SetUpActivity class
 */
public class SetUpActivity extends CleanArchActivity {
    private DrawerLayout mDrawerLayout;
    private String ID;
    private Intent intent;

    /**
     * initialize action bar and navigation view
     * @param context context
     * @param id_nav_view int
     * @param id_nav_item int
     */
    public void init(AppCompatActivity context, int id_nav_view, int id_nav_item) {
        ID = getIntent().getStringExtra("ID");
        createActionBar();
        createNavView(context, id_nav_view, id_nav_item);
    }

    /**
     * create Action Bar
     */
    protected void createActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, 0, 0);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    /**
     * create Nav View
     * @param context context
     * @param id_nav_view int
     * @param id_nav_item int
     */
    protected void createNavView(final AppCompatActivity context, int id_nav_view, int id_nav_item) {
        NavigationView navigationView = findViewById(id_nav_view);
        //navigationView.setCheckedItem(id_nav_item);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_allevents:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AttendeeEventActivity.class);
                        break;
                    case R.id.nav_myEvents:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AttendeeMyEventActivity.class);
                        break;
                    case R.id. nav_contacts_attendee_for_attendee:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AttendeeContactAttendeeActivity.class);
                        break;
                    case R.id.nav_contacts_speaker_for_attendee:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AttendeeContactSpeakerActivity.class);
                        break;
                    case R.id.nav_announcements:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AnnouncementActivity.class);
                        break;
                    case R.id.nav_myEvents_speaker:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, SpeakerMyEventActivity.class);
                        break;
                    case R.id.nav_contacts_attendee_for_speaker:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(context, AttendeeContactSpeakerActivity.class));
                        break;
                    case R.id.nav_announcements_speaker:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, SpeakerAnnouncementActivity.class);
                        break;
                    case R.id.nav_allevents_organizer:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, OrganizerEventActivity.class);
                        break;
                    case R.id.nav_room:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, RoomActivity.class);
                        break;
                }
                intent.putExtra("ID", ID);
                startActivity(intent);
                return true;
            }
        });
    }

    /**
     * on Create Options Menu
     * @param menu Menu
     * @return boolean
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * on Options Item Selected
     * @param item MenuItem
     * @return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                intent = new Intent(SetUpActivity.this, Settings.class);
                intent.putExtra("ID", ID);
                startActivity(intent);
                break;
            case R.id.signOut:
                ActivityCollector.finishAll();
        }
        return true;
    }

}
