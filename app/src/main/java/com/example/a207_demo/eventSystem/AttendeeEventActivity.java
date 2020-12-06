package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a207_demo.entities.Discussion;
import com.example.a207_demo.entities.Party;
import com.example.a207_demo.entities.Talk;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Event activity to show attendee.
 */
public class AttendeeEventActivity extends EventActivity {

    //Todo: generate event list through manager
    private List<Event> eventList = new ArrayList<>();
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
        super.init(this, R.id.nav_view_attendee, R.id.nav_allevents);
        createEventMenu();
    }

    /**
     * create Event Menu
     */
    protected void createEventMenu() {
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(recyclerView);
        initEvents();
        attendeeEventAdapter = new AttendeeEventAdapter(this, eventList);
        recyclerView.setAdapter(attendeeEventAdapter);
    }

    /**
     * initialise Events
     */
    protected void initEvents() {
        super.initEvents();
        eventList = getEventManager().getAllEvent();

        //Todo: implement image later
        for (Event event : eventList) {
            event.setImageId(R.drawable.default_image);
        }
    }
}
