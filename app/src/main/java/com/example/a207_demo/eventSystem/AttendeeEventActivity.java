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
    private EventManager eventManager;

    /**
     * Required function to initiate an Activity class.
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
    public void init(){
        super.init(this, R.id.nav_view_attendee, R.id.nav_allevents);
        eventManager = getEventManager();
        createEventMenu();
    }

    protected void createEventMenu(){
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(recyclerView);
        initEvents();
        AttendeeEventAdapter attendeeEventAdapter = new AttendeeEventAdapter(this, eventList);
        recyclerView.setAdapter(attendeeEventAdapter);
    }

    protected void initEvents(){
//        eventManager.loadEvent("TALK", "Exercise with Hardy!", "og12jd3a",
//                "041e891e", new ArrayList(Arrays.asList("c5201361")), "2020111509", "2");
//        eventManager.loadEvent("DISCUSSION", "Practice with Steve!", "djn120da",
//                "041e891e", new ArrayList(Arrays.asList("1a251123")), "2020122010", "4");
        //super.initEvents();
        eventList = eventManager.getAllEvent();

        //System.out.println("HELLLLOOOOOOO");
        for (Event event : eventList){
            //System.out.println("nancyli");
            event.setImageId(R.drawable.default_image);
        }

//        for(int i = 0; i < 2; i++){
//            Event event1 = new Party("event1", "bf202", "20201102", "4");
//            event1.setImageId(R.drawable.default_image);
//            eventList.add(event1);
//            Event event2 = new Party("event2", "bf202", "20201102", "4");
//            event2.setImageId(R.drawable.default_image);
//            eventList.add(event2);
//            Event event3 = new Party("event3", "bf202", "20201102", "4");
//            event3.setImageId(R.drawable.default_image);
//            eventList.add(event3);
//            Event event4 = new Party("event4", "bf202", "20201102", "4");
//            event4.setImageId(R.drawable.default_image);
//            eventList.add(event4);
//            Event event5 = new Party("event5", "bf202", "20201102", "4");
//            event5.setImageId(R.drawable.default_image);
//            eventList.add(event5);
//        }
    }
}
