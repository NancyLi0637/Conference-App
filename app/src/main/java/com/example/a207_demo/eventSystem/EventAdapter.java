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

public abstract class EventAdapter extends RecyclerView.Adapter<EventAdapter.VHEvent> {
    private Context context;
    private List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList){
        this.context = context;
        this.eventList = eventList;
    }
    
    abstract public VHEvent onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public void setClickEventListener(final VHEvent holder, final Class nextClass){
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Event event = eventList.get(position);
                Intent intent = new Intent(context, nextClass);
                intent.putExtra("event_title", event.getTitle());
                intent.putExtra("event_room", event.getRoomID());
                intent.putExtra("event_time", event.getStartTime());
                intent.putExtra("event_duration", event.getDuration());
                intent.putExtra("event_image_id", event.getImageId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull VHEvent holder, int position) {
        Event event = eventList.get(position);
        holder.eventTitle.setText(event.getTitle());
        Glide.with(context).load(event.getImageId()).into(holder.eventImage);
    }

    @Override
    public int getItemCount() {
        return eventList.isEmpty() ? 0 : eventList.size();
    }

    static class VHEvent extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView eventImage;
        TextView eventTitle;

        public VHEvent(View v){
            super(v);
            cardView = (CardView) v;
            eventImage = v.findViewById(R.id.event_image);
            eventTitle = v.findViewById(R.id.event_title);;
        }
    }

}
