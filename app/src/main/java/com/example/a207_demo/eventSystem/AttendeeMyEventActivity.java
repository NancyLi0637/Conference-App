package com.example.a207_demo.eventSystem;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

/**
 * Event activity of events attendee signed up for.
 */
public class AttendeeMyEventActivity extends EventActivity {

    private ArrayList<ArrayList<String>> eventList;
    private AttendeeMyEventAdapter attendeeMyEventAdapter;

    /**
     * Required function to initiate an Activity class.
     *
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_attendee);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * Set up the activity.
     */
    public void init() {
        super.init(this, R.id.nav_view_attendee, R.id.nav_myEvents);
        createEventMenu();
    }

    protected void createEventMenu() {
        initEvents();
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        attendeeMyEventAdapter = new AttendeeMyEventAdapter(this, eventList, getID());
        super.createEventMenu(recyclerView, layoutManager, attendeeMyEventAdapter);
        //recyclerView.setAdapter(attendeeMyEventAdapter);
    }

    /**
     * initialise Events
     */
    @Override
    protected void initEvents() {
        super.initEvents();
        eventList = getEventManager().generateAllInfo(getEventManager().getEventsFromAttendee(getID()));
    }

    protected void refreshEvents() {
        createEventMenu();
        attendeeMyEventAdapter.notifyDataSetChanged();
        super.refreshEvents();
    }

}