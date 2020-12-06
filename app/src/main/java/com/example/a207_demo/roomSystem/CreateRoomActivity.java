package com.example.a207_demo.roomSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.CleanArchActivity;

/**
 * CreateRoomActivity
 */
public class CreateRoomActivity extends CleanArchActivity implements View.OnClickListener {
    private Intent intent;
    private String roomNum;
    private String roomCap;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        init();

        ActivityCollector.addActivity(this);
    }


    private void init() {
        super.reset();
        super.readRoom();
        intent = new Intent();

        Button back = findViewById(R.id.room_added_back);
        Button finish = findViewById(R.id.room_added_finish);

        back.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    /**
     * onClick
     *
     * @param view View
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.room_added_finish:
                setUpData();
                if (dataMissing()) {
                    Toast.makeText(this, "Information missing!", Toast.LENGTH_LONG).show();
                } else if (!validRoomNum()) {
                    Toast.makeText(this, "ROOM NUMBER entered is invalid!", Toast.LENGTH_LONG).show();
                } else if (!validRoomCap()) {
                    Toast.makeText(this, "ROOM CAPACITY entered is invalid!", Toast.LENGTH_LONG).show();
                } else {
                    boolean created = getRoomManager().createRoom(roomNum, Integer.parseInt(roomCap));
                    if (created) {
                        super.writeRoom();
                        setResult(RESULT_OK, intent);
                        finish();
                    } else {
                        Toast.makeText(this, "There is ROOM NUMBER CONFLICT!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.room_added_back:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    private void setUpData() {
        EditText num = findViewById(R.id.room_num_added);
        EditText cap = findViewById(R.id.room_cap_added);
        roomNum = num.getText().toString();
        roomCap = cap.getText().toString();
    }

    private boolean dataMissing() {
        return roomNum.equals("") || roomCap.equals("");
    }

    private boolean validRoomNum() {
        return getRoomManager().checkValidNum(roomNum);
    }

    private boolean validRoomCap() {
        return getRoomManager().checkValidNum(roomCap);
    }
}