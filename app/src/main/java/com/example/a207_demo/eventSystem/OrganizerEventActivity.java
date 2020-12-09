package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * OrganizerEventActivity
 */
public class OrganizerEventActivity extends EventActivity implements View.OnClickListener, Serializable {

    private ArrayList<ArrayList<String>> eventList;
    private OrganizerEventAdapter organizerEventAdapter;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_allevents_organizer);

        Button addEvent = findViewById(R.id.btn_addEvent);
        addEvent.setOnClickListener(this);

        createEventMenu();
    }

    /**
     * onClick
     * @param v View
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(OrganizerEventActivity.this, CreateEventActivity.class);
        startActivityForResult(intent, 1);
    }

    /**
     * createEventMenu
     */
    protected void createEventMenu() {
        initEvents();
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        organizerEventAdapter = new OrganizerEventAdapter(this, eventList);
        super.createEventMenu(recyclerView, layoutManager, organizerEventAdapter);


        //recyclerView.setAdapter(organizerEventAdapter);
    }

    /**
     * initEvents
     */
    protected void initEvents() {
        super.initEvents();

        eventList = getEventManager().generateAllInfo(getEventManager().getAllEventID());

//        //Todo: implement image later
//        for (Event event : eventList) {
//            event.setImageId(R.drawable.default_image);
//        }

    }

    /**
     * onActivityResult
     * @param requestCode requestCode
     * @param resultCode resultCode
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    super.refreshEvents();
                }
                break;
        }
    }

    protected void refreshEvents(){
        createEventMenu();
        organizerEventAdapter.notifyDataSetChanged();
        super.refreshEvents();
    }

}