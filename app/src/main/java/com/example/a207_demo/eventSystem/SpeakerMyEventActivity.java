package com.example.a207_demo.eventSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SpeakerMyEventActivity extends EventActivity {

    //Todo: generate event list by use case
    private List<Event> eventList = new ArrayList<>();

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_speaker);

        init();

        ActivityCollector.addActivity(this);
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
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(recyclerView);
        SpeakerEventAdapter speakerEventAdapter = new SpeakerEventAdapter(this, eventList);
        recyclerView.setAdapter(speakerEventAdapter);
    }

    /**
     * initEvents
     */
    protected void initEvents(){
//        //Todo: generate Event list of this speaker
//        for(int i = 0; i < 2; i++) {
//            Event event1 = new Event("Event10", "BF101", "", "13:00", R.drawable.default_image);
//            eventList.add(event1);
//            Event event2 = new Event("Event20", "TH305", "", "13:00", R.drawable.default_image);
//            eventList.add(event2);
//            Event event3 = new Event("Event30", "RC104", "", "13:00", R.drawable.default_image);
//            eventList.add(event3);
//            Event event4 = new Event("Event40", "RC507", "", "13:00", R.drawable.default_image);
//            eventList.add(event4);
//            Event event5 = new Event("Event50", "SU302", "", "13:00", R.drawable.default_image);
//            eventList.add(event5);
//        }
    }

}