package com.example.a207_demo.utility;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;
import com.example.a207_demo.accountSystem.AllAccountActivity;
import com.example.a207_demo.accountSystem.AttendeeAccountActivity;
import com.example.a207_demo.accountSystem.OrganizerAccountActivity;
import com.example.a207_demo.accountSystem.SpeakerAccountActivity;
import com.example.a207_demo.accountSystem.VIPUserAccountActivity;
import com.example.a207_demo.contactSystem.AttendeeContactAttendeeActivity;
import com.example.a207_demo.contactSystem.AttendeeContactSpeakerActivity;
import com.example.a207_demo.contactSystem.OrganizerContactAttendeeActivity;
import com.example.a207_demo.contactSystem.OrganizerContactOrganizerActivity;
import com.example.a207_demo.contactSystem.OrganizerContactSpeakerActivity;
import com.example.a207_demo.contactSystem.SpeakerContactAttendeeActivity;
import com.example.a207_demo.eventSystem.AttendeeEventActivity;
import com.example.a207_demo.eventSystem.AttendeeMyEventActivity;
import com.example.a207_demo.eventSystem.EventEnrollmentActivity;
import com.example.a207_demo.eventSystem.OrganizerEventActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.eventSystem.Top5EventsActivity;
import com.example.a207_demo.messageSystem.AttendeeAnnouncementActivity;
import com.example.a207_demo.messageSystem.SpeakerAnnouncementActivity;
import com.example.a207_demo.roomSystem.RoomActivity;
import com.google.android.material.navigation.NavigationView;

/**
 * SetUpActivity class
 */
public class BaseActivity extends CleanArchActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private Intent intent;

    private String ID;
    private String TYPE;
    private String EMAIL;
    private String USERNAME;

    /**
     * initialize action bar and navigation view
     * @param context context
     * @param id_nav_view int
     * @param id_nav_item int
     */
    protected void init(AppCompatActivity context, int id_nav_view, int id_nav_item) {
        ID = getIntent().getStringExtra("ID");
        TYPE = getIntent().getStringExtra("TYPE");
        EMAIL = getIntent().getStringExtra("EMAIL");
        USERNAME = getIntent().getStringExtra("USERNAME");
        super.setInfo(ID, TYPE, EMAIL, USERNAME);
        createActionBar();
        createNavView(context, id_nav_view, id_nav_item);
    }

    public String getID(){
        return ID;
    }

    public String getTYPE() { return this.TYPE;}

    public String getEMAIL() { return this.EMAIL;}

    public String getUSERNAME() {
        return this.USERNAME;
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
        navigationView = findViewById(id_nav_view);
        navigationView.setCheckedItem(id_nav_item);
        loadInfo();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    //ATTENDEE MENU
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
                    case R.id.nav_announcements_attendee:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AttendeeAnnouncementActivity.class);
                        break;
                    //ORGANIZER MENU
                    case R.id.nav_allevents_organizer:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, OrganizerEventActivity.class);
                        break;
                    case R.id.nav_top_5_events:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, Top5EventsActivity.class);
                        break;
                    case R.id.nav_event_enrollment:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, EventEnrollmentActivity.class);
                        break;
                    case R.id.nav_contacts_organizer_for_organizer:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, OrganizerContactOrganizerActivity.class);
                        break;
                    case R.id.nav_contacts_attendee_for_organizer:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, OrganizerContactAttendeeActivity.class);
                        break;
                    case R.id.nav_contacts_speaker_for_organizer:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, OrganizerContactSpeakerActivity.class);
                        break;
                    case R.id.nav_room:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, RoomActivity.class);
                        break;
                    case R.id.nav_account_all:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AllAccountActivity.class);
                        break;
                    case R.id.nav_account_organizer:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, OrganizerAccountActivity.class);
                        break;
                    case R.id.nav_account_speaker:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, SpeakerAccountActivity.class);
                        break;
                    case R.id.nav_account_attendee:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, AttendeeAccountActivity.class);
                        break;
                    case R.id.nav_account_vipUser:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, VIPUserAccountActivity.class);
                        break;
                    //SPEAKER MENU
                    case R.id.nav_myEvents_speaker:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, SpeakerMyEventActivity.class);
                        break;
                    case R.id.nav_contacts_attendee_for_speaker:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, SpeakerContactAttendeeActivity.class);
                        break;
                    case R.id.nav_announcements_speaker:
                        mDrawerLayout.closeDrawers();
                        intent = new Intent(context, SpeakerAnnouncementActivity.class);
                        break;
                }
                intent.putExtra("ID", ID);
                intent.putExtra("TYPE", TYPE);
                intent.putExtra("EMAIL", EMAIL);
                intent.putExtra("USERNAME", USERNAME);
                startActivity(intent);
                return true;
            }
        });
    }

    private void loadInfo(){
        View view = navigationView.getHeaderView(0);
        ImageView userPic = view.findViewById(R.id.profile_image);
        TextView userType = view.findViewById(R.id.nav_head_type);
        TextView userName = view.findViewById(R.id.nav_head_username);
        TextView userEmail = view.findViewById(R.id.nav_head_email);

        userType.setText(TYPE);
        userName.setText(USERNAME);
        userEmail.setText(EMAIL);

        loadImage(userPic);
    }

    private void loadImage(ImageView userPic){
        if(TYPE.equals("ORGANIZER")){
            Glide.with(this).load(R.drawable.organizer2).into(userPic);
            //userPic.setImageResource(R.drawable.organizer);
        }else if(TYPE.equals("SPEAKER")){
            userPic.setImageResource(R.drawable.speaker);
        }else if(TYPE.equals("ATTENDEE")){
            Glide.with(this).load(R.drawable.icon_contact_blue).into(userPic);
            //userPic.setImageResource(R.drawable.icon_contact_gray);
        }else{
            userPic.setImageResource(R.drawable.vip);
        }
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
                //NavUtils.navigateUpFromSameTask(this);
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                intent = new Intent(BaseActivity.this, Settings.class);
                intent.putExtra("ID", ID);
                intent.putExtra("TYPE", TYPE);
                intent.putExtra("EMAIL", EMAIL);
                intent.putExtra("USERNAME", USERNAME);
                startActivity(intent);
                break;
            case R.id.signOut:
                ActivityCollector.finishAll();
        }
        return true;
    }

}
