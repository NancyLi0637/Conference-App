package com.example.a207_demo.utility;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.use_cases.OrganizerManager;
import com.example.a207_demo.use_cases.SpeakerManager;
import com.example.a207_demo.use_cases.UserManager;

public class CleanArchActivity extends AppCompatActivity {

    private final EventManager eventManager = new EventManager();
    private final UserManager userManager = new UserManager();
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private final OrganizerManager organizerManager = new OrganizerManager();
    private final SpeakerManager speakerManager = new SpeakerManager();
    private final FileReadWriter fileReadWriter = new FileReadWriter(this, eventManager,
            userManager, attendeeManager, organizerManager, speakerManager);

    public EventManager getEventManager(){
       return this.eventManager;
   }

    public FileReadWriter getFileReadWriter(){ return this.fileReadWriter;}

}