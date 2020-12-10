package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

public class EventEnrollmentActivity extends EventActivity {

    private EventEnrollmentAdapter eventEnrollmentAdapter;
    private ArrayList<ArrayList<String>> eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_enrollment);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        super.init(this, R.id.nav_view_organizer, R.id.event_enrollment_info);
        createEventMenu();
    }

    protected void createEventMenu() {
        initEvents();
        RecyclerView recyclerView = findViewById(R.id.event_enrollment_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        eventEnrollmentAdapter = new EventEnrollmentAdapter(this, eventList);

        super.createEventMenu(recyclerView, layoutManager, eventEnrollmentAdapter);
    }

    /**
     * initEvents
     */
    @Override
    protected void initEvents() {
        super.initEvents();

        eventList = getEventManager().generateAllInfo(getEventManager().getAllEventID());
    }

    protected void refreshEvents(){
        createEventMenu();
        eventEnrollmentAdapter.notifyDataSetChanged();
        super.refreshEvents();
    }
}