package com.example.a207_demo.messageSystem;

import android.os.Bundle;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * SpeakerAnnouncementActivity
 */
public class SpeakerAnnouncementActivity extends AnnouncementActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_speaker);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_speaker, R.id.nav_announcements_speaker);
        super.createAnnouncementMenu();
    }

}