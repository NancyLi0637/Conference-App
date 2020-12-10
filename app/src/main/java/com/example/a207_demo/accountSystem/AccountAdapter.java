package com.example.a207_demo.accountSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * AccountAdapter
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.VHAccount> implements Serializable {
    private Context context;
    private ArrayList<ArrayList<String>> accountList;
    private int imageID;

    /**
     * Event Adapter for this Event Activity
     *
     * @param context
     * @param accountList
     */
    public AccountAdapter(Context context, ArrayList<ArrayList<String>> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    /**
     * on Create View Holder
     *
     * @param parent   parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    public VHAccount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_account, parent, false);
        VHAccount holder = new VHAccount(v);
        return holder;
    }

    /**
     * on Bind View Holder
     *
     * @param holder   VHEvent
     * @param position int
     */
    @Override
    public void onBindViewHolder(VHAccount holder, int position) {
        ArrayList<String> accountInfo = accountList.get(position);
        String name = accountInfo.get(0);
        String type = accountInfo.get(1);
        String email = accountInfo.get(2);
        String id = accountInfo.get(3);
        String content = name + "\n" + type + "\n" + email + "\n" + "ID: " + id;

        holder.accountInfo.setText(content);
        loadImage(type);
        Glide.with(context).load(imageID).into(holder.accountImage);
    }

    private void loadImage(String accountType){
        if(accountType.equals("ORGANIZER")){
            imageID = R.drawable.organizer2;
        }else if(accountType.equals("SPEAKER")){
            imageID = R.drawable.speaker;
        }else if(accountType.equals("ATTENDEE")){
            imageID = R.drawable.icon_contact_gray;
        }else{
            imageID = R.drawable.vip;
        }
    }


    /**
     * getItemCount
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return accountList.isEmpty() ? 0 : accountList.size();
    }

    /**
     * VHEvent
     */
    static class VHAccount extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView accountImage;
        TextView accountInfo;

        public VHAccount(View v) {
            super(v);
            cardView = (CardView) v;
            accountImage = v.findViewById(R.id.account_image);
            accountInfo = v.findViewById(R.id.account_info);
        }
    }

}
