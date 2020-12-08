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
import com.example.a207_demo.utility.ActivityCollector;

/**
 * OrganizerEventContentActivity
 */
public class OrganizerEventContentActivity extends EventContentActivity{
    private Intent intent;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String eventID;
    private String eventTitle;
    private int roomCapacity;
    private int currentSize;
    private int newCapacity;


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
        eventID = getEventID();
        eventTitle = getEventTitle();
        roomCapacity = getRoomManager().getRoomCapFromEvent(eventID);
        currentSize = getEventManager().getEventNumAttended(eventID);
        newCapacity = -1;
        loadSwipeRefreshLayout();

        Button announceSpeaker = findViewById(R.id.btn_event_announce_speaker);
        Button announceAttendee = findViewById(R.id.btn_event_announce_attendee);
        Button changeCapacity = findViewById(R.id.btn_change_cap);
        Button eventCancel = findViewById(R.id.btn_cancel_event);

        announceSpeaker.setOnClickListener(this);
        announceAttendee.setOnClickListener(this);
        changeCapacity.setOnClickListener(this);
        eventCancel.setOnClickListener(this);
    }

    /**
     * onClick
     * @param view View
     */
    public void onClick(View view){
        intent = new Intent(OrganizerEventContentActivity.this, SendAnnouncementActivity.class);
        intent.putExtra("class", "eventContent");
        intent.putExtra("eventTitle", eventTitle);
        switch (view.getId()){
            case R.id.btn_event_announce_speaker:
                intent.putExtra("userIDs", getEventManager().getSpeakersFromEvent(eventID));
                startActivity(intent);
                break;
            case R.id.btn_event_announce_attendee:
                intent.putExtra("userIDs", getEventManager().getAttendeesFromEvent(eventID));
                startActivity(intent);
                break;
            case R.id.btn_change_cap:
                changeCapacity();
                break;
            case R.id.btn_cancel_event:
                if(cancelEvent()){
                    Toast.makeText(this, "Event is cancelled!", Toast.LENGTH_LONG).show();
                    super.writeEvent();
                    startActivity(new Intent(OrganizerEventContentActivity.this, OrganizerEventActivity.class));
                }else{
                    Toast.makeText(this, "Some error occurred!", Toast.LENGTH_LONG).show();
                }
                break;
        }
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
//                createRoomMenu();
//                roomAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * cancelEvent
     */
    private boolean cancelEvent(){
        return getEventManager().cancelEvent(eventID);
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
                    newCapacity = Integer.parseInt(capEntered);
                    getEventManager().setEventCapacity(eventID, newCapacity);
                    writeEvent();
                    Toast.makeText(OrganizerEventContentActivity.this,
                            "You have SUCCESSFULLY changed event capacity",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        builder.show();
    }

    private boolean validInteger(String num){
        return getEventManager().checkValidInteger(num);
    }

    private void write(){
        super.writeEvent();
    }

}