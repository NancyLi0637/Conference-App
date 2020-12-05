package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.entities.Party;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * OrganizerEventActivity
 */
public class OrganizerEventActivity extends EventActivity implements View.OnClickListener {

    private List<Event> eventList = new ArrayList<>();
    private OrganizerEventAdapter eventAdapter;
    private EventManager eventManager;

    /**
     * onCreate
     * @param savedInstanceState
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
        eventManager = getEventManager();

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
        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        super.createEventMenu(recyclerView);
        initEvents();
        eventAdapter = new OrganizerEventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
    }

    /**
     * initEvents
     */
    protected void initEvents() {
        super.initEvents();
        eventList = eventManager.getAllEvent();

        //Todo: implement image later
        for (Event event : eventList) {
            event.setImageId(R.drawable.default_image);
        }

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
                    refreshEvents();
                }
                break;
        }
    }

    /**
     * refresh Events, update the screen to show events
     */
    private void refreshEvents() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        createEventMenu();
                        eventAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}