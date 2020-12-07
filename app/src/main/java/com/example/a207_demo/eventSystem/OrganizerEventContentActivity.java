package com.example.a207_demo.eventSystem;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.roomSystem.Room;
import com.example.a207_demo.utility.ActivityCollector;


import java.util.ArrayList;

/**
 * OrganizerEventContentActivity
 */
public class OrganizerEventContentActivity extends EventContentActivity{

    private String eventID;

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
     */
    protected void init(){
        super.init();
        ArrayList<String> event = getIntent().getStringArrayListExtra("event");
        eventID = event.get(0);
        Button eventCancel = findViewById(R.id.btn_cancel_event);
        eventCancel.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        if(cancelEvent()){
            Toast.makeText(this, "Event is cancelled!", Toast.LENGTH_LONG).show();
            super.writeEvent();
            startActivity(new Intent(OrganizerEventContentActivity.this, OrganizerEventActivity.class));
        }else{
            Toast.makeText(this, "Some error occurred!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * cancelEvent
     */
    public boolean cancelEvent(){
        return getEventManager().cancelEvent(eventID);
    }

}