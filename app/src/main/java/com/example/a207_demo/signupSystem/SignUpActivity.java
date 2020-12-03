package com.example.a207_demo.signupSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
                    Toast.makeText(SignUpActivity.this, "Your email is invalid, please try again",
                            Toast.LENGTH_LONG).show();
                }else if(!validUsername()){
                    //Todo: implement error message
                    Toast.makeText(SignUpActivity.this, "Your name is invalid, please try again",
                            Toast.LENGTH_LONG).show();
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

    /**
     * Check if the email entered is valid
     * @return boolean
     */
    private boolean validEmail(){
        EditText email = findViewById(R.id.email_signUp);
        String userEM = email.getText().toString();

        UserManager userManager = new UserManager();

        return isValidEmail(userEM, userManager);
    }

    /**
     * Check if the email entered is valid with userManager
     * @param email email of the user
     * @param userManager an instance of userManager
     * @return boolean
     */
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

    /**
     * Check if the userName entered is valid
     * @return boolean
     */
    private boolean validUsername(){
        EditText firstName = findViewById(R.id.firstname);
        EditText lastName = findViewById(R.id.lastname);
        String userFN = firstName.getText().toString();
        String userLN = lastName.getText().toString();

        UserManager userManager = new UserManager();

        return isValidUserName(userFN + userLN, userManager);
    }

    /**
     * Check if the userName entered is valid with userManager
     * @param user String username
     * @param userManager an instance of userManager
     * @return boolean
     */
    private boolean isValidUserName(String user, UserManager userManager) {
        return user.length() >= 2 && userManager.validNewName(user);
    }


    /**
     * set up data and save data into database
     */
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


    }

    /**
     * create a New Account
     * @param userType String, the type of the user
     * @param username username
     * @param userEmail userEmail
     * @param userPassword userPassword
     */
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

}