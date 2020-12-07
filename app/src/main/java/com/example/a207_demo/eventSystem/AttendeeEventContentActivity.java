package com.example.a207_demo.eventSystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

/**
 * Event Content Activity class for attendee.
 */
public class AttendeeEventContentActivity extends EventContentActivity implements View.OnClickListener{

    private String eventID;

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content_attendee);

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
        Button eventSignUp = findViewById(R.id.btn_signUp_event);
        eventSignUp.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        if(isInEvent()){
            Toast.makeText(this, "You are in this event already!", Toast.LENGTH_LONG).show();
        }else if(hasRestriction()){
            Toast.makeText(this, "Only VIP Users can sign up for this event!", Toast.LENGTH_LONG).show();
        }else if(noSpace()){
            Toast.makeText(this, "The event is full!", Toast.LENGTH_LONG).show();
        }else{
            boolean signedUp = getEventManager().addAttendeeToEvent(getID(), eventID);

            if(signedUp){
                Toast.makeText(this, "You have SUCCESSFULLY signed up!", Toast.LENGTH_LONG).show();
                super.writeEvent();
                startActivity(new Intent(AttendeeEventContentActivity.this, AttendeeEventActivity.class));
            }else{
                Toast.makeText(this, "There is TIME CONFLICT in your events!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean isInEvent(){
        return getEventManager().inEvent(getID(), eventID);
    }

    /**
     * cancelEvent
     */
    private boolean hasRestriction(){
        return getEventManager().restricted(getID(), eventID, getUserManager());
    }

    private boolean noSpace(){
        return getEventManager().eventFull(eventID);
    }

}