package com.example.a207_demo.eventSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a207_demo.R;

import java.util.ArrayList;

/**
 * SpeakerEventAdapter
 */
public class SpeakerMyEventAdapter extends EventAdapter {
    private Context context;

    /**
     * SpeakerEventAdapter
     * @param context Context
     * @param eventList List<Event>
     */
    public SpeakerMyEventAdapter(Context context, ArrayList<ArrayList<String>> eventList, String ID) {
        super(context, eventList, ID);
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
        setClickEventListener(holder, SpeakerMyEventContentActivity.class);
        return holder;
    }
}
