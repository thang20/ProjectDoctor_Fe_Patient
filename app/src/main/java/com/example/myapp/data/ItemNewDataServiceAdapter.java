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

import com.bumptech.glide.Glide;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.home.AllRelative;
import com.example.myapp.home.InforService;

import java.util.List;

public class ItemNewDataServiceAdapter extends RecyclerView.Adapter<ItemNewDataServiceAdapter.ItemNewDataServiceViewHoder> {
    List<ItemNewDataService> mListItemNewDataService;
    Context mContext1;



    public ItemNewDataServiceAdapter(Context context1, List<ItemNewDataService> mListItemNewDataService) {
        this.mContext1 = context1;
        this.mListItemNewDataService = mListItemNewDataService;

    }

    @NonNull
    @Override
    public ItemNewDataServiceViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_all_services, parent, false);
        return new ItemNewDataServiceViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewDataServiceViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewDataService ItemNewDataService = mListItemNewDataService.get(position);

        if(ItemNewDataService==null){
            return;
        }
        holder.content.setText(ItemNewDataService.getContent());
        holder.money.setText(ItemNewDataService.getMoney()+ " points");
        
//        holder.image.setImageURI(Uri.parse(ItemNewDataService.getImage()));
        Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDataService.getImage()).into(holder.image);
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setServiceContent(ItemNewDataService.getContent());
                MyApplication.myApplication.getDataUser().setServiceMoney(ItemNewDataService.getMoney());
                //MyApplication.myApplication.getDataUser().setServiceImage(ItemNewDataService.getImage());
                MyApplication.myApplication.getDataUser().setServicehName(ItemNewDataService.gethName());

                mContext1.startActivity(new Intent(view.getContext(), InforService.class));


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
        if(mListItemNewDataService!=null){
            return mListItemNewDataService.size();
        }
        return 0;
    }

    public class ItemNewDataServiceViewHoder extends RecyclerView.ViewHolder{
        private TextView content, money;
        private ImageView image;
        private Button next;





        public ItemNewDataServiceViewHoder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.txt_home_home8_nameservice);
            money = itemView.findViewById(R.id.txt_home_home8_point);
            image = itemView.findViewById(R.id.imv_home_home8_imageservice);
            next = itemView.findViewById(R.id.btn_home_home8_nextservice);



        }
    }
}

