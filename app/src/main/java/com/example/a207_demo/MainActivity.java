package com.example.a207_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a207_demo.eventSystem.EventActivity;
import com.example.a207_demo.signupSystem.SignUpActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        Button signUp = findViewById(R.id.btn_signUp);
        Button login = findViewById(R.id.btn_login);

        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_signUp:
                //Todo: connect to signUp system
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                //setUpData(intent);
                startActivity(intent);
                break;
            case R.id.btn_login:
                //Todo: connect to login system
                intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void setUpData(Intent intent){
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.password);

        if(!email.getText().toString().equals("")){
            intent.putExtra("email",email.getText());
        }
        if(!password.getText().toString().equals("")){
            intent.putExtra("password",password.getText());
        }
    }
}
