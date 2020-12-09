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

import java.util.ArrayList;
import java.util.List;

/**
 * MsgAdapter
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.VHMsg> {

    private Context context;
    private ArrayList<ArrayList<String>> conversationList;
    private String myID;


    /**
     * MsgAdapter constructor
     *
     * @param context
     * @param conversationList
     */
    public MsgAdapter(Context context, ArrayList<ArrayList<String>> conversationList, String myID) {
        this.context = context;
        this.conversationList = conversationList;
        this.myID = myID;
    }

    /**
     * onCreateViewHolder
     *
     * @param parent   NonNull ViewGroup parent
     * @param viewType int
     * @return VHMsg
     */
    @NonNull
    @Override
    public VHMsg onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_msg, parent, false);
        VHMsg vh = new VHMsg(view);
        return vh;
    }

    /**
     * onBindViewHolder
     *
     * @param holder   NonNull VHMsg holder
     * @param position int position
     */
    @Override
    public void onBindViewHolder(@NonNull VHMsg holder, int position) {
        ArrayList<String> conversation = conversationList.get(position);
        System.out.println("listt" + conversation);
        String ID = conversation.get(0);
        String msg = conversation.get(1);
        System.out.println("iddd"+ID);
        System.out.println("msgg"+msg);
//        if (msg.getType() == Msg.TYPE_RECEIVED) {
//            holder.leftLayout.setVisibility(View.VISIBLE);
//            holder.rightLayout.setVisibility(View.GONE);
//            holder.leftMsg.setText(msg.getContent());
//        } else {
//            holder.rightLayout.setVisibility(View.VISIBLE);
//            holder.leftLayout.setVisibility(View.GONE);
//            holder.rightMsg.setText(msg.getContent());
//        }
        if(ID.equals(myID)){
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg);
        }else {
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg);
        }
    }

    /**
     * getItemCount
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return conversationList.isEmpty() ? 0 : conversationList.size();
    }

    /**
     * VHMsg
     */
    static class VHMsg extends RecyclerView.ViewHolder {
//        private RelativeLayout leftLayout;
//        private RelativeLayout rightLayout;
//        private TextView leftMsg;
//        private TextView rightMsg;
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;
        View mView;

        /**
         * VHMsg
         *
         * @param view View
         */
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
