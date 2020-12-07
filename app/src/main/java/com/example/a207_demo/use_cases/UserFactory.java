package com.example.a207_demo.use_cases;

import com.example.a207_demo.utility.CleanArchActivity;

import java.io.Serializable;

/**
 * A Factory class to create a new user account according to the type of the user. As a result, the
 * newly created user will be stored in managers.
 */
public class UserFactory implements Serializable {
    private final AttendeeManager attendeeManager;
    private final VIPUserManager vipUserManager;
    private final OrganizerManager organizerManager;
    private final SpeakerManager speakerManager;

    /**
     * Use dependency injection: pass in the constructor all the necessary managers
     *
     * @param attendeeManager  AttendeeManager
     * @param organizerManager OrganizerManager
     * @param speakerManager   SpeakerManager
     */
    public UserFactory(AttendeeManager attendeeManager, VIPUserManager vipUserManager,
                       OrganizerManager organizerManager, SpeakerManager speakerManager) {
        this.attendeeManager = attendeeManager;
        this.vipUserManager = vipUserManager;
        this.organizerManager = organizerManager;
        this.speakerManager = speakerManager;
    }


    /**
     * Create a new user account and store it in managers.
     *
     * @param userName userName of this new user
     * @param email    email of this new user
     * @param password password of this new user
     * @param type     type of this new user
     */
    public void createNewUserAccount(String type, String userName, String email, String password) {
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
