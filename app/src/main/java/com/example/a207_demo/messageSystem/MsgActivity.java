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

import com.example.a207_demo.use_cases.ConversationManager;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.BaseActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.example.a207_demo.entities.*;
/**
 *
 */
public class MsgActivity extends BaseActivity implements View.OnClickListener {
    //Todo: access MsgSystem Controller
    private ArrayList<ArrayList<String>> conversationList = new ArrayList<>();
    private MsgAdapter msgAdapter;
    private RecyclerView msgRecyclerView;
    //private Conversation currentConversation;

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
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(this, conversationList, myID);
        msgRecyclerView.setAdapter(msgAdapter);
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
            //Todo: write to database
            getConversationManager().sendMessage(myID, content);
            super.writeConversation();
            //Msg msg = new Msg(content, Msg.TYPE_SENT);
            //msgList.add(msg);
            ArrayList<String> newMsg = new ArrayList<>();
            newMsg.add(myID);
            newMsg.add(content);
            conversationList.add(newMsg);
            msgAdapter.notifyItemInserted(conversationList.size() - 1);
            msgRecyclerView.scrollToPosition(conversationList.size() - 1);
            inputText.setText("");
        }else{
            Toast.makeText(this, "Message cannot be EMPTY!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * initMsg
     */
    public void initMsg() {
//        Msg msg1 = new Msg("Hello guys.", Msg.TYPE_RECEIVED);
//        msgList.add(msg1);
//        Msg msg2 = new Msg("No hello.", Msg.TYPE_SENT);
//        msgList.add(msg2);
//        Msg msg3 = new Msg("Ok fine", Msg.TYPE_RECEIVED);
//        msgList.add(msg3);
         //Todo: intent should get the other user id from contact adapter (otherID) hard code rightnow
        super.reset();
        //super.readUser();
        super.readConversation();

        getConversationManager().currentConversationSetter(userIDs);
        conversationList = getConversationManager().getMessagesOfCurrentConversation();

        //String otherID = "abc123456";
        //userID: getID(), friendIG: getIntent().getStringExtra('friendID'), friendName: getIntent().getStringExtra('friendName')
        //ConversationManager conversationManager = getConversationManager();
        //HashSet<String> talkerList = new HashSet<String>();
        //talkerList.add(getID());
        //talkerList.add(otherID);
        //conversationManager.currentConversationSetter(talkerList);
        //ArrayList<String[]> messages = conversationManager.getMessagesOfCurrentConversation();
//        for (String[] message: messages){
//            Msg msg;
//            if (message[0].equals(getID())){
//                msg = new Msg(message[1], Msg.TYPE_SENT);
//            }else{
//                msg = new Msg(message[1], Msg.TYPE_RECEIVED);
//            }
//            msgList.add(msg);
//        }
    }

}
