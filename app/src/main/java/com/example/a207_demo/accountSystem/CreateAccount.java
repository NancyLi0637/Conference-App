package com.example.a207_demo.accountSystem;

import com.example.a207_demo.use_cases.UserFactory;
import com.example.a207_demo.use_cases.UserManager;
import com.example.a207_demo.utility.CleanArchActivity;

/**
 * Create a new account
 */
public class CreateAccount extends CleanArchActivity {

    private final UserManager userManager = getUserManager();
    private final UserFactory userFactory = new UserFactory(getAttendeeManager(),
            getOrganizerManager(), getSpeakerManager());


    /**
     * The method of creating a new account.
     *
     * @param type     Type of user
     * @param username Username of user
     * @param userEM   Email of user
     * @param userPW   Password of user
     * @return True if created successfully
     */
    public void createNewAccount(String type, String username, String userEM, String userPW) {
        userFactory.createNewUserAccount(type, username, userEM, userPW);
    }

    /**
     * Check if the email entered is valid with userManager
     *
     * @param email email of the user
     * @return boolean
     */
    public boolean isValidEmail(String email) {
        return userManager.validEmail(email);
    }

    /**
     * Check if the userName entered is valid with userManager
     *
     * @param name String username
     * @return boolean
     */
    public boolean isValidUserName(String name) {
        return userManager.validName(name);
    }

}
