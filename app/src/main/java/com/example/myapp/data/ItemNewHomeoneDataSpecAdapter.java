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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.home.AllRelative;
import com.example.myapp.home.DoctorForSpec;
import com.example.myapp.home.TakeDateTime;

import java.util.List;

public class ItemNewHomeoneDataSpecAdapter extends RecyclerView.Adapter<ItemNewHomeoneDataSpecAdapter.ItemNewHomeoneDataSpecViewHoder> {
    List<ItemNewHomeoneDataSpec> mListItemNewHomeoneDataSpec;
    Context mContext1;



    public ItemNewHomeoneDataSpecAdapter(Context context1, List<ItemNewHomeoneDataSpec> mListItemNewHomeoneDataSpec) {
        this.mContext1 = context1;
        this.mListItemNewHomeoneDataSpec = mListItemNewHomeoneDataSpec;

    }

    @NonNull
    @Override
    public ItemNewHomeoneDataSpecViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_spec, parent, false);
        return new ItemNewHomeoneDataSpecViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewHomeoneDataSpecViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewHomeoneDataSpec ItemNewHomeoneDataSpec = mListItemNewHomeoneDataSpec.get(position);

        if(ItemNewHomeoneDataSpec==null){
            return;
        }
        holder.name1.setText(ItemNewHomeoneDataSpec.getName1());
        holder.name2.setText(ItemNewHomeoneDataSpec.getName2());

        if(ItemNewHomeoneDataSpec.getName1().equals("Urology examination")){
            holder.image1.setImageResource(R.drawable.khoa1);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("On-demand examination")){
            holder.image1.setImageResource(R.drawable.khoa2);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Vaccination consultation")){
            holder.image1.setImageResource(R.drawable.khoa3);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Cardiology examination")){
            holder.image1.setImageResource(R.drawable.khoa4);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Cancer resistance test")){
            holder.image1.setImageResource(R.drawable.khoa5);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Outpatient examination/outpatient")){
            holder.image1.setImageResource(R.drawable.khoa6);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Therapeutic materials")){
            holder.image1.setImageResource(R.drawable.khoa7);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Internal examination")){
            holder.image1.setImageResource(R.drawable.khoa8);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Cancer examination")){
            holder.image1.setImageResource(R.drawable.khoa9);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Oriental medicine examination")){
            holder.image1.setImageResource(R.drawable.khoa10);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Eye exam")){
            holder.image1.setImageResource(R.drawable.khoa11);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("General examination")){
            holder.image1.setImageResource(R.drawable.khoa13);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Obstetric/Gynecological Examination")){
            holder.image1.setImageResource(R.drawable.khoa14);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Ear, nose and throat examination")){
            holder.image1.setImageResource(R.drawable.khoa15);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Gastrointestinal examination")){
            holder.image1.setImageResource(R.drawable.khoa16);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Dermatological examination")){
            holder.image1.setImageResource(R.drawable.khoa17);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Pediatric examination")){
            holder.image1.setImageResource(R.drawable.khoa18);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Breast exam")){
            holder.image1.setImageResource(R.drawable.khoa19);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Medical examination, respiratory examination")){
            holder.image1.setImageResource(R.drawable.khoa20);
        }else if(ItemNewHomeoneDataSpec.getName1().equals("Dental visit")) {
            holder.image1.setImageResource(R.drawable.khoa22);
        }

        if(ItemNewHomeoneDataSpec.getName2().equals("Urology examination")){
            holder.image2.setImageResource(R.drawable.khoa1);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("On-demand examination")){
            holder.image2.setImageResource(R.drawable.khoa2);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Vaccination consultation")){
            holder.image2.setImageResource(R.drawable.khoa3);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Cardiology examination")){
            holder.image2.setImageResource(R.drawable.khoa4);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Cancer resistance test")){
            holder.image2.setImageResource(R.drawable.khoa5);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Outpatient examination/outpatient")){
            holder.image2.setImageResource(R.drawable.khoa6);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Check the amount of therapeutic materials")){
            holder.image2.setImageResource(R.drawable.khoa7);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Internal examination")){
            holder.image2.setImageResource(R.drawable.khoa8);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Cancer examination")){
            holder.image2.setImageResource(R.drawable.khoa9);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Oriental medicine examination")){
            holder.image2.setImageResource(R.drawable.khoa10);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Eye exam")){
            holder.image2.setImageResource(R.drawable.khoa11);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("General examination")){
            holder.image2.setImageResource(R.drawable.khoa13);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Obstetric/Gynecological Examination")){
            holder.image2.setImageResource(R.drawable.khoa14);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Ear, nose and throat examination")){
            holder.image2.setImageResource(R.drawable.khoa15);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Gastrointestinal examination")){
            holder.image2.setImageResource(R.drawable.khoa16);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Dermatological examination")){
            holder.image2.setImageResource(R.drawable.khoa17);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Pediatric examination")){
            holder.image2.setImageResource(R.drawable.khoa18);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Breast exam")){
            holder.image2.setImageResource(R.drawable.khoa19);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Medical examination, respiratory examination")){
            holder.image2.setImageResource(R.drawable.khoa20);
        }else if(ItemNewHomeoneDataSpec.getName2().equals("Dental visit")) {
            holder.image2.setImageResource(R.drawable.khoa22);
        }


//        holder.image.setImageURI(Uri.parse(ItemNewHomeoneDataSpec.getImage()));
        holder.next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String check = MyApplication.getMyApplication().getDataUser().getCheckCall();
                if(check.equals("0")){
                    mContext1.startActivity(new Intent(view.getContext(), TakeDateTime.class));
                    MyApplication.getMyApplication().getDataUser().setHomeOneSpecHospital(ItemNewHomeoneDataSpec.getName1());

                }else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneSpecHospital(ItemNewHomeoneDataSpec.getName1());
                    mContext1.startActivity(new Intent(view.getContext(), DoctorForSpec.class));
                }

            }
        });
        holder.next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String check = MyApplication.getMyApplication().getDataUser().getCheckCall();
                if(check.equals("0")){
                    mContext1.startActivity(new Intent(view.getContext(), TakeDateTime.class));
                    MyApplication.getMyApplication().getDataUser().setHomeOneSpecHospital(ItemNewHomeoneDataSpec.getName2());

                }else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneSpecHospital(ItemNewHomeoneDataSpec.getName2());
                    mContext1.startActivity(new Intent(view.getContext(), DoctorForSpec.class));
                }

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
        if(mListItemNewHomeoneDataSpec!=null){
            return mListItemNewHomeoneDataSpec.size();
        }
        return 0;
    }

    public class ItemNewHomeoneDataSpecViewHoder extends RecyclerView.ViewHolder{
        private TextView name1, name2;
        private ImageView image1, image2;
        private LinearLayout next1, next2;






        public ItemNewHomeoneDataSpecViewHoder(@NonNull View itemView) {
            super(itemView);

            name1 = itemView.findViewById(R.id.home_homeOne_spec_name1);
            image1 = itemView.findViewById(R.id.home_homeOne_spec_image1);
            name2 = itemView.findViewById(R.id.home_homeOne_spec_name2);
            image2 = itemView.findViewById(R.id.home_homeOne_spec_image2);
            next1 = itemView.findViewById(R.id.ln_home_homeOne_spec_next1);
            next2 = itemView.findViewById(R.id.ln_home_homeOne_spec_next2);





        }
    }
}

