package com.example.a207_demo.eventSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a207_demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * SpeakerEventAdapter
 */
public class SpeakerEventAdapter extends EventAdapter {
    private Context context;

    /**
     * SpeakerEventAdapter
     * @param context Context
     * @param eventList List<Event>
     */
    public SpeakerEventAdapter(Context context, ArrayList<ArrayList<String>> eventList) {
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
        setClickEventListener(holder, SpeakerEventContentActivity.class);
        return holder;
    }
}
