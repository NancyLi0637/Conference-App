package com.example.a207_demo.accountSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * AttendeeAccountActivity
 */
public class AttendeeAccountActivity extends AccountActivity {

    @Override
    protected void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_account_attendee);
        super.init();
        createAccountMenu();
    }

    protected void createAccountMenu(){
        super.createAccountMenu(getAttendeeManager().generateAccountInfo());
    }


}