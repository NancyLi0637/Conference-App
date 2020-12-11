package com.example.a207_demo.contactSystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;

import java.util.ArrayList;

/**
 * Organizer Contact Adapter
 */
public class OrganizerContactAdapter extends ContactAdapter {

    private Context context;
    private String type;
    private String myID;

    public OrganizerContactAdapter(Context context, ArrayList<ArrayList<String>> contactList,
                                   String myID, String type, int imageID) {
        super(context, contactList, myID, imageID);
        this.context = context;
        this.type = type;
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
        setClickContactListener(holder, SendAnnouncementActivity.class);
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
                intent.putExtra("class", type);
                intent.putExtra("eventTitle", "");
                ArrayList<String> ID = new ArrayList<>();
                ID.add(userInfo.get(0));
                intent.putExtra("userIDs", ID);
                context.startActivity(intent);
            }
        });

    }

}
