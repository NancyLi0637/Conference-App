package com.example.a207_demo.messageSystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a207_demo.R;
import com.example.a207_demo.contactSystem.ContactActivity;
import com.example.a207_demo.contactSystem.SpeakerContactActivity;
import com.example.a207_demo.eventSystem.Event;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.utility.Settings;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class SpeakerAnnouncementActivity extends SetUpActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_speaker);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init() {
        super.init(this, R.id.nav_view_speaker, R.id.nav_announcements_speaker);
    }

}