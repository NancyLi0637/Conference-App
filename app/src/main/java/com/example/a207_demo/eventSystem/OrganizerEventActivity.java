package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

public class OrganizerEventActivity extends EventActivity implements View.OnClickListener{

    //TODO: generate event list through manager
    private List<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        super.init(this, R.id.nav_view_organizer, R.id.nav_allevents_organizer);

        Button addEvent = findViewById(R.id.btn_addEvent);
        addEvent.setOnClickListener(this);

        createEventMenu(eventList);
    }

    @Override
    public void onClick(View v){
        Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
    }

    protected void createEventMenu(List<Event> eventList){
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(eventList, recyclerView);
        OrganizerEventAdapter eventAdapter = new OrganizerEventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
    }

    protected void initEvents(List<Event> eventList){
        //Todo: generate Event through use case

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