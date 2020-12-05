package com.example.a207_demo.contactSystem;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a207_demo.R;
import com.example.a207_demo.messageSystem.MsgActivity;

import java.util.List;

/**
 * ContactAdapter
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.VHContact> {

    private Context context;
    private List<Contact> contactsList;

    /**
     * ContactAdapter
     *
     * @param context
     * @param contactsList
     */
    public ContactAdapter(Context context, List<Contact> contactsList) {
        this.context = context;
        this.contactsList = contactsList;
    }

    /**
     * onCreateViewHolder
     *
     * @param parent   @NonNull ViewGroup parent
     * @param viewType int viewType
     * @return VHContact
     */
    @NonNull
    @Override
    public VHContact onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        VHContact holder = new ContactAdapter.VHContact(v);
        setClickContactListener(holder);
        return holder;
    }

    /**
     * setClickContactListener
     *
     * @param holder ContactAdapter.VHContact holder
     */
    public void setClickContactListener(final ContactAdapter.VHContact holder) {
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MsgActivity.class);
                context.startActivity(intent);
            }
        });
    }

    /**
     * onBindViewHolder
     *
     * @param holder   @NonNull VHContact holder
     * @param position final int position
     */
    @Override
    public void onBindViewHolder(@NonNull VHContact holder, final int position) {

        Contact contact = contactsList.get(position);
        holder.contactName.setText(contact.getName());
        Glide.with(context).load(contact.getImageId()).into(holder.contactImage);

    }

    /**
     * getItemCount
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return contactsList.isEmpty() ? 0 : contactsList.size();
    }

    /**
     * VHContact is a static class VHContact extends RecyclerView.ViewHolder
     */
    static class VHContact extends RecyclerView.ViewHolder {
        ImageView contactImage;
        TextView contactName;
        View mView;

        /**
         * VHContact constructor
         *
         * @param v View
         */
        public VHContact(View v) {
            super(v);
            mView = v;
            contactImage = v.findViewById(R.id.contact_image);
            contactName = v.findViewById(R.id.contact_name);
            ;
        }
    }
}
