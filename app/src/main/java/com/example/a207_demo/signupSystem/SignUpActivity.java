package com.example.a207_demo.signupSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.MainActivity;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.CleanArchActivity;


/**
 * Activity class for user sign up.
 */
public class SignUpActivity extends CleanArchActivity implements View.OnClickListener {

    private final CreateAccount accountCreator = new CreateAccount();

    private String userName;
    private String userEmail;
    private String userPassword;
    private String userType;

    /**
     * Required function to initiate an Activity class.
     *
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
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_signUp:
                if (!validEmail()) {
                    Toast.makeText(SignUpActivity.this, "Your email is invalid, please try again",
                            Toast.LENGTH_LONG).show();
                } else if (!validUsername()) {
                    Toast.makeText(SignUpActivity.this, "Your name is invalid, please try again",
                            Toast.LENGTH_LONG).show();
                } else {
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
     *
     * @return boolean
     */
    private boolean validEmail() {
        EditText email = findViewById(R.id.email_signUp);
        userEmail = email.getText().toString();

        return accountCreator.isValidEmail(userEmail);
    }

    /**
     * Check if the userName entered is valid
     *
     * @return boolean
     */
    private boolean validUsername() {
        EditText firstName = findViewById(R.id.firstname);
        EditText lastName = findViewById(R.id.lastname);
        userName = firstName.getText().toString() + " " + lastName.getText().toString();

        return accountCreator.isValidUserName(userName);
    }

    /**
     * set up data and save data into database
     */
    private void setUpData() {
        EditText password = findViewById(R.id.password_signUp);
        Spinner type = findViewById(R.id.userType);

        userPassword = password.getText().toString();
        userType = String.valueOf(type.getSelectedItem());

        accountCreator.createNewAccount(userType, userName, userEmail, userPassword);
        super.writeUser();
    }

}