package com.example.a207_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a207_demo.signupSystem.SignUpActivity;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * The top level class for running the app.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * Required function to initiate an Activity class.
     * @param savedInstanceState saved data for unexpected crush
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        ActivityCollector.addActivity(this);

    }

    /**
     * Set up the activity.
     */
    public void init(){
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
                intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
//                if(info_matched()) {
//                    //Todo: distinguish account
//                    String type = account_type();
//                    if(type.equals("ATTENDEE")){
//                        intent = new Intent(MainActivity.this, AttendeeEventActivity.class);
//                    }else if(account_type().equals("Organizer"){
//                        intent = new Intent(MainActivity.this, OrganizerEventActivity.class);
//                    }else{
//                        intent = new Intent(MainActivity.this, SpeakerMyEventActivity.class);
//                    }
//                    startActivity(intent);
//                }else{
//                    //Todo: implement error message
//                }
                //Todo: delete after above if-else implemented
                intent = new Intent(MainActivity.this, TempActivity.class);
                startActivity(intent);
                break;
        }
    }

    private boolean info_matched(){
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);
        String userEM = email.getText().toString();
        String userPW = password.getText().toString();

        //Todo: validate email and password through manager
        return true;
    }

    private String account_type(){
        //Todo: check type through manager
        return "";
    }

}
