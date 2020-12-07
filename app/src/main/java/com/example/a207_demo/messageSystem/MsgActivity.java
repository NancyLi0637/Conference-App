package com.example.a207_demo.messageSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.use_cases.ConversationManager;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.SetUpActivity;
import com.example.a207_demo.utility.Settings;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import com.example.a207_demo.entities.*;
/**
 *
 */
public class MsgActivity extends SetUpActivity implements View.OnClickListener {
    //Todo: access MsgSystem Controller
    private List<Msg> msgList = new ArrayList<>();
    private MsgAdapter msgAdapter;
    private RecyclerView msgRecyclerView;
    private Conversation currentConversation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

        ActivityCollector.addActivity(this);

    }

    /**
     * init
     */
    public void init() {
        super.init();
        createActionBar();
        createRecyclerView();
        initMsg();
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
    public void createRecyclerView() {
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(this, msgList);
        msgRecyclerView.setAdapter(msgAdapter);
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
            //Todo: access MsgSystem Controller
            Msg msg = new Msg(content, Msg.TYPE_SENT);
            msgList.add(msg);
            msgAdapter.notifyItemInserted(msgList.size() - 1);
            msgRecyclerView.scrollToPosition(msgList.size() - 1);
            inputText.setText("");
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
        super.readUser();
        String otherID = "abc123456";
        //Todo: connect conversationmanager in cleanarch to filereadwriter
        //userID: getID(), friendIG: getIntent().getStringExtra('friendID'), friendName: getIntent().getStringExtra('friendName')
        ConversationManager conversationManager = getConversationManager();
        HashSet<String> talkerList = new HashSet<String>();
        talkerList.add(getID());
        talkerList.add(otherID);
        conversationManager.currentConversationSetter(talkerList);
        ArrayList<String[]> messages = conversationManager.getMessagesOfCurrentConversation();
        for (String[] message: messages){
            Msg msg;
            if (message[0].equals(getID())){
                msg = new Msg(message[1], Msg.TYPE_SENT);
            }else{
                msg = new Msg(message[1], Msg.TYPE_RECEIVED);
            }
            msgList.add(msg);
        }
    }

}
