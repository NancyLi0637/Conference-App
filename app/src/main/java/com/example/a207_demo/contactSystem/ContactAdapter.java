package com.example.a207_demo.contactSystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;
import com.example.a207_demo.eventSystem.EventAdapter;
import com.example.a207_demo.messageSystem.MsgActivity;
import com.example.a207_demo.messageSystem.SendAnnouncementActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * ContactAdapter
 */
public abstract class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.VHContact> {

    private Context context;
    private ArrayList<ArrayList<String>> contactsList;
    private String myID;
    private String userName;
    private String userID;

    /**
     * ContactAdapter
     *
     * @param context
     * @param contactsList
     */
    public ContactAdapter(Context context, ArrayList<ArrayList<String>> contactsList, String myID) {
        this.context = context;
        this.contactsList = contactsList;
        this.myID = myID;
    }

    /**
     * ContactAdapter
     *
     * @param context
     * @param contactsList
     */
    public ContactAdapter(Context context, ArrayList<ArrayList<String>> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    /**
     * on Create View Holder
     * @param parent parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    @Override
    abstract public VHContact onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    /**
     * onBindViewHolder
     *
     * @param holder   @NonNull VHContact holder
     * @param position final int position
     */
    @Override
    public void onBindViewHolder(@NonNull VHContact holder, final int position) {
        ArrayList<String> user = contactsList.get(position);
        userID = user.get(0);
        userName = user.get(1);
        holder.contactName.setText(userName);
        Glide.with(context).load(R.drawable.jenny).into(holder.contactImage);

    }

    /**
     * getItemCount
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return contactsList.isEmpty() ? 0 : contactsList.size();
    }

    public String getUserID(){
        return userID;
    }

    /**
     * VHContact is a static class VHContact extends RecyclerView.ViewHolder
     */
    static class VHContact extends RecyclerView.ViewHolder {
        ImageView contactImage;
        TextView contactName;
        View mView;

        /**
         * VHContact constructor
         *
         * @param v View
         */
        public VHContact(View v) {
            super(v);
            mView = v;
            contactImage = v.findViewById(R.id.contact_image);
            contactName = v.findViewById(R.id.contact_name);
            ;
        }
    }
}
