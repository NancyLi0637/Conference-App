package com.example.a207_demo.accountSystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a207_demo.R;
import com.example.a207_demo.utility.ActivityCollector;

public class AllAccountActivity extends AccountActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void init(){
        setContentView(R.layout.activity_account_all);
        super.init(this, R.id.nav_view_organizer, R.id.nav_account_all);
        super.init();

        Button addAccount = findViewById(R.id.btn_addAccount);
        addAccount.setOnClickListener(this);

        createAccountMenu();
    }

    protected void createAccountMenu(){
        super.createAccountMenu(getUserManager().generateAccountInfo());
    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(AllAccountActivity.this, SignUpActivity.class);
        intent.putExtra("class", "ACCOUNT");
        startActivity(intent);
    }

}