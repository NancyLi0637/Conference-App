package com.example.a207_demo.utility;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a207_demo.R;
import com.example.a207_demo.contactSystem.ContactActivity;
import com.example.a207_demo.eventSystem.EventActivity;
import com.example.a207_demo.eventSystem.AttendeeMyEventActivity;
import com.example.a207_demo.messageSystem.AnnouncementActivity;
import com.google.android.material.navigation.NavigationView;

public class SetUpActivity extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;

    public void createActionBar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0,0);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    public void createNavView(final AppCompatActivity context, int id){
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(id);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()){
                    case R.id.nav_allevents:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(context, EventActivity.class));
                        break;
                    case R.id.nav_myEvents:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(context, AttendeeMyEventActivity.class));
                        break;
                    case R.id.nav_contacts:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(context, ContactActivity.class));
                        break;
                    case R.id.nav_announcements:
                        mDrawerLayout.closeDrawers();
                        startActivity(new Intent(context, AnnouncementActivity.class));
                }

                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                startActivity(new Intent(SetUpActivity.this, Settings.class));
                break;
            case R.id.signOut:
                ActivityCollector.finishAll();
        }
        return true;
    }

}
