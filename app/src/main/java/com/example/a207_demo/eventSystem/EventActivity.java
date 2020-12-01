package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Event activity to show attendee.
 */
public class EventActivity extends SetUpActivity {

    //Todo: access Event Use case
    private List<Event> eventList = new ArrayList<>();

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        init();

        ActivityCollector.addActivity(this);

    }

    /**
     * Set up the activity.
     */
    public void init(){
        super.createActionBar();
        super.createNavView(this, R.id.nav_allevents);
        createEventMenu();
    }

    /**
     * Set up action bar
     * @param menu Menu items of the activity
     * @return true if created successfully
     */
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Menu item listener for clicking events.
     * @param item Item clicked
     * @return true if clicked successfully
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void createEventMenu(){
        initEvents();

        RecyclerView recyclerView = findViewById(R.id.event_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        EventAdapter eventAdapter = new EventAdapter(this, eventList);
        recyclerView.setAdapter(eventAdapter);
    }

    private void initEvents(){
        //Todo: access Event Use case to generate event
        for (int i = 0; i < 50; i++){
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
