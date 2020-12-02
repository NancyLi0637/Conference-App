package com.example.a207_demo.eventSystem;

import android.os.Bundle;

import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

public class OrganizerEventContentActivity extends EventContentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    protected void fillContent(String eventTitle, String eventContent, int eventImageId){
        super.fillContent(eventTitle, eventContent, eventImageId);
        Button eventCancel = findViewById(R.id.btn_cancel_event);
        eventCancel.setOnClickListener(this);
    }

}