package com.example.a207_demo.contactSystem;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

/**
 * AttendeeContactAttendeeActivity
 */
public class AttendeeContactAttendeeActivity extends ContactActivity implements View.OnClickListener{

    private SwipeRefreshLayout swipeRefreshLayout;
    private ContactMsgAdapter contactMsgAdapter;

    private ArrayList<ArrayList<String>> contactList;
    private ArrayList<String> userIDs;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_attendee_attendee);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_attendee, R.id.nav_contacts_attendee_for_attendee);
        setSwipeRefreshLayout();

        Button addAttendee = findViewById(R.id.btn_add_friend);
        addAttendee.setOnClickListener(this);

        createContactMenu();
    }

    protected void setSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.contact_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContacts();
            }
        });
    }

    public void onClick(View view){
        Intent intent = new Intent(AttendeeContactAttendeeActivity.this, SearchFriendActivity.class);
        intent.putExtra("myID", getID());
        startActivityForResult(intent, 1);
    }

    /**
     * createContactMenu
     */
    public void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.attendee_contact_attendee_recycler_view);
        contactMsgAdapter = new ContactMsgAdapter(this, contactList, getID());
        super.createContactMenu(recyclerView, contactMsgAdapter);
    }

    protected void initContacts() {
        super.initContacts();

        contactList = getUserManager().generateFormattedFriendInfo(getID());
    }

    /**
     * onActivityResult
     * @param requestCode requestCode
     * @param resultCode resultCode
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    refreshContacts();
                }
                break;
        }
    }

    protected void refreshContacts() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createContactMenu();
                contactMsgAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}