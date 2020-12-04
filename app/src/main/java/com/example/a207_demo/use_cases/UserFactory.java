package com.example.a207_demo.use_cases;


/**
 * A Factory class to create a new user account and store it in managers.
 */
public class UserFactory {
    private final AttendeeManager attendeeManager = new AttendeeManager();
//    private final VIPUserManager vipUserManager = new VIPUserManager();
    private final OrganizerManager organizerManager = new OrganizerManager();
    private final SpeakerManager speakerManager = new SpeakerManager();


    /**
     * Create a new user account and store it in managers.
     * @param userName userName of this new user
     * @param email email of this new user
     * @param password password of this new user
     * @param type type of this new user
     */
    public void createNewUserAccount(String userName, String email, String password, String type){
        switch (type) {
            case "ATTENDEE":
                attendeeManager.createAttendee(userName, email, password);
                break;
//            case "VIPUser":
//                vipUserManager.createVIPUser(userName, email, password);
//                break;
            case "ORGANIZER":
                organizerManager.createOrganizer(userName, email, password);
                break;
            case "SPEAKER":
                speakerManager.createSpeaker(userName, email, password);
                break;
        }
    }
}
