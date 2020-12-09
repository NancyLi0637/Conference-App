package com.example.a207_demo.speakerSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * SelectSpeakerActivity
 */
public class SelectSpeakerActivity extends CleanArchActivity implements View.OnClickListener{

    private List<String> speakerList;
    private SelectSpeakerAdapter selectSpeakerAdapter;

    private Intent intent;


    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker_select);

        init();

        ActivityCollector.addActivity(this);
    }

    private void init(){
        intent = new Intent();

        Button back = findViewById(R.id.speaker_selected_back);
        Button finish = findViewById(R.id.speaker_selected_finish);

        back.setOnClickListener(this);
        finish.setOnClickListener(this);

        createSpeakerMenu();
    }

    /**
     * onClick
     * @param v View
     */
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.speaker_selected_finish:
                setUpData();
                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.speaker_selected_back:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    private void setUpData(){
        ArrayList<String> speakerNames = selectSpeakerAdapter.getSpeakerNames();
        intent.putStringArrayListExtra("speakerNames", speakerNames);
    }

    private void createSpeakerMenu(){
        initSpeakers();

        RecyclerView speakerRecycleView = findViewById(R.id.speaker_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        speakerRecycleView.setLayoutManager(linearLayoutManager);
        selectSpeakerAdapter = new SelectSpeakerAdapter(this, speakerList);
        speakerRecycleView.setAdapter(selectSpeakerAdapter);
    }

    private void initSpeakers(){
        //Todo: clean up after implementing speaker System
        super.reset();
        super.readUser();

        Intent lastIntent = getIntent();
        String time = lastIntent.getStringExtra("eventTime");
        String duration = lastIntent.getStringExtra("eventDuration");
        speakerList = getSpeakerManager().getAllAvailableSpeaker(time, duration, getEventManager());
    }
}