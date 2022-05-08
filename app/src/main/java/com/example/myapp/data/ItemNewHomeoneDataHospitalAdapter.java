package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.home.AllHospital;
import com.example.myapp.home.AllRelative;
import com.example.myapp.hospital.LocationHospital;

import java.util.List;

public class ItemNewHomeoneDataHospitalAdapter extends RecyclerView.Adapter<ItemNewHomeoneDataHospitalAdapter.ItemNewHomeoneDataHospitalViewHoder> {
    List<ItemNewHomeoneDataHospital> mListItemNewHomeoneDataHospital;
    Context mContext1;



    public ItemNewHomeoneDataHospitalAdapter(Context context1, List<ItemNewHomeoneDataHospital> mListItemNewHomeoneDataHospital) {
        this.mContext1 = context1;
        this.mListItemNewHomeoneDataHospital = mListItemNewHomeoneDataHospital;

    }

    @NonNull
    @Override
    public ItemNewHomeoneDataHospitalViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_hospital_data, parent, false);
        return new ItemNewHomeoneDataHospitalViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewHomeoneDataHospitalViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewHomeoneDataHospital ItemNewHomeoneDataHospital = mListItemNewHomeoneDataHospital.get(position);

        if(ItemNewHomeoneDataHospital==null){
            return;
        }
        holder.name.setText(ItemNewHomeoneDataHospital.getName());
        holder.address.setText(ItemNewHomeoneDataHospital.getAddress());
//        holder.image.setImageURI(Uri.parse(ItemNewHomeoneDataHospital.getImage()));
        Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewHomeoneDataHospital.getImage()).into(holder.image);
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setHomeOneNameHospital(ItemNewHomeoneDataHospital.getName());
                mContext1.startActivity(new Intent(view.getContext(), AllRelative.class));


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
        if(mListItemNewHomeoneDataHospital!=null){
            return mListItemNewHomeoneDataHospital.size();
        }
        return 0;
    }

    public class ItemNewHomeoneDataHospitalViewHoder extends RecyclerView.ViewHolder{
        private TextView name, address;
        private ImageView image;
        private Button next;





        public ItemNewHomeoneDataHospitalViewHoder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_home_home1_data_allHospital_name);
            address = itemView.findViewById(R.id.txt_home_home1_data_allHospital_address);
            image = itemView.findViewById(R.id.imv_home_home1_data_allHospital_image);
            next = itemView.findViewById(R.id.btn_home_home1_data_allHospital_book);



        }
    }
}
