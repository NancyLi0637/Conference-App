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

import com.bumptech.glide.Glide;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

/**
 * Event Content Activity class for attendee.
 */
public class AttendeeEventContentActivity extends EventContentActivity implements View.OnClickListener{

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

    protected void fillContent(String eventTitle, String eventContent, int eventImageId){
        super.fillContent(eventTitle, eventContent, eventImageId);
        Button eventSignUp = findViewById(R.id.btn_signUp_event);
        eventSignUp.setOnClickListener(this);
    }

}