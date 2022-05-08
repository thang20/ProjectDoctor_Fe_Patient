package com.example.myapp.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.myapp.MyApplication;
import com.example.myapp.New_interface.Comment_New;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.profile_interface.ProfileDataSchedule;
import com.example.myapp.profile_interface.UpdateProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemNewDataPostAdapter extends RecyclerView.Adapter<ItemNewDataPostAdapter.ItemNewDataPostViewHoder> {
    List<ItemNewDataPost> mListItemNewDataPost;
    Context mContext1;
    Integer numberLikeNow;


    public ItemNewDataPostAdapter(Context context1, List<ItemNewDataPost> mListItemNewDataPost) {
        this.mContext1 = context1;
        this.mListItemNewDataPost = mListItemNewDataPost;

    }

    @NonNull
    @Override
    public ItemNewDataPostViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_data_post, parent, false);
        return new ItemNewDataPostViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewDataPostViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewDataPost ItemNewDataPost = mListItemNewDataPost.get(position);
        MyApplication.myApplication.getDataUser().setK(1);
        if(ItemNewDataPost==null){

            return;
        }
        String checkFace = ItemNewDataPost.getImageFace();
        String checkImage = ItemNewDataPost.getImage();

        if(checkFace.equals("")){
            holder.face.setImageResource(R.drawable.profile);
        }
        else {
            //holder.face.setImageURI(Uri.parse(ItemNewDataPost.getImageFace()));
            //new UpdateProfile.LoadImage1().execute(MyApplication.getMyApplication().getDataUser().getAPI() + MyApplication.getMyApplication().getDataUser().getByteArrayImageStatic());
            Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataPost.getImageFace()).into(holder.face);


        }

        String dateCheck = ItemNewDataPost.getDate();
        if(dateCheck.equals(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date()))){
            Calendar cal = Calendar.getInstance();
            String timepost = ItemNewDataPost.getTime();
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

            holder.date.setText(ItemNewDataPost.getDate());
            holder.time.setText(ItemNewDataPost.getTime());

        }


        String likec = ItemNewDataPost.getLike();
        numberLikeNow = Integer.parseInt(ItemNewDataPost.getNumberlike());
        if(likec.equals("1")){
            holder.like.setColorFilter(Color.parseColor("#2BC4BF"));
            holder.likeText.setTextColor(Color.parseColor("#2BC4BF"));
            holder.likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }else {
            MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
            holder.like.setColorFilter(Color.parseColor("#656362"));
            holder.likeText.setTextColor(Color.parseColor("#656362"));
            holder.likeText.setText(String.valueOf(numberLikeNow) + " Like");
        }


        holder.name.setText(ItemNewDataPost.getFullname());
        holder.likeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String likec = ItemNewDataPost.getLike();
                if(likec.equals("0")){
                    ItemNewDataPost.setLike("1");
                }else {
                    ItemNewDataPost.setLike("0");
                }
                if(likec.equals("0")){
                    holder.like.setColorFilter(Color.parseColor("#2BC4BF"));
                    holder.likeText.setTextColor(Color.parseColor("#2BC4BF"));
                    ///////////////

                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                        jsonObject = new JSONObject();
                        jsonObject.put("email", email);
                        jsonObject.put("likec", likec);
                        jsonObject.put("idpost", ItemNewDataPost.getIdpost());
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.like(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();

                                Toast.makeText(mContext1, "thanks for the contribution", Toast.LENGTH_LONG).show();
                                holder.likeText.setText(String.valueOf(notice) + " Like");
                                ItemNewDataPost.setNumberlike(notice);



                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(mContext1, "Fail to call API", Toast.LENGTH_LONG).show();

                        }
                    });



                }else {
                    MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
                    holder.like.setColorFilter(Color.parseColor("#656362"));
                    holder.likeText.setTextColor(Color.parseColor("#656362"));
                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                        jsonObject = new JSONObject();
                        jsonObject.put("email", email);
                        jsonObject.put("likec", likec);
                        jsonObject.put("idpost", ItemNewDataPost.getIdpost());
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.like(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();

                                Toast.makeText(mContext1, "thanks for the contribution", Toast.LENGTH_LONG).show();

                                holder.likeText.setText(String.valueOf(notice) + " Like");
                                ItemNewDataPost.setNumberlike(notice);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(mContext1, "Fail to call API", Toast.LENGTH_LONG).show();

                        }
                    });

                }

