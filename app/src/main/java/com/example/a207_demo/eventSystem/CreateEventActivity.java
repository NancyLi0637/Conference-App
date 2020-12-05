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
import com.example.a207_demo.roomSystem.SelectRoomActivity;
import com.example.a207_demo.speakerSystem.SelectSpeakerActivity;
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
    private String eventRestriction = "PUBLIC";
    private String roomId;
    private List<String> speakerId;

    private FileReadWriter fileReadWriter;

    private Intent intent;

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
        //Todo: move to speaker
        fileReadWriter = getFileReadWriter();
        fileReadWriter.EventReader();
        intent = new Intent();

        Button selectRoom = findViewById(R.id.select_room);
        Button selectSpeaker = findViewById(R.id.select_speaker);
        Button back = findViewById(R.id.create_back_event);
        Button finish = findViewById(R.id.finish);
        CheckBox vipOnly = findViewById(R.id.vip_only);

        selectRoom.setOnClickListener(this);
        selectSpeaker.setOnClickListener(this);
        back.setOnClickListener(this);
        finish.setOnClickListener(this);
        vipOnly.setOnClickListener(this);
    }

    /**
     * onClick
     * @param v View
     */
    public void onClick(View v) {
        setUpData();
        switch (v.getId()) {
            case R.id.vip_only:
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    eventRestriction = "VIP-ONLY";
                } else {
                    eventRestriction = "PUBLIC";
                }
                break;
            case R.id.select_room:
                intent = new Intent(CreateEventActivity.this, SelectRoomActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.select_speaker:
                if(eventType.equals("PARTY")){
                    Toast.makeText(this, "NO SPEAKER needed for PARTY", Toast.LENGTH_LONG).show();
                }else {
                    intent = new Intent(CreateEventActivity.this, SelectSpeakerActivity.class);
                    startActivityForResult(intent, 2);
                }
                break;
            case R.id.create_back_event:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.finish:
                setUpData();
                if (dataMissing()) {
                    Toast.makeText(this, "INFORMATION missing", Toast.LENGTH_LONG).show();
                } else if (!validTitle()) {
                    Toast.makeText(this, "EVENT TITLE needs to be at least length 3 and unique", Toast.LENGTH_LONG).show();
                } else if (!validTime()) {
                    //Todo: before 0 and 12
                    Toast.makeText(this, "TIME entered is invalid!", Toast.LENGTH_LONG).show();
                } else if(!validDuration()){
                    Toast.makeText(this, "DURATION entered is invalid!", Toast.LENGTH_LONG).show();
                } else {

//                    ArrayList<String> speakerId = new ArrayList<>(Arrays.asList("speaker1"));
////
//                    boolean created = getEventManager().createEvent(eventTitle, "room1", speakerId,
//                            eventTime, eventDuration, restriction, eventType);

                    //fileReadWriter.EventWriter();
//                    intent = new Intent();
//                    setResult(RESULT_OK, intent);
//                    finish();

//                    if(created){
//                        fileReadWriter.EventWriter();
//                        System.out.println("EVENT8" + getEventManager().getAllEvent());
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

    private boolean dataMissing() {
        return eventTitle.equals("") || eventTime.equals("") || eventDuration.equals("");
    }

    private boolean validTitle(){
        return getEventManager().checkValidTitle(eventTitle);
    }

    private boolean validTime() {
        //return getEventManager().checkValidTimeFormat(eventTime) && getEventManager().checkValidFutureTime(eventTime);
        return true;
    }

    private boolean validDuration(){
        return getEventManager().checkValidDuration(eventDuration);
    }

    /**
     * onActivityResult
     * @param requestCode requestCode
     * @param resultCode resultCode
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                   roomId = getRoomManager().changeNumTOID(data.getStringExtra("roomNum"));
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                   speakerId = getUserManager().getUserIdsFromName(
                           data.getStringArrayListExtra("speakerId"));
                }
        }
    }

}