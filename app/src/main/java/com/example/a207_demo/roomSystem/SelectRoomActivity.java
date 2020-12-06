package com.example.a207_demo.roomSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.a207_demo.R;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.speakerSystem.SelectSpeakerActivity;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomActivity extends CleanArchActivity implements View.OnClickListener {

    private List<String> roomList = new ArrayList<>();
    //private FileReadWriter fileReadWriter;
    private Intent intent;
    private boolean selected;
    private String roomNum;

    /**
     * onCreate
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        init();

        ActivityCollector.addActivity(this);
    }

    private void init() {
        //fileReadWriter = getFileReadWriter();
        intent = new Intent();

        Button back = findViewById(R.id.room_selected_back);
        Button finish = findViewById(R.id.room_selected_finish);

        back.setOnClickListener(this);
        finish.setOnClickListener(this);

        createRoomMenu();
    }

    /**
     * onClick
     * @param v View
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.room_selected_finish:
                setUpData();
                if (!selected) {
                    Toast.makeText(this, "You need to select a room!", Toast.LENGTH_LONG).show();
                } else {
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.room_selected_back:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }


    private void setUpData() {
        RadioButton select = findViewById(R.id.room_selected);
        TextView room = findViewById(R.id.room_num);
        selected = select.isChecked();
        roomNum = room.getText().toString();
        intent.putExtra("roomNum", roomNum);
    }

    private void createRoomMenu() {
        initRooms();

        RecyclerView roomRecycleView = findViewById(R.id.room_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        roomRecycleView.setLayoutManager(linearLayoutManager);
        SelectRoomAdapter selectRoomAdapter = new SelectRoomAdapter(this, roomList);
        roomRecycleView.setAdapter(selectRoomAdapter);
    }

    private void initRooms() {
        //Todo: clean up after implementing Room System
        getFileReadWriter().reset();
        getFileReadWriter().RoomReader();

        Intent lastIntent = getIntent();
        String time = lastIntent.getStringExtra("eventTime");
        String duration = lastIntent.getStringExtra("eventDuration");
        roomList = getRoomManager().getAvailableRoom(time, duration, getEventManager());
    }
}