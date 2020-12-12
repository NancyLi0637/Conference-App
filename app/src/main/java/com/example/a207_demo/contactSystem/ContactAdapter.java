package com.example.a207_demo.contactSystem;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;

import java.util.ArrayList;

/**
 * ContactAdapter
 */
public abstract class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.VHContact> {

    private Context context;
    private ArrayList<ArrayList<String>> contactsList;
    private String myID;
    private String userName;
    private String userID;
    private int imageID;

    /**
     * ContactAdapter
     *
     * @param context      Context
     * @param contactsList ArrayList<ArrayList<String>>
     * @param myID         String
     * @param imageID      int
     */
    public ContactAdapter(Context context, ArrayList<ArrayList<String>> contactsList, String myID, int imageID) {
        this.context = context;
        this.contactsList = contactsList;
        this.myID = myID;
        this.imageID = imageID;
    }

    /**
     * ContactAdapter
     *
     * @param context      Context
     * @param contactsList ArrayList<ArrayList<String>>
     */
    public ContactAdapter(Context context, ArrayList<ArrayList<String>> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    /**
     * on Create View Holder
     *
     * @param parent   parent ViewGroup
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
        Glide.with(context).load(imageID).into(holder.contactImage);
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

    /**
     * getMyID
     *
     * @return String
     */
    public String getMyID() {
        return myID;
    }

    /**
     * getContactsList
     *
     * @return ArrayList<ArrayList < String>>
     */
    public ArrayList<ArrayList<String>> getContactsList() {
        return contactsList;
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
