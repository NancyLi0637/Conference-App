package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;

import java.util.ArrayList;

/**
 * Event activity to show attendee.
 */
public class AttendeeEventActivity extends EventActivity {

    private ArrayList<ArrayList<String>> eventList;
    private AttendeeEventAdapter attendeeEventAdapter;

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
        super.init(this, R.id.nav_view_attendee, R.id.nav_allevents); // From SetUpActivity
        createEventMenu();
    }

    /**
     * create Event Menu
     */
    protected void createEventMenu() {
        initEvents();
        // Firstly, setLayoutManager for this recyclerView
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        attendeeEventAdapter = new AttendeeEventAdapter(this, eventList, getID());
        super.createEventMenu(recyclerView, layoutManager, attendeeEventAdapter);

        // Secondly, prepare data: list of events to show


        // Third, setAdapter for this recyclerView

        //recyclerView.setAdapter(attendeeEventAdapter);
    }

    /**
     * initialise Events
     */
    protected void initEvents() {
        super.initEvents();
        eventList = getEventManager().generateAllInfo(getEventManager().getAllEventID());

    }

    protected void refreshEvents() {
        createEventMenu();
        attendeeEventAdapter.notifyDataSetChanged();
        super.refreshEvents();
    }
}
