package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.home.ResponsUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemNewDataQADoctorAdapter extends RecyclerView.Adapter<ItemNewDataQADoctorAdapter.ItemNewDataQADoctorViewHoder> {
    List<ItemNewDataQADoctor> mListItemNewDataQADoctor;
    Context mContext1;
    Integer numberLikeNow;


    public ItemNewDataQADoctorAdapter(Context context1, List<ItemNewDataQADoctor> mListItemNewDataQADoctor) {
        this.mContext1 = context1;
        this.mListItemNewDataQADoctor = mListItemNewDataQADoctor;

    }

    @NonNull
    @Override
    public ItemNewDataQADoctorViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_data_qadoctor, parent, false);
        return new ItemNewDataQADoctorViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewDataQADoctorViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewDataQADoctor ItemNewDataQADoctor = mListItemNewDataQADoctor.get(position);
        MyApplication.myApplication.getDataUser().setK(1);
        if(ItemNewDataQADoctor==null){

            return;
        }
        String checkFace = ItemNewDataQADoctor.getImageFace();


        if(checkFace.equals("")){
            holder.face.setImageResource(R.drawable.profile);
        }
        else {
            //holder.face.setImageURI(Uri.parse(ItemNewDataQADoctor.getImageFace()));
            //new UpdateProfile.LoadImage1().execute(MyApplication.getMyApplication().getDataUser().getAPI() + MyApplication.getMyApplication().getDataUser().getByteArrayImageStatic());
            Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataQADoctor.getImageFace()).into(holder.face);


        }

        String dateCheck = ItemNewDataQADoctor.getDate();
        if(dateCheck.equals(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()))){
            Calendar cal = Calendar.getInstance();
            String timepost = ItemNewDataQADoctor.getTime();
            String[] parts = timepost.split(":");
            int hourp = Integer.parseInt(parts[0]);
            int mip = Integer.parseInt(parts[1]);

            int minute = cal.get(Calendar.MINUTE);
            int hourofday = cal.get(Calendar.HOUR_OF_DAY);
            if(hourp!=hourofday){
                int changeH = hourofday - hourp;
                holder.time.setText(String.valueOf(changeH) + " hour ago");

            }else {
                int changeP = minute - mip;
                if(changeP<=1){
                    holder.time.setText("now");
                }else {

                    holder.time.setText(String.valueOf(changeP) + " minute ago");
                }
            }

        }else {

            holder.date.setText(ItemNewDataQADoctor.getDate());
            holder.time.setText(ItemNewDataQADoctor.getTime());

        }


        holder.name.setText(ItemNewDataQADoctor.getFullname());


        holder.content.setText(ItemNewDataQADoctor.getContent());
        holder.commentAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MyApplication.getMyApplication().getDataUser().setCommentIDPost(ItemNewDataQADoctor.getIdpost());
                MyApplication.getMyApplication().getDataUser().setCommentIDPeople(ItemNewDataQADoctor.getIdpeople());
//
                MyApplication.getMyApplication().getDataUser().setCommentImageFace(ItemNewDataQADoctor.getImageFace());
                MyApplication.getMyApplication().getDataUser().setCommentTime(ItemNewDataQADoctor.getTime());
                MyApplication.getMyApplication().getDataUser().setCommentDate(ItemNewDataQADoctor.getDate());
                MyApplication.getMyApplication().getDataUser().setCommentFullName(ItemNewDataQADoctor.getFullname());

                MyApplication.getMyApplication().getDataUser().setCommentContent(ItemNewDataQADoctor.getContent());

                mContext1.startActivity(new Intent(view.getContext(), ResponsUser.class));

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
        if(mListItemNewDataQADoctor!=null){
            return mListItemNewDataQADoctor.size();
        }
        return 0;
    }

    public class ItemNewDataQADoctorViewHoder extends RecyclerView.ViewHolder{
        private ImageView face;
        private TextView name, time, date, content;
        private LinearLayout commentAll;




        public ItemNewDataQADoctorViewHoder(@NonNull View itemView) {
            super(itemView);
            face = itemView.findViewById(R.id.rcv_item_new_imageFace_doctorqa);
            name = itemView.findViewById(R.id.txt_item_new_fullName_doctorqa);
            time = itemView.findViewById(R.id.txt_item_new_time_doctorqa);
            date = itemView.findViewById(R.id.txt_item_new_date_doctorqa);
            content = itemView.findViewById(R.id.txt_item_new_content_doctorqa);
            commentAll = itemView.findViewById(R.id.ln_item_new_commetAll_doctorqa);


        }
    }



}

