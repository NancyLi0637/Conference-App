package com.example.a207_demo.signupSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.a207_demo.ActivityCollector;
import com.example.a207_demo.MainActivity;
import com.example.a207_demo.R;
import com.example.a207_demo.eventSystem.EventActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        attainData();

        Button signUp = findViewById(R.id.btn_signUp);
        Button login = findViewById(R.id.btn_login);

        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    public void attainData(){
        // get Intent() returns the intent that started the activity, and you can use this to
        // retrieve any extra info that was sent along with it.
        Intent intent = getIntent();
        EditText emailView = findViewById(R.id.email_signUp);
        EditText passwordView = findViewById(R.id.password_signUp);


//        if(intent.getStringExtra("email") != null){
//            email.setText(intent.getStringExtra("email"));
//        }
//        if(intent.getStringExtra("password") != null){
//            password.setText(intent.getStringExtra("password"));
//        }

        EditText firstNameView = findViewById(R.id.firstname);
        EditText lastNameView = findViewById(R.id.lastname);

        Spinner userType = (Spinner) findViewById(R.id.userType);
        // Use getSelectedItem() to get the selected item in a spinner:
        String userTypeStr = String.valueOf(userType.getSelectedItem());

        String email = emailView.getText().toString();
        String password = passwordView.getText().toString();

        String firstName = firstNameView.getText().toString();
        String lastName = lastNameView.getText().toString();

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_signUp:
                //Todo: connect to signUp system
                Intent intent = new Intent(SignUpActivity.this, EventActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                //Todo: connect to login system
                intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }


}