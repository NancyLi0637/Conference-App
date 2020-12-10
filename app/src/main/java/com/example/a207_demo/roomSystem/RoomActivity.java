package com.example.a207_demo.roomSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.BaseActivity;

import java.util.ArrayList;

/**
 * RoomActivity
 */
public class RoomActivity extends BaseActivity implements View.OnClickListener {
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<ArrayList<String>> roomList;
    private RoomAdapter roomAdapter;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_room);

        Button addEvent = findViewById(R.id.btn_addRoom);
        addEvent.setOnClickListener(this);
        swipeRefreshLayout = findViewById(R.id.room_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRooms();
            }
        });

        createRoomMenu();
    }

    /**
     * onClick
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(RoomActivity.this, CreateRoomActivity.class);
        startActivityForResult(intent, 1);
    }

    /**
     * createEventMenu
     */
    protected void createRoomMenu() {
        initRooms();
        RecyclerView recyclerView = findViewById(R.id.room_recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        roomAdapter = new RoomAdapter(this, roomList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(roomAdapter);
    }

    /**
     * initEvents
     */
    protected void initRooms() {
        super.reset();
        super.readRoom();

        //eventList = getEventManager().getAllEvent();
        roomList = getRoomManager().generateAllInfo();

    }

    /**
     * onActivityResult
     *
     * @param requestCode requestCode
     * @param resultCode  resultCode
     * @param data        Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    refreshRooms();
                }
                break;
        }
    }

    private void refreshRooms() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createRoomMenu();
                roomAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
