package com.example.a207_demo.messageSystem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a207_demo.R;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.VHMsg> {

    private Context context;
    private List<Msg> mMsgList;



    public MsgAdapter(Context context, List<Msg> mMsgList) {
        this.context = context;
        this.mMsgList = mMsgList;
    }

    @NonNull
    @Override
    public VHMsg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg,parent,false);
        VHMsg vh = new VHMsg(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VHMsg holder, int position) {
        Msg msg = mMsgList.get(position);

        if(msg.getType() == Msg.TYPE_RECEIVED) {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        }else{
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.isEmpty() ? 0 : mMsgList.size();
    }

    static class VHMsg extends RecyclerView.ViewHolder {
        private RelativeLayout leftLayout;
        private RelativeLayout rightLayout;
        private TextView leftMsg;
        private TextView rightMsg;
        View mView;

        public VHMsg(View view) {
            super(view);
            mView = view;
            leftLayout = view.findViewById(R.id.layout_left);
            rightLayout = view.findViewById(R.id.layout_right);
            leftMsg = view.findViewById(R.id.msg_left);
            rightMsg = view.findViewById(R.id.msg_right);
        }

    }

}
