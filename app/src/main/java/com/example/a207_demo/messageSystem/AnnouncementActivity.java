package com.example.a207_demo.messageSystem;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;


public class AnnouncementActivity extends SetUpActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        super.createActionBar();
        super.createNavView(this, R.id.nav_announcements);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}