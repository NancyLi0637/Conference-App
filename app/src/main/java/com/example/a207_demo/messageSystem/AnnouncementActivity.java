package com.example.a207_demo.messageSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.BaseActivity;

import java.util.ArrayList;

/**
 * AnnouncementActivity
 */
public class AnnouncementActivity extends BaseActivity {

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
        announcementList = getUserManager().getAnnouncements(getID());
    }

}