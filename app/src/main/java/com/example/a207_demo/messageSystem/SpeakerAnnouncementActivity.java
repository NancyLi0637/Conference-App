package com.example.a207_demo.messageSystem;

import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;

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