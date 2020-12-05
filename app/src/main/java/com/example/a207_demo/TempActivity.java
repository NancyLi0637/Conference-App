package com.example.a207_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.eventSystem.AttendeeEventActivity;
import com.example.a207_demo.eventSystem.OrganizerEventActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * TempActivity
 */
public class TempActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * onCreate
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        Button attendee = findViewById(R.id.btn_attendee);
        Button organizer = findViewById(R.id.btn_organizer);
        Button speaker = findViewById(R.id.btn_speaker);

        attendee.setOnClickListener(this);
        organizer.setOnClickListener(this);
        speaker.setOnClickListener(this);

        ActivityCollector.addActivity(this);
    }

    /**
     * onClick
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_attendee:
                startActivity(new Intent(TempActivity.this, AttendeeEventActivity.class));
                break;
            case R.id.btn_organizer:
                startActivity(new Intent(TempActivity.this, OrganizerEventActivity.class));
                break;
            case R.id.btn_speaker:
                startActivity(new Intent(TempActivity.this, SpeakerMyEventActivity.class));
                break;
        }
    }


}