package com.example.a207_demo.contactSystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.MsgActivity;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;

import java.util.ArrayList;
import java.util.HashSet;

public class ContactMsgAdapter extends ContactAdapter{

    private Context context;

    public ContactMsgAdapter(Context context, ArrayList<ArrayList<String>> contactList, String myID) {
        super(context, contactList, myID);
        this.context = context;
    }

    /**
     * onCreateViewHolder for OrganizerEventAdapter
     *
     * @param parent   parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    @NonNull
    @Override
    public VHContact onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        VHContact holder = new VHContact(v);
        setClickContactListener(holder, MsgActivity.class);
        return holder;
    }


    /**
     * setClickContactListener
     *
     * @param holder ContactAdapter.VHContact holder
     */
    public void setClickContactListener(final VHContact holder, final Class nextClass) {
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                ArrayList<String> userInfo = getContactsList().get(position);

                Intent intent = new Intent(context, nextClass);
                intent.putExtra("myID", getMyID());
                intent.putExtra("userID", userInfo.get(0));
                intent.putExtra("userName", userInfo.get(1));
                context.startActivity(intent);
            }
        });

    }

}
