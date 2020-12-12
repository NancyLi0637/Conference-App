package com.example.a207_demo.eventSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

public class AttendeeMyEventContentActivity extends EventContentActivity implements View.OnClickListener {

    /**
     * Required function to initiate an Activity class.
     *
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myevent_content_attendee);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * fillContent
     */
    protected void init() {
        super.init();
        Button eventCancelEnrol = findViewById(R.id.btn_cancel_enrolment);
        eventCancelEnrol.setOnClickListener(this);
    }

    /**
     * onClick
     *
     * @param view View
     */
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to cancel your enrollment?");
        builder.setNegativeButton("No", null);
        builder.setCancelable(true);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (cancelled()) {
                    Toast.makeText(AttendeeMyEventContentActivity.this,
                            "You have SUCCESSFULLY cancelled your enrolment!!", Toast.LENGTH_LONG).show();
                    writeEvent();
                    startActivity(new Intent(AttendeeMyEventContentActivity.this, AttendeeMyEventActivity.class));
                } else {
                    Toast.makeText(AttendeeMyEventContentActivity.this,
                            "Some errors have occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.show();
//        if(cancelled()){
//            Toast.makeText(this, "You have SUCCESSFULLY cancelled your enrolment!!", Toast.LENGTH_LONG).show();
//            super.writeEvent();
//            startActivity(new Intent(AttendeeMyEventContentActivity.this, AttendeeMyEventActivity.class));
//        }else{
//            Toast.makeText(this, "Some errors have occurred!", Toast.LENGTH_LONG).show();
//        }
    }

    private boolean cancelled() {
        return getEventManager().removeAttendeeFromEvent(getMyID(), getEventID());
    }

}