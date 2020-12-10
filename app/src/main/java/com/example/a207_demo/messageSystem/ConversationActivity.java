package com.example.a207_demo.messageSystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.BaseActivity;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 */
public class ConversationActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<ArrayList<String>> conversationList = new ArrayList<>();
    private ConversationAdapter conversationAdapter;
    private RecyclerView conversationRecyclerView;

    private String myID;
    private String userID;
    private String userName;
    private HashSet<String> userIDs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ActivityCollector.addActivity(this);

        init();
    }

    /**
     * init
     */
    public void init() {
        super.init();
        loadData();

        createActionBar();
        createConversationMenu();

        sendMsg();
    }

    /**
     * createActionBar
     */
    public void createActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * createActionBar
     */
    private void createConversationMenu() {
        initMsg();
        conversationRecyclerView = findViewById(R.id.conversation_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        conversationRecyclerView.setLayoutManager(linearLayoutManager);
        conversationAdapter = new ConversationAdapter(this, conversationList, myID);
        conversationRecyclerView.setAdapter(conversationAdapter);
        conversationRecyclerView.scrollToPosition(conversationList.size()-1);
    }

    private void loadData(){
        myID = getIntent().getStringExtra("myID");
        userID = getIntent().getStringExtra("userID");
        userName = getIntent().getStringExtra("userName");
        userIDs = new HashSet<>();
        userIDs.add(myID);
        userIDs.add(userID);
    }

    /**
     * sendMsg
     */
    public void sendMsg() {
        Button send = findViewById(R.id.btn_send);
        send.setOnClickListener(this);
    }

    /**
     * sendMsg
     *
     * @param v View
     */
    @Override
    public void onClick(View v) {
        EditText inputText = findViewById(R.id.input_msg);
        String content = inputText.getText().toString();

        if (!"".equals(content)) {
            getConversationManager().sendMessage(myID, content);
            super.writeConversation();
            conversationAdapter.notifyDataSetChanged();
            conversationRecyclerView.scrollToPosition(conversationList.size() - 1);
            inputText.setText("");
        }else{
            Toast.makeText(this, "Message cannot be EMPTY!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * initMsg
     */
    public void initMsg() {
        super.reset();
        //super.readUser();
        super.readConversation();

        getConversationManager().createConversation(userIDs);
        getConversationManager().currentConversationSetter(userIDs);
        conversationList = getConversationManager().getMessagesOfCurrentConversation();
    }

}
