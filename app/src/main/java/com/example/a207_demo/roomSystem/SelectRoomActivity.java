package com.example.a207_demo.roomSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.a207_demo.R;
import com.example.a207_demo.gateway.FileReadWriter;
import com.example.a207_demo.speakerSystem.SelectSpeakerActivity;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomActivity extends CleanArchActivity implements View.OnClickListener{

    private List<Room> roomList = new ArrayList<>();
    private FileReadWriter fileReadWriter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_room);

        init();

        ActivityCollector.addActivity(this);
    }

    private void init(){
        fileReadWriter = getFileReadWriter();
        intent = new Intent();

        Button back = findViewById(R.id.room_selected_back);
        Button next = findViewById(R.id.room_selected_next);

        back.setOnClickListener(this);
        next.setOnClickListener(this);

        createRoomMenu();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.room_selected_back:
                setResult(RESULT_CANCELED, intent);
                finish();
            case R.id.room_selected_next:
                intent = new Intent(SelectRoomActivity.this, SelectSpeakerActivity.class);
                setUpData();
                startActivity(intent);
        }
    }

    private void setUpData(){
        Intent lastIntent = getIntent();
        intent.putExtra("eventType", lastIntent.getStringExtra("eventType"));
        intent.putExtra("eventTitle", lastIntent.getStringExtra("eventTitle"));
        intent.putExtra("eventTime", lastIntent.getStringExtra("eventTime"));
        intent.putExtra("eventDuration", lastIntent.getStringExtra("eventDuration"));
        intent.putExtra("eventRestriction",lastIntent.getStringExtra("eventRestriction"));

        RadioButton room = findViewById(R.id.room_selected);
        String roomNum = room.getText().toString();
        intent.putExtra("roomNum", roomNum);
    }

    private void createRoomMenu(){
        initRooms();

        RecyclerView roomRecycleView = findViewById(R.id.room_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        roomRecycleView.setLayoutManager(linearLayoutManager);
        SelectRoomAdapter selectRoomAdapter = new SelectRoomAdapter(this, roomList);
        roomRecycleView.setAdapter(selectRoomAdapter);
    }

    private void initRooms(){
        //Todo: clean up after implementing Room System
        fileReadWriter.reset();
        fileReadWriter.RoomReader();

        roomList = getRoomManager().getAllRoom();
//        for(int i = 0; i < 3; i++){
//            Room room1 = new Room("BF201", 5);
//            roomList.add(room1);
//            Room room2 = new Room("BF201", 5);
//            roomList.add(room2);
//            Room room3 = new Room("BF201", 5);
//            roomList.add(room3);
//            Room room4 = new Room("BF201", 5);
//            roomList.add(room4);
//            Room room5 = new Room("BF201", 5);
//            roomList.add(room5);
//        }
    }
}