package com.example.a207_demo.accountSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

/**
 * SpeakerAccountActivity
 */
public class SpeakerAccountActivity extends AccountActivity {

    /**
     * init
     */
    @Override
    protected void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_account_speaker);
        super.init();
        createAccountMenu();
    }

    protected void createAccountMenu() {
        super.createAccountMenu(getSpeakerManager().generateAccountInfo());
    }
}