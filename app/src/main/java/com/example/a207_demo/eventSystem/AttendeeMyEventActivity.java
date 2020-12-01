package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Event activity of events attendee signed up for.
 */
public class AttendeeMyEventActivity extends EventActivity {

    //Todo: access Event Use case
    private List<Event> eventList = new ArrayList<>();

    /**
     * Set up the activity.
     */
    @Override
    public void init(){
        super.createActionBar();
        super.createNavView(this, R.id.nav_myEvents);
        createEventMenu();
    }

    protected void createEventMenu(){
        initEvents();

        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        EventAdapter eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
    }

    @Override
    protected void initEvents(){
        //Todo: generate event list of this attendee
        for(int i = 0; i < 2; i++) {
            Event event1 = new Event("Event1", "BF101", "", "13:00", R.drawable.default_image);
            eventList.add(event1);
            Event event2 = new Event("Event2", "TH305", "", "13:00", R.drawable.default_image);
            eventList.add(event2);
            Event event3 = new Event("Event3", "RC104", "", "13:00", R.drawable.default_image);
            eventList.add(event3);
            Event event4 = new Event("Event4", "RC507", "", "13:00", R.drawable.default_image);
            eventList.add(event4);
            Event event5 = new Event("Event5", "SU302", "", "13:00", R.drawable.default_image);
            eventList.add(event5);
        }
    }

}