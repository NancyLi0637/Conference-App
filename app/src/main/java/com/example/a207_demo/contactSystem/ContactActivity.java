package com.example.a207_demo.contactSystem;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.BaseActivity;

/**
 * ContactActivity
 */
public abstract class ContactActivity extends BaseActivity {

    /**
     * createContactMenu
     *
     * @param recyclerView   RecyclerView
     * @param contactAdapter ContactAdapter
     */
    public void createContactMenu(RecyclerView recyclerView, ContactAdapter contactAdapter) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

    /**
     * initContacts
     */
    protected void initContacts() {
        super.reset();
        super.readUser();
        super.readEvent();
        super.readConversation();
    }

}
