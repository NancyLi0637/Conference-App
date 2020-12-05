package com.example.a207_demo.roomSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.io.Serializable;
import java.util.List;

public class SelectRoomAdapter extends RecyclerView.Adapter<SelectRoomAdapter.VHSelectRoom> implements Serializable {
    private Context context;
    private List<String> roomList;
    private int lastSelectedRoom = -1;

    public SelectRoomAdapter(Context context, List<String> roomList){
        this.context = context;
        this.roomList = roomList;
    }

    @NonNull
    @Override
    public VHSelectRoom onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_select_room, parent, false);
        VHSelectRoom holder = new VHSelectRoom(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VHSelectRoom holder, int position) {
        String room = roomList.get(position);
        holder.roomNum.setText(room);
        holder.selectedRoom.setChecked(lastSelectedRoom == position);
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class VHSelectRoom extends RecyclerView.ViewHolder{

        private TextView roomNum;
        private RadioButton selectedRoom;

        public VHSelectRoom(View v){
            super(v);
            roomNum = v.findViewById(R.id.room_num);
            selectedRoom = v.findViewById(R.id.room_selected);

            selectedRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedRoom = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }

    }
}
