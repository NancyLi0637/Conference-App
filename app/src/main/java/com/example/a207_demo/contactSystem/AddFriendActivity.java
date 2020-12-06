package com.example.a207_demo.contactSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.a207_demo.MainActivity;
import com.example.a207_demo.R;
import com.example.a207_demo.eventSystem.AttendeeEventActivity;
import com.example.a207_demo.eventSystem.OrganizerEventActivity;
import com.example.a207_demo.eventSystem.SpeakerMyEventActivity;
import com.example.a207_demo.use_cases.ConversationManager;
import com.example.a207_demo.use_cases.UserManager;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;
import java.util.List;

public class AddFriendActivity extends SetUpActivity implements View.OnClickListener{

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init() {
        Button search = findViewById(R.id.btn_search);

        search.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent;

    }
}
