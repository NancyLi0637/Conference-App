package com.example.a207_demo.eventSystem;

import android.content.Context;
import android.content.Intent;
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
import java.util.List;

/**
 * Abstract class EventAdapter
 */
public abstract class EventAdapter extends RecyclerView.Adapter<EventAdapter.VHEvent> implements Serializable {
    private Context context;
    private ArrayList<ArrayList<String>> eventList;

    /**
     * Event Adapter for this Event Activity
     * @param context
     * @param eventList
     */
    public EventAdapter(Context context, ArrayList<ArrayList<String>> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    /**
     * on Create View Holder
     * @param parent parent ViewGroup
     * @param viewType viewType
     * @return VHEvent
     */
    abstract public VHEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    /**
     * set Click Event Listener
     * @param holder VHEvent
     * @param nextClass Class
     */
    public void setClickEventListener(final VHEvent holder, final Class nextClass) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                ArrayList<String> event = eventList.get(position);
                Intent intent = new Intent(context, nextClass);
                intent.putStringArrayListExtra("event", event);
                context.startActivity(intent);
            }
        });
    }

    /**
     * on Bind View Holder
     * @param holder VHEvent
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull VHEvent holder, int position) {
        List<String> event= eventList.get(position);
        holder.eventTitle.setText(event.get(0));
        //Todo: implement image later
        Glide.with(context).load(R.drawable.default_image).into(holder.eventImage);
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
    static class VHEvent extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView eventImage;
        TextView eventTitle;

        public VHEvent(View v) {
            super(v);
            cardView = (CardView) v;
            eventImage = v.findViewById(R.id.event_image);
            eventTitle = v.findViewById(R.id.event_title);
            ;
        }
    }

}
