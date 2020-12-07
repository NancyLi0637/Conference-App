package com.example.a207_demo.eventSystem;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;

/**
 * EventActivity
 */
public class EventActivity extends SetUpActivity {

    private SwipeRefreshLayout swipeRefreshLayout;

    /**
     * createEventMenu
     * @param recyclerView RecyclerView recyclerView
     */
    protected void createEventMenu(RecyclerView recyclerView) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout = findViewById(R.id.event_swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshEvents();
            }
        });
    }

    /**
     * initEvents
     */
    protected void initEvents() {
        super.reset();
        super.readEvent();
        super.readRoom();
        super.readUser();
    }

    protected void refreshEvents(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                createEventMenu();
//                eventAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}