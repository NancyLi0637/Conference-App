package com.example.a207_demo.eventSystem;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

/**
 *
 */
public class SpeakerMyEventActivity extends EventActivity {

    private ArrayList<ArrayList<String>> eventList;
    private SpeakerMyEventAdapter speakerMyEventAdapter;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_speaker);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * init
     */
    public void init(){
        super.init(this, R.id.nav_view_speaker, R.id.nav_myEvents_speaker);
        createEventMenu();
    }

    /**
     * createEventMenu
     */
    protected void createEventMenu(){
        initEvents();
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        speakerMyEventAdapter = new SpeakerMyEventAdapter(this, eventList);
        super.createEventMenu(recyclerView, layoutManager, speakerMyEventAdapter);
    }

    /**
     * initEvents
     */
    protected void initEvents(){
        super.initEvents();
        eventList = getEventManager().generateAllInfo(getEventManager().getEventsFromSpeaker(getID()));
    }

    protected void refreshEvents(){
        createEventMenu();
        speakerMyEventAdapter.notifyDataSetChanged();
        super.refreshEvents();
    }

}