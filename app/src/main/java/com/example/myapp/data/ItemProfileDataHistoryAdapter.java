package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.R;

import java.util.List;

public class ItemProfileDataHistoryAdapter extends RecyclerView.Adapter<ItemProfileDataHistoryAdapter.ItemProfileDataHistoryViewHoder> {
    List<ItemProfileDataHistory> mListItemProfileDataHistory;
    Context mContext1;


    public ItemProfileDataHistoryAdapter(Context context1, List<ItemProfileDataHistory> mListItemProfileDataHistory) {
        this.mContext1 = context1;
        this.mListItemProfileDataHistory = mListItemProfileDataHistory;

    }

    @NonNull
    @Override
    public ItemProfileDataHistoryViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_data_history, parent, false);
        return new ItemProfileDataHistoryViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemProfileDataHistoryViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemProfileDataHistory ItemProfileDataHistory = mListItemProfileDataHistory.get(position);
        if(ItemProfileDataHistory==null){

            return;
        }
        holder.remind.setText(ItemProfileDataHistory.getRemind());
        holder.time.setText(ItemProfileDataHistory.getTime() + " - " + ItemProfileDataHistory.getDate());
        holder.money.setText("Balance : " +ItemProfileDataHistory.getMoney());
        String typecheck = ItemProfileDataHistory.getType();
        if(typecheck.equals("0")){
            holder.moneyadd.setText("+"+ItemProfileDataHistory.getMoneyadd() + " .00");
            holder.type.setImageResource(R.drawable.h1);
            holder.moneyadd.setTextColor(Color.parseColor("#85D534"));

        }else if(typecheck.equals("1")){
            holder.moneyadd.setText("-"+ItemProfileDataHistory.getMoneyadd() + " .00");
            holder.type.setImageResource(R.drawable.h2);
            holder.moneyadd.setTextColor(Color.parseColor("#D82A2A"));
        }else if(typecheck.equals("2")){
            holder.moneyadd.setText("-"+ItemProfileDataHistory.getMoneyadd() + " .00");
            holder.type.setImageResource(R.drawable.h5);
            holder.moneyadd.setTextColor(Color.parseColor("#D82A2A"));
        }
        else {

        }

    }
    //    private void onClickGoToDetail(ItemProfileDataHistory ItemProfileDataHistory){
//        if(ItemProfileDataHistory==null){
//            return;
//        }
//
//        Intent intent = new Intent(mContext1, ProfileDataScheduleDetail.class);
//        Bundle bundle=new Bundle();
//        bundle.putParcelable("parcelable",ItemProfileDataHistory);
//        intent.putExtras(bundle);
//        mContext1.startActivity(intent);
//
//
//    }
    public void release(){
        mContext1 = null;
    }
    @Override
    public int getItemCount() {
        if(mListItemProfileDataHistory!=null){
            return mListItemProfileDataHistory.size();
        }
        return 0;
    }

    public class ItemProfileDataHistoryViewHoder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        private TextView remind;
        private TextView time;
        private TextView money;
        private TextView moneyadd;
        private ImageView type;



        public ItemProfileDataHistoryViewHoder(@NonNull View itemView) {
            super(itemView);
            remind = itemView.findViewById(R.id.txt_profile_data_history_remind);
            time = itemView.findViewById(R.id.txt_profile_data_history_time);
            money = itemView.findViewById(R.id.txt_profile_data_history_money);
            moneyadd = itemView.findViewById(R.id.txt_history_moneyadd);
            type = itemView.findViewById(R.id.imv_history_type);


        }
    }
}


