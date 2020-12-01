package com.example.a207_demo.signupSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
                }else{
                    setUpData();
                    intent = new Intent(SignUpActivity.this, MainActivity.class);
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

//        //Todo: validate email through manager
//        if(!validNewEmail(userEM)){
//
//        }
        return true;
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

        //Todo: save data into database
//        if (userTypeStr.equals("Organizer")) {
//            return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, userManager,"ATTENDEE");
//        } else if (CurrentAction.equals("2")) {
//            return createAccount.CreateNewAccount(attendeeManager, organizerManager, speakerManager, userManager,"ORGANIZER");
//        } else if(CurrentAction.equals("cancel")){
//            return false;
//        }
    }

}