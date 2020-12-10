package com.example.a207_demo.roomSystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;
import com.example.a207_demo.eventSystem.EventAdapter;
import com.example.a207_demo.eventSystem.OrganizerEventContentActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * RoomAdapter
 */
public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.VHRoom> implements Serializable {
    private Context context;
    private ArrayList<ArrayList<String>> roomList;

    /**
     * Event Adapter for this Event Activity
     * @param context
     * @param roomList
     */
    public RoomAdapter(Context context, ArrayList<ArrayList<String>> roomList) {
        this.context = context;
        this.roomList = roomList;
    }

    /**
     * on Create View Holder
     * @param parent parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
   public VHRoom onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
       View v = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
       VHRoom holder = new VHRoom(v);
       return holder;
   }

    /**
     * on Bind View Holder
     * @param holder VHEvent
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull VHRoom holder, int position) {
        List<String> roomInfo = roomList.get(position);
        String content = roomInfo.get(0) + "\n" + "Capacity: " + roomInfo.get(1);
        holder.roomInfo.setText(content);
    }

    /**
     * getItemCount
     * @return int
     */
    @Override
    public int getItemCount() {
        return roomList.isEmpty() ? 0 : roomList.size();
    }

    /**
     * VHEvent
     */
    static class VHRoom extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView roomInfo;

        public VHRoom(View v) {
            super(v);
            cardView = (CardView) v;
            roomInfo = v.findViewById(R.id.room_info);
            ;
        }
    }

}
