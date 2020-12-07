package com.example.a207_demo.utility;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Settings
 */
public class Settings extends AppCompatActivity {
    private String ID;
    private String TYPE;
    private String EMAIL;
    private String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();

        ActivityCollector.addActivity(this);
    }

    /**
     * init
     */
    public void init(){
        ArrayList<String> info = getIntent().getStringArrayListExtra("info");
        ID = info.get(0);
        TYPE = info.get(1);
        EMAIL = info.get(2);
        USERNAME = info.get(3);

        TextView userId = findViewById(R.id.userid_setting);
        TextView userType = findViewById(R.id.usertype_setting);
        TextView userEmail = findViewById(R.id.useremail_setting);
        TextView userName = findViewById(R.id.username_setting);

        userId.setText(ID);
        userType.setText(TYPE);
        userEmail.setText(EMAIL);
        userName.setText(USERNAME);

        createActionBar();
    }

    /**
     * createActionBar
     */
    public void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * onOptionsItemSelected
     * @param item MenuItem
     * @return boolean
     */
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}