//                numberLikeNow = Integer.parseInt(ItemNewDataPost.getNumberlike());
//
//                MyApplication.myApplication.getDataUser().setK( MyApplication.myApplication.getDataUser().getK() + 1);
//
//
//                if(MyApplication.myApplication.getDataUser().getK()%2==0) {
//                    MyApplication.myApplication.getDataUser().setLIKEORDIS(1);
//                    numberLikeNow  = numberLikeNow + 1;
//                    holder.like.setColorFilter(Color.parseColor("#2BC4BF"));
//                    holder.likeText.setTextColor(Color.parseColor("#2BC4BF"));
//                    holder.likeText.setText(String.valueOf(numberLikeNow) + " Like");
//                }else {
//                    MyApplication.myApplication.getDataUser().setLIKEORDIS(0);
//                    holder.like.setColorFilter(Color.parseColor("#656362"));
//                    holder.likeText.setTextColor(Color.parseColor("#656362"));
//                    holder.likeText.setText(String.valueOf(numberLikeNow) + " Like");
//
//                }
            }
        });
        if(checkImage.equals("0")){
            holder.image.getLayoutParams().height = 0;
            holder.image.getLayoutParams().width = 0;
        }
        else {
//            holder.image.setImageURI(Uri.parse(ItemNewDataPost.getImage()));
            Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataPost.getImage()).into(holder.image);
            Log.d("444", MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataPost.getImage());

        }
        holder.content.setText(ItemNewDataPost.getContent());
        holder.commentAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MyApplication.getMyApplication().getDataUser().setCommentIDPost(ItemNewDataPost.getIdpost());
                MyApplication.getMyApplication().getDataUser().setCommentIDPeople(ItemNewDataPost.getIdpeople());

                MyApplication.getMyApplication().getDataUser().setCommentImageFace(ItemNewDataPost.getImageFace());
                MyApplication.getMyApplication().getDataUser().setCommentTime(ItemNewDataPost.getTime());
                MyApplication.getMyApplication().getDataUser().setCommentDate(ItemNewDataPost.getDate());
                MyApplication.getMyApplication().getDataUser().setCommentFullName(ItemNewDataPost.getFullname());
                MyApplication.getMyApplication().getDataUser().setCommentImage(ItemNewDataPost.getImage());
                MyApplication.getMyApplication().getDataUser().setCommentNumberLike(ItemNewDataPost.getNumberlike());
                MyApplication.getMyApplication().getDataUser().setCommentContent(ItemNewDataPost.getContent());
                MyApplication.getMyApplication().getDataUser().setLike(ItemNewDataPost.getLike());

                mContext1.startActivity(new Intent(view.getContext(), Comment_New.class));


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
        if(mListItemNewDataPost!=null){
            return mListItemNewDataPost.size();
        }
        return 0;
    }

    public class ItemNewDataPostViewHoder extends RecyclerView.ViewHolder{
        private ImageView face, image, like;
        private TextView name, time, date, likeText, content;
        private LinearLayout likeAll, commentAll;




        public ItemNewDataPostViewHoder(@NonNull View itemView) {
            super(itemView);
            face = itemView.findViewById(R.id.rcv_item_new_imageFace);
            image = itemView.findViewById(R.id.imv_item_new_image);
            like = itemView.findViewById(R.id.imv_item_new_like);
            name = itemView.findViewById(R.id.txt_item_new_fullName);
            time = itemView.findViewById(R.id.txt_item_new_time);
            date = itemView.findViewById(R.id.txt_item_new_date);
            likeText = itemView.findViewById(R.id.txt_item_new_liketext);
            content = itemView.findViewById(R.id.txt_item_new_content);
            likeAll = itemView.findViewById(R.id.ln_item_new_likeAll);
            commentAll = itemView.findViewById(R.id.ln_item_new_commetAll);


        }
    }



}
