package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.home.AllRelative;

import java.util.List;

public class ItemNewHomeoneDataMedicalAdapter extends RecyclerView.Adapter<ItemNewHomeoneDataMedicalAdapter.ItemNewHomeoneDataMedicalViewHoder> {
    List<ItemNewHomeoneDataMedical> mListItemNewHomeoneDataMedical;
    Context mContext1;



    public ItemNewHomeoneDataMedicalAdapter(Context context1, List<ItemNewHomeoneDataMedical> mListItemNewHomeoneDataMedical) {
        this.mContext1 = context1;
        this.mListItemNewHomeoneDataMedical = mListItemNewHomeoneDataMedical;

    }

    @NonNull
    @Override
    public ItemNewHomeoneDataMedicalViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recod_medical, parent, false);
        return new ItemNewHomeoneDataMedicalViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewHomeoneDataMedicalViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewHomeoneDataMedical ItemNewHomeoneDataMedical = mListItemNewHomeoneDataMedical.get(position);

        if(ItemNewHomeoneDataMedical==null){
            return;
        }
        //ex, kq, drug, hospital, spec, time, phonedt, namedt;
        holder.ex.setText(ItemNewHomeoneDataMedical.getEx());
        holder.kq.setText(ItemNewHomeoneDataMedical.getKq());
        holder.drug.setText(ItemNewHomeoneDataMedical.getDrug());
        holder.hospital.setText(ItemNewHomeoneDataMedical.getHospital());
        holder.spec.setText(ItemNewHomeoneDataMedical.getSpec());
        holder.time.setText(ItemNewHomeoneDataMedical.getTime());
        holder.phonedt.setText(ItemNewHomeoneDataMedical.getPhonedt());
        holder.namedt.setText(ItemNewHomeoneDataMedical.getNamedt());
        holder.name.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic());




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
        if(mListItemNewHomeoneDataMedical!=null){
            return mListItemNewHomeoneDataMedical.size();
        }
        return 0;
    }

    public class ItemNewHomeoneDataMedicalViewHoder extends RecyclerView.ViewHolder{
        private TextView ex, kq, drug, hospital, spec, time, phonedt, namedt, name;






        public ItemNewHomeoneDataMedicalViewHoder(@NonNull View itemView) {
            super(itemView);

            ex = itemView.findViewById(R.id.txt_profile_recod_medical_test);
            kq = itemView.findViewById(R.id.txt_profile_recod_medical_result);
            drug = itemView.findViewById(R.id.txt_profile_recod_medical_drug);
            //doctor = itemView.findViewById(R.id.btn_home_home1_data_allHospital_book);

            hospital = itemView.findViewById(R.id.txt_profile_recod_medical_hospital);
            spec = itemView.findViewById(R.id.txt_profile_recod_medical_spec);
            time = itemView.findViewById(R.id.txt_profile_recod_medical_time);
            phonedt = itemView.findViewById(R.id.txt_profile_recod_medical_phone);

            namedt = itemView.findViewById(R.id.txt_profile_recod_medical_nameDoctor);
            name = itemView.findViewById(R.id.txt_profile_recod_medical_name);




        }
    }
}

