package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.example.myapp.New_interface.Comment_New;
import com.example.myapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ItemNewDataCommentAdapter extends RecyclerView.Adapter<ItemNewDataCommentAdapter.ItemNewDataCommentViewHoder> {
    List<ItemNewDataComment> mListItemNewDataComment;
    Context mContext1;
    Integer numberLikeNow;


    public ItemNewDataCommentAdapter(Context context1, List<ItemNewDataComment> mListItemNewDataComment) {
        this.mContext1 = context1;
        this.mListItemNewDataComment = mListItemNewDataComment;

    }

    @NonNull
    @Override
    public ItemNewDataCommentViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_data_comment, parent, false);
        return new ItemNewDataCommentViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewDataCommentViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewDataComment ItemNewDataComment = mListItemNewDataComment.get(position);

        if(ItemNewDataComment==null){
            return;
        }


        String checkFace = ItemNewDataComment.getImageFace();
        String checkImage = ItemNewDataComment.getImage();

        if(checkFace.equals("")){
            holder.face.setImageResource(R.drawable.profile);
        }
        else {
//            holder.face.setImageURI(Uri.parse(ItemNewDataComment.getImageFace()));
            Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataComment.getImageFace()).into(holder.face);
        }

        String dateCheck = ItemNewDataComment.getDate();
        if(dateCheck.equals(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()))){
            Calendar cal = Calendar.getInstance();
            String timepost = ItemNewDataComment.getTime();
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

            holder.date.setText(ItemNewDataComment.getDate());
            holder.time.setText(ItemNewDataComment.getTime());

        }


        holder.name.setText(ItemNewDataComment.getFullname());

        if(checkImage.equals("0")){
            holder.image.getLayoutParams().height = 0;
            holder.image.getLayoutParams().width = 0;
        }
        else {
//            holder.image.setImageURI(Uri.parse(ItemNewDataComment.getImage()));
            Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataComment.getImage()).into(holder.image);

        }
        holder.content.setText(ItemNewDataComment.getContent());


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
        if(mListItemNewDataComment!=null){
            return mListItemNewDataComment.size();
        }
        return 0;
    }

    public class ItemNewDataCommentViewHoder extends RecyclerView.ViewHolder{
        private ImageView face, image;
        private TextView name, time, date,  content;





        public ItemNewDataCommentViewHoder(@NonNull View itemView) {
            super(itemView);
            face = itemView.findViewById(R.id.rcv_item_new_item_comment_imageFace);
            image = itemView.findViewById(R.id.imv_item_new_item_comment_image);
            name = itemView.findViewById(R.id.txt_item_new_item_comment_fullName);
            time = itemView.findViewById(R.id.txt__item_new_item_comment_time);
            date = itemView.findViewById(R.id.txt__item_new_item_comment_date);
            content = itemView.findViewById(R.id.txt_item_new_item_comment_content);



        }
    }
}
