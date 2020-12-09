package com.example.a207_demo.accountSystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

import java.util.ArrayList;

public class OrganizerAccountActivity extends AccountActivity {

    @Override
    public void init() {
        super.init(this, R.id.nav_view_organizer, R.id.nav_account_organizer);
        super.init();
        createAccountMenu();
    }

    protected void createAccountMenu(){
        super.createAccountMenu(getOrganizerManager().generateAccountInfo());
    }
}