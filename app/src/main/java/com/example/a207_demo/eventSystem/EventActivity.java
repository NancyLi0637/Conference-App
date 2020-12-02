package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.SetUpActivity;

import java.util.List;

public abstract class EventActivity extends SetUpActivity {

    protected void createEventMenu(List<Event> eventList, RecyclerView recyclerView){
        initEvents(eventList);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    abstract protected void initEvents(List<Event> eventList);
}