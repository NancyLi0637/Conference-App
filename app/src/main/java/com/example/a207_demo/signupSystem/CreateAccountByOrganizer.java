package com.example.a207_demo.signupSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.AnnouncementAdapter;
import com.example.a207_demo.roomSystem.CreateRoomActivity;
import com.example.a207_demo.roomSystem.RoomActivity;
import com.example.a207_demo.roomSystem.RoomAdapter;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.SetUpActivity;

import java.util.ArrayList;

public class CreateAccountByOrganizer extends SetUpActivity implements View.OnClickListener{
    private SwipeRefreshLayout swipeRefreshLayout;

    private CreateAccountByOrgAdapter createAccountByOrgAdapter;
    private ArrayList<ArrayList<String>> accountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_by_organizer);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        super.init(this, R.id.nav_view_organizer, R.id.nav_account);

        Button addAccount = findViewById(R.id.btn_addAccount);
        addAccount.setOnClickListener(this);
        swipeRefreshLayout = findViewById(R.id.account_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshAccounts();
            }
        });

        createAccountMenu();
    }

    /**
     * onClick
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CreateAccountByOrganizer.this, SignUpActivity.class);
        intent.putExtra("class", "ORGANIZER");
        startActivity(intent);
    }

    /**
     * createEventMenu
     */
    protected void createAccountMenu() {
        RecyclerView recyclerView = findViewById(R.id.account_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        initAccounts();
        createAccountByOrgAdapter = new CreateAccountByOrgAdapter(this, accountList);
        recyclerView.setAdapter(createAccountByOrgAdapter);
    }

    /**
     * initEvents
     */
    protected void initAccounts() {
       super.init();

       accountList = getUserManager().generateAccountInfo();

//        //Todo: implement image later
//        for (Event event : eventList) {
//            event.setImageId(R.drawable.default_image);
//        }

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
                    refreshAccounts();
                }
                break;
        }
    }

    private void refreshAccounts() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAccountMenu();
                createAccountByOrgAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}