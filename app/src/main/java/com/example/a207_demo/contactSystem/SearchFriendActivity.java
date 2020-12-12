package com.example.a207_demo.contactSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;
import com.example.a207_demo.utility.BaseActivity;

/**
 * AddFriendActivity
 */
public class SearchFriendActivity extends BaseActivity implements View.OnClickListener{

    private Intent intent;
    private String userID;

    /**
     * onCreate
     *
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_search);
        ActivityCollector.addActivity(this);
        init();
    }

    /**
     * init
     */
    public void init() {
        intent = new Intent();
        super.reset();
        super.readUser();

        Button back = findViewById(R.id.friend_back_contact);
        Button search = findViewById(R.id.btn_search);
        back.setOnClickListener(this);
        search.setOnClickListener(this);
    }

    /**
     * onClick
     * @param v View
     */
    public void onClick(View v) {

        switch ((v.getId())){
            case R.id.btn_search:
                EditText friend = findViewById(R.id.friend_name);
                String friendName = friend.getText().toString();
                if(friendName.equals("")){
                    Toast.makeText(SearchFriendActivity.this, "Please enter a VALID NAME!",
                            Toast.LENGTH_LONG).show();
                }else if(!searchFriend(friendName)){
                    Toast.makeText(SearchFriendActivity.this, "Please check the attendee " +
                                    "name again",
                            Toast.LENGTH_LONG).show();
                }else{
                    intent = new Intent(SearchFriendActivity.this, AddFriendActivity.class);
                    userID = getUserManager().getUserIdFromName(friendName);
                    intent.putExtra("myID", getIntent().getStringExtra("myID"));
                    intent.putExtra("userID", userID);
                    intent.putExtra("userName", friendName);
                    intent.putExtra("userType", getUserManager().getUserType(userID));
                    startActivityForResult(intent, 1);
                }
                break;
            case R.id.friend_back_contact:
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
        }

    }

    private boolean searchFriend(String friendName){
        return getAttendeeManager().checkAttendee(getUserManager().getUserIdFromName(friendName));
    }



    /**
     * onActivityResult
     * @param requestCode requestCode
     * @param resultCode resultCode
     * @param data Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }
}
