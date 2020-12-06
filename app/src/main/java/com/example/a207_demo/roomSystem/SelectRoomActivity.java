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

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

import java.util.ArrayList;
import java.util.List;

public class SelectRoomActivity extends CleanArchActivity implements View.OnClickListener{

    private List<String> roomList = new ArrayList<>();
    //private FileReadWriter fileReadWriter;
    private Intent intent;
    private boolean selected;
    private String roomID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_select);

        init();

        ActivityCollector.addActivity(this);
    }

    private void init(){
        //fileReadWriter = getFileReadWriter();
        intent = new Intent();

        Button back = findViewById(R.id.room_selected_back);
        Button finish = findViewById(R.id.room_selected_finish);

        back.setOnClickListener(this);
        finish.setOnClickListener(this);

        createRoomMenu();
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.room_selected_finish:
                setUpData();
                if(!selected){
                    Toast.makeText(this, "You need to select a room!", Toast.LENGTH_LONG).show();
                }else{
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

    private void setUpData(){
        RadioButton select = findViewById(R.id.room_selected);
        TextView room = findViewById(R.id.room_num);
        selected = select.isChecked();
        roomID = getRoomManager().changeNumTOID(room.getText().toString());
        intent.putExtra("roomID", roomID);
    }

    private void createRoomMenu(){
        initRooms();

        RecyclerView roomRecycleView = findViewById(R.id.select_room_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        roomRecycleView.setLayoutManager(linearLayoutManager);
        SelectRoomAdapter selectRoomAdapter = new SelectRoomAdapter(this, roomList);
        roomRecycleView.setAdapter(selectRoomAdapter);
    }

    private void initRooms(){
        //Todo: clean up after implementing Room System
        super.reset();
        super.readRoom();

        Intent lastIntent = getIntent();
        String time = lastIntent.getStringExtra("eventTime");
        String duration = lastIntent.getStringExtra("eventDuration");
        roomList = getRoomManager().getAvailableRoom(time, duration, getEventManager());
    }
}