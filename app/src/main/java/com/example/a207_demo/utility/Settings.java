package com.example.a207_demo.utility;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

public class Settings extends AppCompatActivity {
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();

        ActivityCollector.addActivity(this);
    }

    public void init(){
        ID = getIntent().getStringExtra("ID");
        TextView userId = findViewById(R.id.username_setting);
        userId.setText(ID);
        createActionBar();
    }

    public void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}