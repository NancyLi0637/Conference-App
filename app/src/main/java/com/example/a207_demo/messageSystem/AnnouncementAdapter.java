package com.example.a207_demo.messageSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * AnnouncementAdapter
 */
public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.VHAnnouncement> {

    private Context context;
    private ArrayList<String> announcementList;


    /**
     * MsgAdapter constructor
     *
     * @param context          Context
     * @param announcementList ArrayList<String>
     */
    public AnnouncementAdapter(Context context, ArrayList<String> announcementList) {
        this.context = context;
        this.announcementList = announcementList;
    }

    /**
     * onCreateViewHolder
     *
     * @param parent   NonNull ViewGroup parent
     * @param viewType int
     * @return VHMsg
     */
    @NonNull
    @Override
    public VHAnnouncement onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_announcement, parent, false);
        VHAnnouncement holder = new VHAnnouncement(view);
        return holder;
    }

    /**
     * onBindViewHolder
     *
     * @param holder   NonNull VHMsg holder
     * @param position int position
     */
    @Override
    public void onBindViewHolder(@NonNull VHAnnouncement holder, int position) {
        String announcement = announcementList.get(position);

        holder.announcement.setText(announcement);
    }

    /**
     * getItemCount
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return announcementList.isEmpty() ? 0 : announcementList.size();
    }

    /**
     * VHMsg
     */
    static class VHAnnouncement extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView announcement;


        /**
         * VHMsg
         *
         * @param view View
         */
        public VHAnnouncement(View view) {
            super(view);
            cardView = (CardView) view;
            announcement = view.findViewById(R.id.announcement_info);
        }

    }

}
