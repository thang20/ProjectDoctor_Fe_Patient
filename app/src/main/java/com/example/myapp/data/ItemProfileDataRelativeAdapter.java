package com.example.myapp.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;
import com.example.myapp.profile_interface.ProfileDataRelativeDetail;

import java.util.List;

public class ItemProfileDataRelativeAdapter extends RecyclerView.Adapter<ItemProfileDataRelativeAdapter.ItemProfileDataRelativeViewHoder> {
    List<ItemProfileDataRelative> mListItemProfileDataRelative;
    Context mContext1;


    public ItemProfileDataRelativeAdapter(Context context1, List<ItemProfileDataRelative> mListItemProfileDataRelative) {
        this.mContext1 = context1;
        this.mListItemProfileDataRelative = mListItemProfileDataRelative;

    }

    @NonNull
    @Override
    public ItemProfileDataRelativeViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_data_relative, parent, false);
        return new ItemProfileDataRelativeViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemProfileDataRelativeViewHoder holder, int position) {
    ItemProfileDataRelative itemProfileDataRelative = mListItemProfileDataRelative.get(position);
    if(itemProfileDataRelative==null){

        return;
    }
    holder.name.setText(itemProfileDataRelative.getName());
    holder.dateOfBirth.setText(itemProfileDataRelative.getDateOfBirth());
    holder.phone.setText(itemProfileDataRelative.getPhone());
    holder.address.setText(itemProfileDataRelative.getAddress());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            onClickGoToDetail(itemProfileDataRelative);
        }
    });
    }
    private void onClickGoToDetail(ItemProfileDataRelative itemProfileDataRelative){
        if(itemProfileDataRelative==null){
            return;
        }

        Intent intent = new Intent(mContext1, ProfileDataRelativeDetail.class);
        Bundle bundle=new Bundle();
        bundle.putParcelable("parcelable",itemProfileDataRelative);
        intent.putExtras(bundle);
        mContext1.startActivity(intent);


    }
    public void release(){
        mContext1 = null;
    }
    @Override
    public int getItemCount() {
        if(mListItemProfileDataRelative!=null){
            return mListItemProfileDataRelative.size();
        }
        return 0;
    }

    public class ItemProfileDataRelativeViewHoder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        private TextView name;
        private TextView dateOfBirth;
        private TextView phone;
        private TextView address;


        public ItemProfileDataRelativeViewHoder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ln_profile_data_relative);
            name = itemView.findViewById(R.id.txt_profile_data_relative_name);
            dateOfBirth = itemView.findViewById(R.id.txt_profile_data_relative_dateofbirth);
            phone = itemView.findViewById(R.id.txt_profile_data_relative_phone);
            address = itemView.findViewById(R.id.txt_profile_data_relative_address);
        }
    }
}
