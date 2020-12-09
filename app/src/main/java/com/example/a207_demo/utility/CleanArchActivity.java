package com.example.a207_demo.utility;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a207_demo.eventSystem.EventManager;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.use_cases.AttendeeManager;
import com.example.a207_demo.use_cases.ConversationManager;
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
    private final ConversationManager conversationManager = new ConversationManager();
    private FileReadWriter fileReadWriter = new FileReadWriter(this);

    private static String ID;
    private static String TYPE;

    public void setInfo(String ID, String TYPE){
        this.ID = ID;
        this.TYPE = TYPE;
    }

    public String getID(){
        return this.ID;
    }

    public String getTYPE() { return this.TYPE;}

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
     * getConversationManager
     * @return ConversationManager
     */
    public ConversationManager getConversationManager(){return this.conversationManager;}

    /**
     * Get FileReadWriter of the whole system
     * @return FileReadWriter
     */
    public FileReadWriter getFileReadWriter() {
        return this.fileReadWriter;
    }

    public void reset(){
        fileReadWriter.reset(eventManager, userManager,
                attendeeManager, organizerManager, speakerManager, roomManager, conversationManager);
    }

    public void readUser(){
        fileReadWriter.UserReader(attendeeManager, organizerManager, speakerManager);
    }

    public void readEvent(){
        fileReadWriter.EventReader(eventManager, roomManager);
    }
    public void readRoom(){
        fileReadWriter.RoomReader(roomManager);
    }

    public void readConversation() {
        fileReadWriter.ConversationReader(conversationManager);
    }

    public void writeUser(){
        fileReadWriter.UserWriter(userManager);
    }

    public void writeEvent(){
        fileReadWriter.EventWriter(eventManager);
    }

    public void writeRoom(){
        fileReadWriter.RoomWriter(roomManager);
    }

    public void writeConversation() {
        fileReadWriter.ConversationWriter(conversationManager);
    }

}