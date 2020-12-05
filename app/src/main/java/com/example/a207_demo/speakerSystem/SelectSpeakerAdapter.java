package com.example.a207_demo.speakerSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SelectSpeakerAdapter extends RecyclerView.Adapter<SelectSpeakerAdapter.VHSelectSpeaker> {
    private Context context;
    private List<String> speakerList;
    private ArrayList<String> speakerNames;

    public SelectSpeakerAdapter(Context context, List<String> speakerList){
        this.context = context;
        this.speakerList = speakerList;
    }

    @NonNull
    @Override
    public VHSelectSpeaker onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_select_speaker, parent, false);
        VHSelectSpeaker holder = new VHSelectSpeaker(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final VHSelectSpeaker holder, int position) {
        final String name = speakerList.get(position);
        holder.speakerNum.setText(name);
        holder.selectedSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.selectedSpeaker.isChecked()){
                    speakerNames.add(name);
                }else{
                    speakerNames.remove(name);
                }
            }
        });

    }

    public ArrayList<String> getSpeakerNames(){return this.speakerNames;}

    @Override
    public int getItemCount() {
        return speakerList.size();
    }

    public class VHSelectSpeaker extends RecyclerView.ViewHolder{

        private TextView speakerNum;
        private CheckBox selectedSpeaker;

        public VHSelectSpeaker(View v){
            super(v);
            speakerNum = v.findViewById(R.id.speaker_name);
            selectedSpeaker = v.findViewById(R.id.speaker_selected);
            speakerNames = new ArrayList<>();

        }
    }
}
