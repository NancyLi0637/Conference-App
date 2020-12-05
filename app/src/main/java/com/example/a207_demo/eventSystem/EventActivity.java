package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * EventActivity
 */
public class EventActivity extends SetUpActivity {
    private FileReadWriter fileReadWriter;

    /**
     * createEventMenu
     * @param recyclerView RecyclerView recyclerView
     */
    protected void createEventMenu(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * initEvents
     */
    protected void initEvents() {
        fileReadWriter = getFileReadWriter();
        fileReadWriter.reset();
        fileReadWriter.EventReader();
    }
}