package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Event activity of events attendee signed up for.
 */
public class AttendeeMyEventActivity extends AttendeeEventActivity {

    //Todo: access Event Use case
    private List<Event> eventList = new ArrayList<>();

    /**
     * Set up the activity.
     */
    @Override
    public void init(){
        super.init(this, R.id.nav_view_attendee, R.id.nav_myEvents);
        createEventMenu(eventList);
    }

    @Override
    protected void createEventMenu(List<Event> eventList){
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(eventList, recyclerView);
        AttendeeMyEventAdapter attendeeMyEventAdapter = new AttendeeMyEventAdapter(this, eventList);
        recyclerView.setAdapter(attendeeMyEventAdapter);
    }

    @Override
    protected void initEvents(List<Event> eventList){
        //Todo: generate event list of this attendee
        for(int i = 0; i < 2; i++) {
            Event event1 = new Event("Event5", "BF101", "", "13:00", R.drawable.default_image);
            eventList.add(event1);
            Event event2 = new Event("Event4", "TH305", "", "13:00", R.drawable.default_image);
            eventList.add(event2);
            Event event3 = new Event("Event3", "RC104", "", "13:00", R.drawable.default_image);
            eventList.add(event3);
            Event event4 = new Event("Event2", "RC507", "", "13:00", R.drawable.default_image);
            eventList.add(event4);
            Event event5 = new Event("Event1", "SU302", "", "13:00", R.drawable.default_image);
            eventList.add(event5);
        }
    }

}