package com.example.a207_demo.use_cases;

import com.example.a207_demo.entities.*;

public class UserFactory {
    private final AttendeeManager attendeeManager = new AttendeeManager();
    private final VIPUserManager vipUserManager = new VIPUserManager();
    private final OrganizerManager organizerManager = new OrganizerManager();
    private final SpeakerManager speakerManager = new SpeakerManager();


    /**
     * Create a new user account and store it in managers.
     * @param userName
     * @param email
     * @param password
     * @param type
     */
    public void createNewUserAccount(String userName, String email, String password, String type){
        switch (type) {
            case "ATTENDEE":
                attendeeManager.createAttendee(userName, email, password);
                break;
            case "VIPUser":
                vipUserManager.createVIPUser(userName, email, password);
                break;
            case "ORGANIZER":
                organizerManager.createOrganizer(userName, email, password);
                break;
            case "SPEAKER":
                speakerManager.createSpeaker(userName, email, password);
                break;
        }
    }
}
