package com.example.a207_demo.eventSystem;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.a207_demo.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * OrganizerEventAdapter
 */
public class OrganizerEventAdapter extends EventAdapter implements Serializable {
    private Context context;

    /**
     * OrganizerEventAdapter Constructor
     * @param context Context
     * @param eventList ArrayList<ArrayList<String>> eventList
     */
    public OrganizerEventAdapter(Context context, ArrayList<ArrayList<String>> eventList, String ID) {
        super(context, eventList, ID);
        this.context = context;
    }

    /**
     * OrganizerEventAdapter
     * @param context Context
     * @param eventList ArrayList
     */
    public OrganizerEventAdapter(Context context, ArrayList<ArrayList<String>> eventList) {
        super(context, eventList);
        this.context = context;
    }

    /**
     * onCreateViewHolder for OrganizerEventAdapter
     * @param parent parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    @NonNull
    @Override
    public VHEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        VHEvent holder = new VHEvent(v);
        setClickEventListener(holder, OrganizerEventContentActivity.class);
        return holder;
    }

}
