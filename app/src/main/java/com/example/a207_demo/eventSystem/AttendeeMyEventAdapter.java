package com.example.a207_demo.eventSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a207_demo.R;

import java.util.List;

/**
 * AttendeeMyEventAdapter
 */
public class AttendeeMyEventAdapter extends EventAdapter {
    private Context context;

    /**
     * AttendeeMyEventAdapter
     * @param context Context
     * @param eventList List<Event>
     */
    public AttendeeMyEventAdapter(Context context, List<Event> eventList){
        super(context, eventList);
        this.context = context;
    }

    /**
     * onCreateViewHolder
     * @param parent parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    @NonNull
    @Override
    public VHEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        VHEvent holder = new VHEvent(v);
        setClickEventListener(holder, AttendeeMyEventContentActivity.class);
        return holder;
    }
}
