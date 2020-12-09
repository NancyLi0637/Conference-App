package com.example.a207_demo.eventSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.roomSystem.SelectRoomActivity;
import com.example.a207_demo.speakerSystem.SelectSpeakerActivity;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * CreateEventActivity for creating a new event
 */
public class CreateEventActivity extends CleanArchActivity implements View.OnClickListener, Serializable {
    private String eventType;
    private String eventTitle;
    private String eventTime;
    private String eventDuration;
    private String eventRestriction = "PUBLIC";
    private String eventCapacity;
    private String roomID;
    private ArrayList<String> speakerId = new ArrayList<>();

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
        super.reset();
        super.readEvent();
        super.readRoom();
        super.readUser();
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
                if(!getRoomManager().hasRooms()){
                    Toast.makeText(this, "No Room available. Go create one first!", Toast.LENGTH_LONG).show();
                }else if(!validTime()){
                    Toast.makeText(this, "You must enter VALID TIME first!", Toast.LENGTH_LONG).show();
                }else if(!validInteger(eventCapacity)){
                    Toast.makeText(this, "You must enter VALID CAPACITY first!", Toast.LENGTH_LONG).show();
                }else{
                    intent = new Intent(CreateEventActivity.this, SelectRoomActivity.class);
                    loadData();
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.select_speaker:
                if(eventType.equals("PARTY")){
                    Toast.makeText(this, "NO SPEAKER needed for PARTY", Toast.LENGTH_LONG).show();
                }else if(!getSpeakerManager().hasSpeakers()){
                    Toast.makeText(this, "No Speaker available. Go create one first!", Toast.LENGTH_LONG).show();
                }else if(!validTime()){
                    Toast.makeText(this, "You must enter VALID TIME first!", Toast.LENGTH_LONG).show();
                }else {
                    intent = new Intent(CreateEventActivity.this, SelectSpeakerActivity.class);
                    loadData();
                    startActivityForResult(intent, 2);
                }
                break;
            case R.id.finish:
                setUpData();
                if (dataMissing()) {
                    Toast.makeText(this, "INFORMATION missing", Toast.LENGTH_LONG).show();
                } else if (!validTitle()) {
                    Toast.makeText(this, "EVENT TITLE needs to be at least length 3 and unique", Toast.LENGTH_LONG).show();
                } else if (!validTime()) {
                    //Todo: between 0 and 12
                    Toast.makeText(this, "TIME entered is invalid!", Toast.LENGTH_LONG).show();
                } else if(!validInteger(eventDuration)){
                    Toast.makeText(this, "DURATION entered is invalid!", Toast.LENGTH_LONG).show();
                } else if(!validInteger(eventCapacity)){
                    Toast.makeText(this, "EVENT CAPACITY entered is invalid!", Toast.LENGTH_LONG).show();
                } else if(eventType.equals("TALK") && speakerId.size() > 1){
                    Toast.makeText(this, "Only ONE SPEAKER allowed for Talk Events!", Toast.LENGTH_LONG).show();
                } else {
                    boolean created = getEventManager().createEvent(eventType, eventTitle, roomID, speakerId,
                            eventTime, eventDuration, eventRestriction, Integer.parseInt(eventCapacity));

                    if(created){
                        Toast.makeText(this, "You have SUCCESSFULLY created event!", Toast.LENGTH_LONG).show();
                        super.writeEvent();
                        speakerId = new ArrayList<>();
                        intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }else{
                        Toast.makeText(this, "There is TIME CONFLICT in your event.", Toast.LENGTH_LONG).show();
                        roomID = null;
                        speakerId = new ArrayList<>();
                    }
                }
                break;
            case R.id.create_back_event:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    private void setUpData() {
        Spinner type = findViewById(R.id.event_type);
        EditText title = findViewById(R.id.event_title);
        EditText startTime = findViewById(R.id.event_time);
        EditText duration = findViewById(R.id.event_duration);
        EditText capacity = findViewById(R.id.event_capacity);

        eventType = String.valueOf(type.getSelectedItem());
        eventTitle = title.getText().toString();
        eventTime = startTime.getText().toString();
        //TODO: integer duration later?
        eventDuration = duration.getText().toString();
        eventCapacity = capacity.getText().toString();
    }

    private void loadData(){
        intent.putExtra("eventTime", eventTime);
        intent.putExtra("eventDuration", eventDuration);
        intent.putExtra("eventCapacity", eventCapacity);
    }

    private boolean dataMissing() {
        return eventTitle.equals("") || eventTime.equals("") || eventDuration.equals("") ||
                eventCapacity.equals("") || roomID == null;
    }

    private boolean validTitle(){
        return getEventManager().checkValidTitle(eventTitle);
    }

    private boolean validTime() {
        return getEventManager().checkValidTime(eventTime);
    }

    private boolean validInteger(String num){
        return getEventManager().checkValidInteger(num);
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
                   roomID = data.getStringExtra("roomID");
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                   ArrayList<String> speakerNames = data.getStringArrayListExtra("speakerNames");
                   if(speakerNames != null){
                       speakerId = getUserManager().getUserIdsFromName(speakerNames);
                   }
                }
        }
    }
}