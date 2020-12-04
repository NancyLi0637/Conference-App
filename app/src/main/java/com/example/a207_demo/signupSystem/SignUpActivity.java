package com.example.a207_demo.signupSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.use_cases.*;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.MainActivity;
import com.example.a207_demo.R;



/**
 * Activity class for user sign up.
 */
public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private final CreateAccount accountCreater = new CreateAccount();
    private final FileReadWriter fileReadWriter = new FileReadWriter();

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
        Intent intent;

        switch(v.getId()){
            case R.id.btn_signUp:
                if(!validEmail()){
                    Toast.makeText(SignUpActivity.this, "Your email is invalid, please try again",
                            Toast.LENGTH_LONG).show();
                }else if(!validUsername()){
                    Toast.makeText(SignUpActivity.this, "Your name is invalid, please try again",
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SignUpActivity.this, "You have signed up SUCCESSFULLY!",
                            Toast.LENGTH_LONG).show();
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

    /**
     * Check if the email entered is valid
     * @return boolean
     */
    private boolean validEmail(){
        EditText email = findViewById(R.id.email_signUp);
        String userEM = email.getText().toString();

        return accountCreater.isValidEmail(userEM);
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

        return accountCreater.isValidUserName(userFN + userLN);
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
        String userTypeStr = String.valueOf(userType.getSelectedItem());

        //Todo: create account using CreateANewAccount (factory)
        //Todo: save data into database
        accountCreater.createNewAccount(userTypeStr, userFN + userLN, userEM, userPW);
        fileReadWriter.connectWriters(this);
    }

}