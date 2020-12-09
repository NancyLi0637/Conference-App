package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

public class Top5EventsActivity extends EventActivity {

    private ArrayList<ArrayList<String>> eventList;
    private OrganizerEventAdapter organizerEventAdapter;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_top5);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_top_5_events);
        createEventMenu();
    }

    /**
     * createEventMenu
     */

    protected void createEventMenu() {
        initEvents();
        RecyclerView recyclerView = findViewById(R.id.event_top5_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        organizerEventAdapter = new OrganizerEventAdapter(this, eventList);
        super.createEventMenu(recyclerView, layoutManager, organizerEventAdapter);


        //recyclerView.setAdapter(organizerEventAdapter);
    }

    /**
     * initEvents
     */
    @Override
    protected void initEvents() {
        super.initEvents();

        eventList = getEventManager().generateAllInfo(getEventManager().getTop5Events());

//        //Todo: implement image later
//        for (Event event : eventList) {
//            event.setImageId(R.drawable.default_image);
//        }

    }

    protected void refreshEvents(){
        createEventMenu();
        organizerEventAdapter.notifyDataSetChanged();
        super.refreshEvents();
    }
}