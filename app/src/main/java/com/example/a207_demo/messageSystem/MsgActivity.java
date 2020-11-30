package com.example.a207_demo.messageSystem;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.ActivityCollector;
import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

public class MsgActivity extends AppCompatActivity implements View.OnClickListener{
    //Todo: access MsgSystem Controller
    private List<Msg> msgList = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    private MsgAdapter msgAdapter;
    private RecyclerView msgRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        init();

        ActivityCollector.addActivity(this);

    }

    public void init(){
        createActionBar();
        createRecyclerView();
        initMsg();
        sendMsg();
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

    public void createRecyclerView(){
        msgRecyclerView = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(this, msgList);
        msgRecyclerView.setAdapter(msgAdapter);
    }

    public void sendMsg(){
        Button send = findViewById(R.id.btn_send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        EditText inputText = findViewById(R.id.input_msg);
        String content = inputText.getText().toString();

        if(!"".equals(content)) {
            //Todo: access MsgSystem Controller
            Msg msg = new Msg(content, Msg.TYPE_SENT);
            msgList.add(msg);
            msgAdapter.notifyItemInserted(msgList.size()-1);
            msgRecyclerView.scrollToPosition(msgList.size()-1);
            inputText.setText("");
        }
    }

    public void initMsg(){
        Msg msg1 = new Msg("Hello guys.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("No hello.", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("Ok fine", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }




}
