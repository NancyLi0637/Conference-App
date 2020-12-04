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

public abstract class EventContentActivity extends CleanArchActivity implements View.OnClickListener{
    private EventManager eventManager;

    /**
     * Set the activity up
     */
    public void init(){
        eventManager = getEventManager();
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

    protected void setUpData(){
        Intent intent = getIntent();
        String eventTitle = intent.getStringExtra("event_title");
        String eventRoom = intent.getStringExtra("event_room");
        String eventTime = eventManager.generateFormattedStartTime(intent.getStringExtra("event_time"));
        String eventDuration = intent.getStringExtra("event_duration");
        int eventImageId = intent.getIntExtra("event_image_id", 0);

        String eventContent = "Room: " + eventRoom + "\n" +
                "Time: " + eventTime + "\n" + "Duration: " + eventDuration;
        fillContent(eventTitle, eventContent, eventImageId);
    }

    protected void fillContent(String eventTitle, String eventContent, int eventImageId){
        ImageView eventImageView = findViewById(R.id.event_image_view);
        TextView eventInfo = findViewById(R.id.event_info);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(eventTitle);
        Glide.with(this).load(eventImageId).into(eventImageView);
        eventInfo.setText(eventContent);
    }

    /**
     * Button listener for event sign up button
     * @param v Button Sign up
     */
    @Override
    public void onClick(View v){
        //Todo: add attendee to this event through manager
    }

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