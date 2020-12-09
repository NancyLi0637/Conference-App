package com.example.a207_demo.eventSystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;
import com.example.a207_demo.speakerSystem.SelectSpeakerActivity;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

/**
 * OrganizerEventContentActivity
 */
public class OrganizerEventContentActivity extends EventContentActivity{

    private Intent intent;
    private SwipeRefreshLayout swipeRefreshLayout;

    private int roomCapacity;
    private int currentSize;
    private ArrayList<String> speakerIds;
    private ArrayList<String> eventSpeakerIDs;
    private ArrayList<String> eventAttendeeIDs;



    /**
     * onCreate
     * @param savedInstanceState Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content_organizer);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * fillContent
     */
    protected void init(){
        super.init();
        loadEventInfo();
        loadSwipeRefreshLayout();
        loadButtons();
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_event_announce_speaker:
                loadAnnounceData();
                intent.putExtra("userIDs", eventSpeakerIDs);
                startActivity(intent);
                break;
            case R.id.btn_event_announce_attendee:
                loadAnnounceData();
                intent.putExtra("userIDs", eventAttendeeIDs);
                startActivity(intent);
                break;
            case R.id.btn_change_cap:
                changeCapacity();
                break;
            case R.id.btn_add_speaker:
                if(getEventType().equals("TALK")){
                    Toast.makeText(this, "No additional speaker allowed!",
                            Toast.LENGTH_LONG).show();
                }else if(getEventType().equals("PARTY")){
                    Toast.makeText(this, "No speaker allowed!",
                            Toast.LENGTH_LONG).show();
                }else{
                    loadSpeakerData();
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.btn_cancel_event:
                if(cancelEvent()){
                    getUserManager().sendAnnouncement(eventSpeakerIDs, getEventTitle(),
                            getEventTitle() + " is CANCELLED!");
                    getUserManager().sendAnnouncement(eventAttendeeIDs, getEventTitle(),
                            getEventTitle() + " is CANCELLED!");
                    Toast.makeText(this, "Event is cancelled!", Toast.LENGTH_LONG).show();
                    super.writeEvent();
                    super.writeUser();
                    startActivity(new Intent(OrganizerEventContentActivity.this, OrganizerEventActivity.class));
                }else{
                    Toast.makeText(this, "Some error occurred!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void loadEventInfo() {
        roomCapacity = getRoomManager().getRoomCapFromEvent(getEventID());
        currentSize = getEventManager().getEventNumAttended(getEventID());
        eventSpeakerIDs = getEventManager().getSpeakersFromEvent(getEventID());
        eventAttendeeIDs = getEventManager().getAttendeesFromEvent(getEventID());
    }

    private void loadButtons(){
        Button announceSpeaker = findViewById(R.id.btn_event_announce_speaker);
        Button announceAttendee = findViewById(R.id.btn_event_announce_attendee);
        Button changeCapacity = findViewById(R.id.btn_change_cap);
        Button addSpeaker = findViewById(R.id.btn_add_speaker);
        Button eventCancel = findViewById(R.id.btn_cancel_event);

        announceSpeaker.setOnClickListener(this);
        announceAttendee.setOnClickListener(this);
        changeCapacity.setOnClickListener(this);
        addSpeaker.setOnClickListener(this);
        eventCancel.setOnClickListener(this);
    }

    private void loadSwipeRefreshLayout(){
        swipeRefreshLayout = findViewById(R.id.event_content_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshEventContent();
            }
        });
    }

    private void refreshEventContent() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadAnnounceData(){
        intent = new Intent(OrganizerEventContentActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "eventContent");
        intent.putExtra("eventTitle", getEventTitle());
    }

    private void loadSpeakerData(){
        intent = new Intent(OrganizerEventContentActivity.this, SelectSpeakerActivity.class);
        intent.putExtra("eventTime", getEventTime());
        intent.putExtra("eventDuration", getEventDuration());
    }

    private boolean validInteger(String num){
        return getEventManager().checkValidInteger(num);
    }

    /**
     * cancelEvent
     */
    private boolean cancelEvent(){
        return getEventManager().cancelEvent(getEventID());
    }

    private void changeCapacity(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText newCap = new EditText(OrganizerEventContentActivity.this);

        loadCapDialog(builder, newCap);
        setOnPositiveButtonListener(builder, newCap);
    }

    private void loadCapDialog(AlertDialog.Builder builder, EditText newCap){
        //new dialog to prompt user
        builder.setTitle("Change Event Capacity");
        builder.setMessage("Please enter the new capacity for the event");
        builder.setNegativeButton("Cancel", null);
        builder.setCancelable(true);

        //create edit text for user input
        ViewGroup.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        newCap.setLayoutParams(lp);
        builder.setView(newCap);
        newCap.setFocusable(true);
    }

    private void setOnPositiveButtonListener(AlertDialog.Builder builder, final EditText newCap){
        //set button for confirming input
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String capEntered = newCap.getText().toString();
                if(!validInteger(capEntered)){
                    Toast.makeText(OrganizerEventContentActivity.this,
                            "Invalid new capacity!",Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(capEntered) > roomCapacity){
                    Toast.makeText(OrganizerEventContentActivity.this,
                            "Capacity exceed current room capacity: " + roomCapacity,
                            Toast.LENGTH_LONG).show();
                }else if(Integer.parseInt(capEntered) < currentSize){
                    Toast.makeText(OrganizerEventContentActivity.this,
                            "Capacity is less than current number of attendees: " +
                                    currentSize + "!", Toast.LENGTH_LONG).show();
                }else{
                    int newCapacity = Integer.parseInt(capEntered);
                    getEventManager().setEventCapacity(getEventID(), newCapacity);
                    writeEvent();
                    Toast.makeText(OrganizerEventContentActivity.this,
                            "You have SUCCESSFULLY changed event capacity",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.show();
    }

    private boolean addSpeaker(){
        return getEventManager().addSpeakerToEvent(speakerIds, getEventID(),
                getEventTime(), getEventDuration());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    ArrayList<String> speakerNames = data.getStringArrayListExtra("speakerNames");
                    if(speakerNames == null){
                        speakerIds = new ArrayList<>();
                    }else{
                        //Todo: fix multi speakerId saving
                        speakerIds = getUserManager().getUserIdsFromName(speakerNames);
                    }

                    if(addSpeaker()){
                        if(speakerIds.isEmpty()){
                            Toast.makeText(this, "No additional speaker added!",
                                    Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this, "You have SUCCESSFULLY added speakers!",
                                    Toast.LENGTH_LONG).show();
                            super.writeEvent();
                        }
                    }else{
                        Toast.makeText(this, "Speakers chosen already IN EVENT " +
                                "or have TIME CONFLICT", Toast.LENGTH_LONG).show();
                    }
                }
        }
    }

}