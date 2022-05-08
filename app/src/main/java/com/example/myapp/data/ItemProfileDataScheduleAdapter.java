package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapp.profile_interface.ProfileDataRelativeDetail;
import com.example.myapp.profile_interface.ProfileDataSchedule;
import com.example.myapp.profile_interface.ProfileDataScheduleAdd;
import com.example.myapp.profile_interface.UpdateProfile;

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

public class ItemProfileDataScheduleAdapter extends RecyclerView.Adapter<ItemProfileDataScheduleAdapter.ItemProfileDataScheduleViewHoder> {
    List<ItemProfileDataSchedule> mListItemProfileDataSchedule;
    Context mContext1;
    ProgressDialog progressDialog;



    public ItemProfileDataScheduleAdapter(Context context1, List<ItemProfileDataSchedule> mListItemProfileDataSchedule) {
        this.mContext1 = context1;
        this.mListItemProfileDataSchedule = mListItemProfileDataSchedule;

    }

    @NonNull
    @Override
    public ItemProfileDataScheduleViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_data_schedule, parent, false);
        return new ItemProfileDataScheduleViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemProfileDataScheduleViewHoder holder, @SuppressLint("RecyclerView") int position) {
    ItemProfileDataSchedule itemProfileDataSchedule = mListItemProfileDataSchedule.get(position);
    if(itemProfileDataSchedule==null){

        return;
    }
    holder.remind.setText(itemProfileDataSchedule.getRemind());
    holder.time.setText(itemProfileDataSchedule.getTime());
    holder.date.setText(itemProfileDataSchedule.getDate());
    holder.linearLayout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            progressDialog = new ProgressDialog(mContext1);
            progressDialog.show();
            progressDialog.setContentView(R.layout.process_dialog);

            progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

            JSONObject jsonObject = new JSONObject();
            String jsonStr = null;
            try {
                String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                jsonObject = new JSONObject();
                jsonObject.put("email", email);
                jsonObject.put("position", String.valueOf(position));
                jsonStr = jsonObject.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            RequestBody body =
                    RequestBody.create(MediaType.parse("text/plain"), jsonStr);

            Apiservice.apiservice.profilescheduledelete(body).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    String notice = null;

                    try {
                        notice = response.body().string();
                        if(notice.equals("success")){
                            Toast.makeText(view.getContext(), "Success", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            mContext1.startActivity(new Intent(view.getContext(), ProfileDataSchedule.class));

                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(view.getContext(), "Fail to Delete", Toast.LENGTH_LONG).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(view.getContext(), "Fail to call API", Toast.LENGTH_LONG).show();

                }
            });
        }
    });
    }
//    private void onClickGoToDetail(ItemProfileDataSchedule itemProfileDataSchedule){
//        if(itemProfileDataSchedule==null){
//            return;
//        }
//
//        Intent intent = new Intent(mContext1, ProfileDataScheduleDetail.class);
//        Bundle bundle=new Bundle();
//        bundle.putParcelable("parcelable",itemProfileDataSchedule);
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
        if(mListItemProfileDataSchedule!=null){
            return mListItemProfileDataSchedule.size();
        }
        return 0;
    }

    public class ItemProfileDataScheduleViewHoder extends RecyclerView.ViewHolder{
        private LinearLayout linearLayout;

        private TextView remind;
        private TextView time;
        private TextView date;


        public ItemProfileDataScheduleViewHoder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.ln_profile_data_schedule);
            remind = itemView.findViewById(R.id.txt_profile_data_schedule_remind);
            time = itemView.findViewById(R.id.txt_profile_data_schedule_time);
            date = itemView.findViewById(R.id.txt_profile_data_schedule_date);
        }
    }
}
