package com.example.a207_demo.eventSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

public class AttendeeMyEventContentActivity extends EventContentActivity implements View.OnClickListener{

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_content_attendee);

        init();

        ActivityCollector.addActivity(this);
    }

    protected void fillContent(String eventTitle, String eventContent, int eventImageId){
        super.fillContent(eventTitle, eventContent, eventImageId);
        Button eventCancel = findViewById(R.id.btn_cancel_enrolment);
        eventCancel.setOnClickListener(this);
    }

}