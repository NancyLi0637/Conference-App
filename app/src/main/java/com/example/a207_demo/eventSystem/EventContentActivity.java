package com.example.a207_demo.eventSystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class EventContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_content);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        createActionBar();
        setUpData();
    }

    public void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void setUpData(){
        Intent intent = getIntent();
        String eventTitle = intent.getStringExtra("event_title");
        String eventRoom = intent.getStringExtra("event_room");
        String eventTime = intent.getStringExtra("event_time");
        int eventImageId = intent.getIntExtra("event_image_id", 0);

        String eventContent = "Room: " + eventRoom + "\n" +
                "Time: " + eventTime;
        fillContent(eventTitle, eventContent, eventImageId);
    }

    public void fillContent(String eventTitle, String eventContent, int eventImageId){
        ImageView eventImageView = findViewById(R.id.event_image_view);
        TextView eventInfo = findViewById(R.id.event_info);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(eventTitle);
        Glide.with(this).load(eventImageId).into(eventImageView);

        eventInfo.setText(eventContent);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}