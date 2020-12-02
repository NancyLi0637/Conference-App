package com.example.a207_demo.signupSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.a207_demo.use_cases.*;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.MainActivity;
import com.example.a207_demo.R;



/**
 * Activity class for user sign up.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        ActivityCollector.addActivity(this);

    }

    /**
     * Set up activity.
     */
    public void init(){
        Button signUp = findViewById(R.id.btn_signUp);
        Button login = findViewById(R.id.btn_login);

        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    /**
     * Button Listener for clicking events.
     * @param v Buttom clicked
     */
    @Override
    public void onClick(View v){
        Intent intent = new Intent();

        switch(v.getId()){
            case R.id.btn_signUp:
                if(!validEmail()){
                    //Todo: implement error message
                }else if(!validUsername()){
                    //Todo: implement error message
                }else{
                    intent = new Intent(SignUpActivity.this, MainActivity.class);
                    setUpData();
                    startActivity(intent);
                }
                break;
            case R.id.btn_login:
                intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean validEmail(){
        EditText email = findViewById(R.id.email_signUp);
        String userEM = email.getText().toString();

        UserManager userManager = new UserManager();

        return isValidEmail(userEM, userManager);
    }

    private boolean isValidEmail(String email, UserManager userManager) {
        if (!userManager.validNewEmail(email)) {
            return false;
        } else if (email.length() >= 6 && email.contains("@") && email.charAt(0) != '@' && email.contains(".") &&
                email.charAt(email.length() - 1) != '.' && email.length() - email.replace(".", "").length() == 1 &&
                email.length() - email.replace("@", "").length() == 1 && email.indexOf('@') < email.indexOf('.') &&
                email.indexOf('@') != email.indexOf('.') - 1 && userManager.validNewEmail(email)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validUsername(){
        EditText firstName = findViewById(R.id.firstname);
        EditText lastName = findViewById(R.id.lastname);
        String userFN = firstName.getText().toString();
        String userLN = lastName.getText().toString();

        UserManager userManager = new UserManager();

        return isValidUserName(userFN + userLN, userManager);
    }

    private boolean isValidUserName(String user, UserManager userManager) {
        return user.length() >= 2 && userManager.validNewName(user);
    }


    private void setUpData(){
        EditText email = findViewById(R.id.email_signUp);
        EditText firstName = findViewById(R.id.firstname);
        EditText lastName = findViewById(R.id.lastname);
        EditText password = findViewById(R.id.password_signUp);
        Spinner userType = findViewById(R.id.userType);

        String userFN = firstName.getText().toString();
        String userLN = lastName.getText().toString();
        String userEM = email.getText().toString();
        String userPW = password.getText().toString();
        // Use getSelectedItem() to get the selected item in a spinner:
        String userTypeStr = String.valueOf(userType.getSelectedItem());

        //Todo: initiate new User object through manager
        //Todo: save data into database
        createNewAccount(userTypeStr, userFN + userLN, userEM, userPW);


//        AttendeeManager attendeeManager = new AttendeeManager();
//        OrganizerManager organizerManager = new OrganizerManager();
//        SpeakerManager speakerManager = new SpeakerManager();
//        UserManager userManager = new UserManager();
//        if (userTypeStr.equals("Organizer")) {
//            return CreateNewAccount(attendeeManager, organizerManager, speakerManager, userManager,"ORGANIZER");
//        } else if (CurrentAction.equals("2")) {
//            return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, userManager,"ORGANIZER");
//        } else if(CurrentAction.equals("cancel")){
//            return false;
//        }
    }

    private void createNewAccount(String userType, String username, String userEmail, String userPassword){
        AttendeeManager attendeeManager = new AttendeeManager();
        OrganizerManager organizerManager = new OrganizerManager();
        //TODO: add VipUserManager
        //VipUserManager vipUserManager = new VipUserManager();

        if(userType.equals("Attendee")){
            attendeeManager.createAttendee(username, userEmail, userPassword);
        }else if(userType.equals("Organizer")){
            organizerManager.createOrganizer(username, userEmail, userPassword);
        }else if(userType.equals("VIP User")){
            //vipUserManger.createVipUser(username, userEmail, userPassword);
        }
    }


//    public boolean CreateNewAccount(AttendeeManager attendeeManager, OrganizerManager organizerManager,
//                                    SpeakerManager speakerManager, UserManager userManager, String type) {
//        String email = input.getInputString("Please enter the email for new account: (ex. 12345@abc.com), or " +
//                "enter 'cancel' at any point to exit account creation\n");
//        if (email.contains(" ")) {
//            return false;
//        }
//        ;
//        while (true) {
//            if (email.equals("cancel")) {
//                return false;
//            } else if (isValidEmail(email, userManager)) {
//                break;
//            } else {
//                email = input.getInputString("Please enter another one, or enter 'cancel' at any point to exit" +
//                        " account creation\n");
//            }
//        }
//
//        String user = input.getInputString("Please enter a user name for new account: (must have length of at " +
//                "least 2 and does NOT contain space), or enter 'cancel' at any point to exit account creation\n");
//        if (user.contains(" ")){
//            return false;
//        };
//        while (true) {
//            if (user.equals("cancel")) {
//                return false;
//            } else if (isValidUserName(user, userManager)) {
//                break;
//            } else {
//                user = input.getInputString("Invalid User name or user name already used, please enter another " +
//                        "one, or enter 'cancel' at any point to exit account creation\n");
//            }
//        }
//        String password = input.getInputString("Please enter a password for " + user + ":\n");
//        if (password.contains(" ")){
//            return false;
//        };
//        while (true) {
//            if (password.equals("cancel")) {
//                return false;
//            } else if (password.length() >= 8) {
//                break;
//            } else {
//                password = input.getInputString("Password must be at least length 8, please try again, or enter " +
//                        "'cancel' at any point to exit account creation\n");
//            }
//        }
//        if (type.equals("SPEAKER")) {
//            speakerManager.createSpeaker(email, user, password);
//        } else if (type.equals("ORGANIZER")) {
//            organizerManager.createOrganizer(email, user, password);
//        } else {
//            attendeeManager.createAttendee(email, user, password);
//        }
//        return true;
//    }

}