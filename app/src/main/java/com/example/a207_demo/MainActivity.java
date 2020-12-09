package com.example.a207_demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a207_demo.eventSystem.AttendeeEventActivity;
import com.example.a207_demo.eventSystem.OrganizerEventActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.accountSystem.SignUpActivity;
import com.example.a207_demo.utility.CleanArchActivity;

/**
 * The top level class for running the app.
 */
public class MainActivity extends CleanArchActivity implements View.OnClickListener {

    private Intent intent;

    private static String ID;
    private static String TYPE;
    private static String EMAIL;
    private static String USERNAME;

    /**
     * Required function to initiate an Activity class.
     *
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
    public void init() {
        Button signUp = findViewById(R.id.btn_signUp);
        Button login = findViewById(R.id.btn_login);

        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    /**
     * Button Listener for clicking events.
     *
     * @param v Button clicked
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signUp:
                super.reset();
                //intent = new Intent(MainActivity.this, SignUpActivity.class);

                //intent = new Intent(MainActivity.this, OrganizerEventActivity.class);
                //intent.putExtra("ID", ID);
                intent = new Intent(MainActivity.this, SpeakerMyEventActivity.class);
                //intent = new Intent(MainActivity.this, AttendeeMyEventActivity.class);
                intent.putExtra("class", "MAIN");
                startActivity(intent);
                break;
            case R.id.btn_login:
                super.reset();
                super.readUser();
                if (info_matched()) {
                    if (TYPE.equals("ATTENDEE") || TYPE.equals("VIPUser")) {
                        intent = new Intent(MainActivity.this, AttendeeEventActivity.class);
                    } else if (TYPE.equals("ORGANIZER")) {
                        intent = new Intent(MainActivity.this, OrganizerEventActivity.class);
                    } else {
                        intent = new Intent(MainActivity.this, SpeakerMyEventActivity.class);
                    }
                    loadInfo();
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Your username and password do not match. Please try again.",
                            Toast.LENGTH_LONG).show();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    /**
     * Check if the information entered by the user match the data stored in the database
     *
     * @return boolean
     */
    private boolean info_matched() {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String userEM = email.getText().toString();
        String userPW = password.getText().toString();

        if (getUserManager().validLogIn(userEM, userPW).equals("NULL")) {
            return false;
        }
        ID = getUserManager().validLogIn(userEM, userPW);
        TYPE = getUserManager().getUserType(userEM, userPW);
//        EMAIL = userEM;
//        USERNAME = getUserManager().getUserNameFromID(ID);
        return true;
    }

    private void loadInfo(){
        //TODO: intent for setting
//        ArrayList<String> info = new ArrayList<>();
//        info.add(ID);
//        info.add(TYPE);
//        info.add(EMAIL);
//        info.add(USERNAME);
        intent.putExtra("ID", ID);
        intent.putExtra("TYPE", TYPE);
    }
}
