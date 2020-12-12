package com.example.a207_demo.contactSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

public class OrganizerContactOrganizerActivity extends ContactActivity implements View.OnClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ContactMsgAdapter contactMsgAdapter;

    private ArrayList<ArrayList<String>> contactList;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_organizer_organizer);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * init
     */
    public void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_contacts_organizer_for_organizer);
        setSwipeRefreshLayout();

        Button addAttendee = findViewById(R.id.btn_add_organizer);
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
        Intent intent = new Intent(OrganizerContactOrganizerActivity.this, SearchFriendActivity.class);
        intent.putExtra("myID", getID());
        intent.putExtra("from", "organizerContact");
        startActivityForResult(intent, 1);
    }

    /**
     * createContactMenu
     */
    public void createContactMenu() {
        initContacts();
        RecyclerView recyclerView = findViewById(R.id.organizer_contact_organizer_recycler_view);
        contactMsgAdapter = new ContactMsgAdapter(this, contactList, getID(),
                R.drawable.organizer2);
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