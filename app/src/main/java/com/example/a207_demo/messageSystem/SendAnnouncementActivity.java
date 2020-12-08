package com.example.a207_demo.messageSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.CleanArchActivity;

public class SendAnnouncementActivity extends CleanArchActivity implements View.OnClickListener{
    private String announcement;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement_send);

        init();
    }

    private void init(){
        super.reset();
        super.readUser();
        intent = new Intent();

        Button back = findViewById(R.id.announcement_back_event);
        Button send = findViewById(R.id.send_announcement);

        back.setOnClickListener(this);
        send.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.send_announcement:
                loadData();
                if(announcement.equals("")){
                    Toast.makeText(this, "Announcement can't be empty!", Toast.LENGTH_LONG).show();
                }else {
                    intent.putExtra("announcement", announcement);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.announcement_back_event:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    private void loadData(){
        EditText announce = findViewById(R.id.announcement_to_send);
        announcement = announce.getText().toString();
        System.out.println("ANNOUNCEMENT" + announcement);
    }
}