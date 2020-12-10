package com.example.a207_demo.eventSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventEnrollmentAdapter extends EventAdapter implements Serializable {
    private Context context;
    private ArrayList<ArrayList<String>> eventList;

    /**
     * Event Adapter for this Event Activity
     * @param context
     * @param eventList
     */
    public EventEnrollmentAdapter(Context context, ArrayList<ArrayList<String>> eventList) {
        super(context, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    /**
     * on Create View Holder
     * @param parent parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    @Override
    public VHEventEnrollment onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false);
        VHEventEnrollment holder = new VHEventEnrollment(v);
        return holder;
    }

    /**
     * on Bind View Holder
     * @param holder VHEvent
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull VHEvent holder, int position) {
        VHEventEnrollment newHolder = ((VHEventEnrollment) holder);
        ArrayList<String> event= eventList.get(position);
        String content = event.get(1) + "\nSpace remaining: " + event.get(8);
        newHolder.eventInfo.setText(content);
    }

    /**
     * getItemCount
     * @return int
     */
    @Override
    public int getItemCount() {
        return eventList.isEmpty() ? 0 : eventList.size();
    }

    /**
     * VHEvent
     */
    static class VHEventEnrollment extends VHEvent {
        CardView cardView;
        TextView eventInfo;

        public VHEventEnrollment(View v) {
            super(v);
            cardView = (CardView) v;
            eventInfo = v.findViewById(R.id.room_info);
        }
    }

}
