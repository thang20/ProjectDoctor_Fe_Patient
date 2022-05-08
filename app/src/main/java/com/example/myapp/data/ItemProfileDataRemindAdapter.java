package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.profile_interface.ProfileDataSchedule;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemProfileDataRemindAdapter extends RecyclerView.Adapter<ItemProfileDataRemindAdapter.ItemProfileDataRemindViewHoder> {
    List<ItemProfileDataRemind> mListItemProfileDataRemind;
    Context mContext1;


    public ItemProfileDataRemindAdapter(Context context1, List<ItemProfileDataRemind> mListItemProfileDataRemind) {
        this.mContext1 = context1;
        this.mListItemProfileDataRemind = mListItemProfileDataRemind;

    }

    @NonNull
    @Override
    public ItemProfileDataRemindViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_data_remind, parent, false);
        return new ItemProfileDataRemindViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemProfileDataRemindViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemProfileDataRemind ItemProfileDataRemind = mListItemProfileDataRemind.get(position);
        if(ItemProfileDataRemind==null){

            return;
        }
        holder.remind.setText(ItemProfileDataRemind.getRemind());
        holder.time.setText(ItemProfileDataRemind.getTime());
        holder.date.setText(ItemProfileDataRemind.getDate());

    }
    //    private void onClickGoToDetail(ItemProfileDataRemind ItemProfileDataRemind){
//        if(ItemProfileDataRemind==null){
//            return;
//        }
//
//        Intent intent = new Intent(mContext1, ProfileDataScheduleDetail.class);
//        Bundle bundle=new Bundle();
//        bundle.putParcelable("parcelable",ItemProfileDataRemind);
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
        if(mListItemProfileDataRemind!=null){
            return mListItemProfileDataRemind.size();
        }
        return 0;
    }

    public class ItemProfileDataRemindViewHoder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        private TextView remind;
        private TextView time;
        private TextView date;


        public ItemProfileDataRemindViewHoder(@NonNull View itemView) {
            super(itemView);
            remind = itemView.findViewById(R.id.txt_profile_data_remind_remind);
            time = itemView.findViewById(R.id.txt_profile_data_remind_time);
            date = itemView.findViewById(R.id.txt_profile_data_remind_date);
        }
    }
}

