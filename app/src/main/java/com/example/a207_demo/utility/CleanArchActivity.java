package com.example.a207_demo.utility;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.use_cases.OrganizerManager;
import com.example.a207_demo.roomSystem.RoomManager;
import com.example.a207_demo.use_cases.SpeakerManager;
import com.example.a207_demo.use_cases.UserManager;

/**
 * The central Activity: Superclass of child activities
 */
public class CleanArchActivity extends AppCompatActivity{

    private final EventManager eventManager = new EventManager();
    private final UserManager userManager = new UserManager();
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private final OrganizerManager organizerManager = new OrganizerManager();
    private final SpeakerManager speakerManager = new SpeakerManager();
    private final RoomManager roomManager = new RoomManager();
//    private final FileReadWriter fileReadWriter = new FileReadWriter(this, eventManager,
//            userManager, attendeeManager, organizerManager, speakerManager, roomManager);
    private FileReadWriter fileReadWriter = new FileReadWriter(this);

    /**
     * Get EventManager of the whole system
     * @return EventManager
     */
    public EventManager getEventManager() {
        return this.eventManager;
    }

    /**
     * Get UserManager of the whole system
     * @return UserManager
     */
    public UserManager getUserManager() {return this.userManager; }

    /**
     * Get AttendeeManager of the whole system
     * @return AttendeeManager
     */
    public AttendeeManager getAttendeeManager() {return this.attendeeManager; }

    /**
     * Get OrganizerManager of the whole system
     * @return OrganizerManager
     */
    public OrganizerManager getOrganizerManager() {return this.organizerManager; }

    /**
     * Get SpeakerManager of the whole system
     * @return SpeakerManager
     */
    public SpeakerManager getSpeakerManager() {return this.speakerManager; }

    /**
     * Get RoomManager of the whole system
     * @return RoomManager
     */
    public RoomManager getRoomManager() {return this.roomManager;}

    /**
     * Get FileReadWriter of the whole system
     * @return FileReadWriter
     */
    public FileReadWriter getFileReadWriter() {
        return this.fileReadWriter;
    }

    public void reset(){
        fileReadWriter.reset(eventManager, userManager,
                attendeeManager, organizerManager,
                speakerManager, roomManager);
    }

    public void read(){
        fileReadWriter.UserReader(attendeeManager, speakerManager, organizerManager);
        fileReadWriter.EventReader(eventManager);
        fileReadWriter.RoomReader(roomManager);
    }

    public void write(){
        fileReadWriter.UserWriter(userManager);
        fileReadWriter.EventWriter(eventManager);
        fileReadWriter.RoomReader(roomManager);
    }

}