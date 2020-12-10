package com.example.a207_demo.accountSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.BaseActivity;

import java.util.ArrayList;

/**
 * AccountActivity
 */
public class AccountActivity extends BaseActivity {
    private SwipeRefreshLayout swipeRefreshLayout;

    private AccountAdapter accountAdapter;
    private ArrayList<ArrayList<String>> accountList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_others);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * initEvents
     */
    protected void init() {
        super.reset();
        super.readUser();

        setSwipeRefreshLayout();
    }

    protected void setSwipeRefreshLayout() {
        swipeRefreshLayout = findViewById(R.id.account_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshAccounts();
            }
        });
    }


    protected void createAccountMenu(ArrayList<ArrayList<String>> accountList) {
        this.accountList = accountList;
        RecyclerView recyclerView = findViewById(R.id.account_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        accountAdapter = new AccountAdapter(this, accountList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(accountAdapter);

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

    protected void refreshAccounts() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                createAccountMenu(accountList);
                accountAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}