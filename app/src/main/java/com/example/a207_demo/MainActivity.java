package com.example.a207_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a207_demo.eventSystem.AttendeeEventActivity;
import com.example.a207_demo.eventSystem.OrganizerEventActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.signupSystem.SignUpActivity;
import com.example.a207_demo.use_cases.UserManager;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

/**
 * The top level class for running the app.
 */
public class MainActivity extends CleanArchActivity implements View.OnClickListener{

    private FileReadWriter fileReadWriter;
    private static String ID;
    private static String type;

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    /**
     * Set up the activity.
     */
    public void init(){
        fileReadWriter = getFileReadWriter();
        Button signUp = findViewById(R.id.btn_signUp);
        Button login = findViewById(R.id.btn_login);

        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    /**
     * Button Listener for clicking events.
     * @param v Button clicked
     */
    @Override
    public void onClick(View v){
        Intent intent;
        switch(v.getId()){
            case R.id.btn_signUp:
                //fileReadWriter.reset();
                //intent = new Intent(MainActivity.this, SignUpActivity.class);
                intent = new Intent(MainActivity.this, OrganizerEventActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                fileReadWriter.reset();
                fileReadWriter.UserReader();

                if (info_matched()) {
                    if (type.equals("ATTENDEE")) {
                        intent = new Intent(MainActivity.this, AttendeeEventActivity.class);
                    } else if (type.equals("ORGANIZER")) {
                        intent = new Intent(MainActivity.this, OrganizerEventActivity.class);
                    } else {
                        intent = new Intent(MainActivity.this, SpeakerMyEventActivity.class);
                    }
                    //Todo: too many lines written in Users.txt (FileReadWriter -> connectWrtier -> UserWriter method)
                    startActivity(intent);
                } else{
                    Toast.makeText(MainActivity.this, "Your username and password do not match. Please try again.",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private boolean info_matched(){
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String userEM = email.getText().toString();
        String userPW = password.getText().toString();

        UserManager userManager = new UserManager();
        if (!userManager.validLogIn(userEM, userPW).equals("NULL")){
            ID = userManager.validLogIn(userEM, userPW);
            type = userManager.getUserType(userEM, userPW);
            return true;
        }
        return false;
    }

}
