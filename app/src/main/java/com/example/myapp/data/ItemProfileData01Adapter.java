package com.example.myapp.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.profile_interface.ProfileData;

import java.net.InterfaceAddress;
import java.util.List;

public class ItemProfileData01Adapter extends RecyclerView.Adapter<ItemProfileData01Adapter.ItemProfileData01ViewHoder> {
    List<ItemProfileData01> mListItemProfileData01;
    Context mContext;


    public ItemProfileData01Adapter(Context context, List<ItemProfileData01> mListItemProfileData01) {
        this.mContext = context;
        this.mListItemProfileData01 = mListItemProfileData01;

    }

    @NonNull
    @Override
    public ItemProfileData01ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_data01, parent, false);
        return new ItemProfileData01ViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemProfileData01ViewHoder holder, int position) {
    ItemProfileData01 itemProfileData01 = mListItemProfileData01.get(position);
    if(itemProfileData01==null){

        return;
    }
    holder.imageView1.setImageResource(itemProfileData01.getResourceId1());
    holder.imageView2.setImageResource(itemProfileData01.getResourceId2());
    holder.txtData.setText(itemProfileData01.getName());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onClickGoToDetail(itemProfileData01);
        }
    });
    }
    private void onClickGoToDetail(ItemProfileData01 itemProfileData01){
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_profileData", itemProfileData01);
        Intent intent = new Intent(mContext, ProfileData.class).putExtras(bundle);
        mContext.startActivity(intent);
    }
    public void release(){
        mContext = null;
    }
    @Override
    public int getItemCount() {
        if(mListItemProfileData01!=null){
            return mListItemProfileData01.size();
        }
        return 0;
    }

    public class ItemProfileData01ViewHoder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;
        private ImageView imageView1;
        private ImageView imageView2;
        private TextView txtData;

        public ItemProfileData01ViewHoder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ln_profile_data);
            imageView1 = itemView.findViewById(R.id.imv_profile_data1_avt);
            imageView2 = itemView.findViewById(R.id.imv_profile_data1_next);
            txtData = itemView.findViewById(R.id.txt_profile_data1_data);
        }
    }
}
