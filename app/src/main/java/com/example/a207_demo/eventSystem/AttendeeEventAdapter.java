package com.example.a207_demo.eventSystem;

import android.content.Context;
import android.content.Intent;
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

import java.util.List;

/**
 * A user case class for setting up scrolling view for Event in android.
 */
public class AttendeeEventAdapter extends EventAdapter {
    private Context context;

    public AttendeeEventAdapter(Context context, List<Event> eventList){
        super(context, eventList);
        this.context = context;
    }

    @NonNull
    @Override
    public VHEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        VHEvent holder = new VHEvent(v);
        setClickEventListener(holder, AttendeeEventContentActivity.class);
        return holder;
    }
}