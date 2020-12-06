package com.example.a207_demo.eventSystem;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * OrganizerEventContentActivity
 */
public class OrganizerEventContentActivity extends EventContentActivity{

    /**
     * onCreate
     * @param savedInstanceState Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * fillContent
     * @param eventTitle String eventTitle
     * @param eventContent String eventContent
     */
    protected void fillContent(String eventTitle, String eventContent){
        super.fillContent(eventTitle, eventContent);
        Button eventCancel = findViewById(R.id.btn_cancel_event);
        eventCancel.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        // TODO: Organizer cancel an event
    }

}