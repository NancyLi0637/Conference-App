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
    private int imageID;
    private ArrayList<Integer> imageIDs = new ArrayList<>();

    /**
     * Event Adapter for this Event Activity
     * @param context Context
     * @param eventList ArrayList<ArrayList<String>>
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
    @Override
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
                // Pass the list of events to the next activity
                intent.putStringArrayListExtra("event", event);
                intent.putExtra("imageID", imageIDs.get(position));
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
        holder.eventTitle.setText(event.get(1));
        String eventType = event.get(5);
        loadImage(eventType);
        Glide.with(context).load(imageID).into(holder.eventImage);
        imageIDs.add(imageID);
    }

    //loading presenter image choices
    private void loadImage(String eventType){
        if(eventType.equals("TALK")){
            imageID = R.drawable.talk;
        }else if(eventType.equals("DISCUSSION")){
            imageID = R.drawable.discussion;
        }else{
            imageID = R.drawable.party;
        }
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
