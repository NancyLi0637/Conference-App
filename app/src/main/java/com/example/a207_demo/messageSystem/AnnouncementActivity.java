package com.example.a207_demo.messageSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;

/**
 * AnnouncementActivity
 */
public class AnnouncementActivity extends SetUpActivity {

    private ArrayList<String> announcementList;

    protected void createAnnouncementMenu(){
        RecyclerView recyclerView = findViewById(R.id.announcement_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initAnnouncements();
        AnnouncementAdapter announcementAdapter = new AnnouncementAdapter(this, announcementList);
        recyclerView.setAdapter(announcementAdapter);
    }

    private void initAnnouncements(){
        super.init();
        announcementList = getUserManager().getAnnouncements(getID());
    }

}