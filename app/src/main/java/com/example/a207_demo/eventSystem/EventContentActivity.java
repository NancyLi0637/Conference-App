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
import com.example.a207_demo.R;
import com.example.a207_demo.utility.CleanArchActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;

/**
 * EventContentActivity
 */
public abstract class EventContentActivity extends CleanArchActivity implements View.OnClickListener{

    /**
     * Set the activity up
     */
    protected void init(){
        super.reset();
        super.readEvent();
        super.readRoom();
        super.readUser();
        createActionBar();
        setUpData();
    }

    /**
     * Create action bar
     */
    public void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * setUpData
     */
    protected void setUpData(){
        ArrayList<String> event = getIntent().getStringArrayListExtra("event");
        String eventTitle = event.get(1);
        String eventRoom = getRoomManager().changeIdTONum(event.get(2));
        String eventTime = getEventManager().generateFormattedStartTime(event.get(3));
        String eventDuration = event.get(4);
        String eventType = event.get(5);
        String eventRestriction = event.get(6);
        String eventSpeakers = processSpeakers(event.get(7));
        String eventStatus = event.get(8);
        String eventContent = "Room: " + eventRoom + "\n" + "Time: " + eventTime + "\n" +
                "Duration: " + eventDuration + "\n" + "Type: " + eventType + "\n" +
                "Restriction: " + eventRestriction + "\n" + "Speakers: " + eventSpeakers + "\n" +
                "Space remaining: " + eventStatus;
        fillContent(eventTitle, eventContent);
    }

    private String processSpeakers(String speakerIDs){
        String result = "";
        if (speakerIDs.equals("[]") || speakerIDs.equals("null")) {
            return result;
        }
        //remove bracket
        String content = speakerIDs.substring(1, speakerIDs.length()-1);
        if (content.contains(", ")) {
            String[] idList = content.split(", ");
            for (String id : idList) {
                String speakerName = getUserManager().getUserNameFromID(id);
                result += speakerName + "/";
            }
        }else {
            result = getUserManager().getUserNameFromID(content);
        }
        return result;
    }

    /**
     * fillContent
     * @param eventTitle eventTitle
     * @param eventContent eventContent
     */
    protected void fillContent(String eventTitle, String eventContent){
        ImageView eventImageView = findViewById(R.id.event_image_view);
        TextView eventInfo = findViewById(R.id.event_info);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(eventTitle);
        //Todo: implement image later
        Glide.with(this).load(R.drawable.default_image).into(eventImageView);
        eventInfo.setText(eventContent);
    }

    /**
     * Button listener for event sign up button
     * @param v Button Sign up
     */
    @Override
    abstract public void onClick(View v);

    /**
     * Return to last menu
     * @param item Item clicked
     * @return true if quit this menu successfully
     */
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}