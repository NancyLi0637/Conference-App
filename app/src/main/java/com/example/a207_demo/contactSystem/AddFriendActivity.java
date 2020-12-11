package com.example.a207_demo.contactSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.BaseActivity;

public class AddFriendActivity extends BaseActivity implements View.OnClickListener{

    private String myID;
    private String userID;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_add);
        ActivityCollector.addActivity(this);

        init();
    }

    public void init(){
        intent = new Intent();
        super.reset();
        super.readUser();
        loadView();
        loadButton();
    }

    private void loadView(){
        myID = getIntent().getStringExtra("myID");
        userID = getIntent().getStringExtra("userID");
        String userName = getIntent().getStringExtra("userName");
        String userType = getIntent().getStringExtra("userType");
        String content = userName + "\n" + userType;

        ImageView imageUser = findViewById(R.id.add_friend_image);
        TextView textUser = findViewById(R.id.add_friend_name);
        Glide.with(this).load(R.drawable.icon_contact_gray).into(imageUser);
        textUser.setText(content);
    }

    private void loadButton(){
        Button back = findViewById(R.id.friend_back_search);
        Button add = findViewById(R.id.btn_add);

        back.setOnClickListener(this);
        add.setOnClickListener(this);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_add:
                if(myID.equals(userID)){
                    Toast.makeText(this, "You cannot add yourself!",
                            Toast.LENGTH_LONG).show();
                }else if(areFriends()){
                    Toast.makeText(this, "This user is ALREADY in your friend list!",
                            Toast.LENGTH_LONG).show();
                }else if(addFriend()){
                    super.writeUser();
                    Toast.makeText(this, "You have successfully added this user!!",
                            Toast.LENGTH_LONG).show();
                    setResult(RESULT_OK, intent);
                    finish();
                }else{
                    Toast.makeText(this, "Some errors have occurred!",
                            Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.friend_back_search:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }
    }

    private boolean areFriends(){
        return getUserManager().areFriends(myID, userID);
    }

    private boolean addFriend(){
        return getUserManager().addFriend(myID, userID);
    }

}