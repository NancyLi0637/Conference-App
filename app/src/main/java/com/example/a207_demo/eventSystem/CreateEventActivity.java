package com.example.a207_demo.eventSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * CreateEventActivity for creating a new event
 */
public class CreateEventActivity extends CleanArchActivity implements View.OnClickListener {
    private String eventType;
    private String eventTitle;
    private String eventTime;
    private String eventDuration;
    private String restriction;

    private FileReadWriter fileReadWriter;
    private EventManager eventManager;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * initialize
     */
    private void init() {
        eventManager = getEventManager();
        fileReadWriter = getFileReadWriter();

        Button next = findViewById(R.id.btn_to_room);
        CheckBox vipOnly = findViewById(R.id.vip_only);

        next.setOnClickListener(this);
        vipOnly.setOnClickListener(this);
    }

    /**
     * onClick
     * @param v View
     */
    public void onClick(View v) {
        setUpData();
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_to_room:
                if (dataMissing()) {
                    Toast.makeText(this, "INFORMATION missing", Toast.LENGTH_LONG).show();
                } else if (eventTitle.length() < 3) {
                    Toast.makeText(this, "EVENT TITLE needs to be at least length 3", Toast.LENGTH_LONG).show();
                } else if (!validTime()) {
                    Toast.makeText(this, "TIME entered is invalid!", Toast.LENGTH_LONG).show();
                } else {
                    //Todo: implement Room system
                    //intent = new Intent(CreateEventActivity.this, ChooseRoom.class);
                    //startActivityForResult(intent, 1);
                    //Todo: implement Speaker system
                    ArrayList<String> speakerId = new ArrayList<>(Arrays.asList("speaker1"));

                    boolean created = eventManager.createEvent(eventTitle, "room1", speakerId,
                            eventTime, eventDuration, restriction, eventType);

                    fileReadWriter.EventWriter();
                    intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();

//                    if(created){
//                        fileReadWriter.EventWriter();
//                        intent = new Intent();
//                        setResult(RESULT_OK, intent);
//                        finish();
//                    }else{
//                        //Todo: better time suggestion
//                        Toast.makeText(this, "There is TIME CONFLICT in your event.", Toast.LENGTH_LONG).show();
//                    }
                }
                break;
        }
    }

    /**
     * onCheckBoxClicked
     * @param v View
     */
    public void onCheckBoxClicked(View v) {
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()) {
            case (R.id.vip_only):
                if (checked) {
                    //Todo: fix null checkbox
                    restriction = "VIP-ONLY";
                } else {
                    restriction = "PUBLIC";
                }
                break;
        }
    }

    /**
     * setUpData
     */
    private void setUpData() {
        Spinner type = findViewById(R.id.event_type);
        EditText title = findViewById(R.id.event_title);
        EditText startTime = findViewById(R.id.event_time);
        EditText duration = findViewById(R.id.duration);

        eventType = String.valueOf(type.getSelectedItem());
        eventTitle = title.getText().toString();
        eventTime = startTime.getText().toString();
        eventDuration = duration.getText().toString();
    }

    /**
     * check if there is data missing
     * @return boolean
     */
    private boolean dataMissing() {
        return eventType == null || eventTitle == null || eventTime == null || eventDuration == null;
    }

    /**
     * check if it is a valid Time
     * @return boolean
     */
    private boolean validTime() {
        return eventManager.checkValidTimeFormat(eventTime) && eventManager.checkValidFutureTime(eventTime);
    }

}