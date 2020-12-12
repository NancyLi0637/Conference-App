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

/**
 * SelectSpeakerAdapter
 */
public class SelectSpeakerAdapter extends RecyclerView.Adapter<SelectSpeakerAdapter.VHSelectSpeaker> {
    private Context context;
    private List<String> speakerList;
    private ArrayList<String> speakerNames;

    /**
     * SelectSpeakerAdapter
     * @param context Context
     * @param speakerList List<String>
     */
    public SelectSpeakerAdapter(Context context, List<String> speakerList) {
        this.context = context;
        this.speakerList = speakerList;
    }

    /**
     * onCreateViewHolder
     *
     * @param parent   NonNull ViewGroup parent
     * @param viewType int viewType
     * @return VHSelectSpeaker
     */
    @NonNull
    @Override
    public VHSelectSpeaker onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_select_speaker, parent, false);
        VHSelectSpeaker holder = new VHSelectSpeaker(v);
        return holder;
    }

    /**
     * onBindViewHolder
     *
     * @param holder   NonNull final VHSelectSpeaker holder
     * @param position int
     */
    @Override
    public void onBindViewHolder(@NonNull final VHSelectSpeaker holder, int position) {
        final String name = speakerList.get(position);
        holder.speakerNum.setText(name);
        holder.selectedSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.selectedSpeaker.isChecked()) {
                    speakerNames.add(name);
                } else {
                    speakerNames.remove(name);
                }
            }
        });

    }

    /**
     * getSpeakerNames
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getSpeakerNames() {
        return this.speakerNames;
    }

    /**
     * getItemCount
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return speakerList.size();
    }

    /**
     * VHSelectSpeaker
     */
    public class VHSelectSpeaker extends RecyclerView.ViewHolder {

        private TextView speakerNum;
        private CheckBox selectedSpeaker;

        /**
         * VHSelectSpeaker
         * @param v View
         */
        public VHSelectSpeaker(View v) {
            super(v);
            speakerNum = v.findViewById(R.id.speaker_name);
            selectedSpeaker = v.findViewById(R.id.speaker_selected);
            speakerNames = new ArrayList<>();

        }
    }
}
