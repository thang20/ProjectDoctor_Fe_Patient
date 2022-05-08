package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.hospital.LocationHospital;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemNewDataHospitalAdapter extends RecyclerView.Adapter<ItemNewDataHospitalAdapter.ItemNewDataHospitalViewHoder> {
    List<ItemNewDataHospital> mListItemNewDataHospital;
    Context mContext1;



    public ItemNewDataHospitalAdapter(Context context1, List<ItemNewDataHospital> mListItemNewDataHospital) {
        this.mContext1 = context1;
        this.mListItemNewDataHospital = mListItemNewDataHospital;

    }

    @NonNull
    @Override
    public ItemNewDataHospitalViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hospital_data, parent, false);
        return new ItemNewDataHospitalViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewDataHospitalViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewDataHospital ItemNewDataHospital = mListItemNewDataHospital.get(position);

        if(ItemNewDataHospital==null){
            return;
        }
        holder.name.setText(ItemNewDataHospital.getName());
        holder.address.setText(ItemNewDataHospital.getAddress());
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext1.startActivity(new Intent(view.getContext(), LocationHospital.class));
                MyApplication.getMyApplication().getDataUser().setLocation(ItemNewDataHospital.getAddress());
            }
        });



//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                Log.d("100", String.valueOf(position));
//
//                JSONObject jsonObject = new JSONObject();
//                String jsonStr = null;
//                try {
//                    String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
//                    jsonObject = new JSONObject();
//                    jsonObject.put("email", email);
//                    jsonObject.put("position", String.valueOf(position));
//                    jsonStr = jsonObject.toString();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                RequestBody body =
//                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);
//
//                Apiservice.apiservice.profilescheduledelete(body).enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                        String notice = null;
//
//                        try {
//                            notice = response.body().string();
//                            if(notice.equals("success")){
//                                Toast.makeText(view.getContext(), "Success", Toast.LENGTH_LONG).show();
//                                mContext1.startActivity(new Intent(view.getContext(), ProfileDataSchedule.class));
//
//                            }else {
//                                Toast.makeText(view.getContext(), "Fail to Delete", Toast.LENGTH_LONG).show();
//                            }
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(view.getContext(), "Fail to call API", Toast.LENGTH_LONG).show();
//
//                    }
//                });
//            }
//        });
    }

    public void release(){
        mContext1 = null;
    }
    @Override
    public int getItemCount() {
        if(mListItemNewDataHospital!=null){
            return mListItemNewDataHospital.size();
        }
        return 0;
    }

    public class ItemNewDataHospitalViewHoder extends RecyclerView.ViewHolder{
        private TextView name, address;
        private ImageView next;





        public ItemNewDataHospitalViewHoder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_hospital_name);
            address = itemView.findViewById(R.id.txt_hospital_address);
            next = itemView.findViewById(R.id.imv_hospital_next);



        }
    }
}
