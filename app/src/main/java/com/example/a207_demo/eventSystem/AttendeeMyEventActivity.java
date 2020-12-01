package com.example.a207_demo.eventSystem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

public class AttendeeMyEventActivity extends SetUpActivity {

    //Todo: access Event Use case
    private List<Event> eventList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        super.createActionBar();
        super.createNavView(this, R.id.nav_myEvents);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}