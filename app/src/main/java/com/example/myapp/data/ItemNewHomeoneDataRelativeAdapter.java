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
import com.example.myapp.home.Specialist;
import com.example.myapp.home.TestAtHomeCheckAddress;

import java.util.List;

public class ItemNewHomeoneDataRelativeAdapter extends RecyclerView.Adapter<ItemNewHomeoneDataRelativeAdapter.ItemNewHomeoneDataRelativeViewHoder> {
    List<ItemNewHomeoneDataRelative> mListItemNewHomeoneDataRelative;
    Context mContext1;



    public ItemNewHomeoneDataRelativeAdapter(Context context1, List<ItemNewHomeoneDataRelative> mListItemNewHomeoneDataRelative) {
        this.mContext1 = context1;
        this.mListItemNewHomeoneDataRelative = mListItemNewHomeoneDataRelative;

    }

    @NonNull
    @Override
    public ItemNewHomeoneDataRelativeViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_relative_data_homeone, parent, false);
        return new ItemNewHomeoneDataRelativeViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewHomeoneDataRelativeViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewHomeoneDataRelative ItemNewHomeoneDataRelative = mListItemNewHomeoneDataRelative.get(position);

        if(ItemNewHomeoneDataRelative==null){
            return;
        }
        holder.name.setText(ItemNewHomeoneDataRelative.getName());
        holder.gender.setText(ItemNewHomeoneDataRelative.getGender());
        holder.age.setText(ItemNewHomeoneDataRelative.getAge());
        holder.relative.setText(ItemNewHomeoneDataRelative.getRelative());
        holder.phone.setText(ItemNewHomeoneDataRelative.getPhone());
        holder.address.setText(ItemNewHomeoneDataRelative.getAddress());

//        holder.image.setImageURI(Uri.parse(ItemNewHomeoneDataRelative.getImage()));
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( MyApplication.myApplication.getDataUser().getCheckTestAtHome().equals("1")){
                    MyApplication.getMyApplication().getDataUser().setHomeOneNameUser(ItemNewHomeoneDataRelative.getName());
                    MyApplication.getMyApplication().getDataUser().setHomeOneAgeUser(ItemNewHomeoneDataRelative.getAge());
                    MyApplication.getMyApplication().getDataUser().setHomeOneGenderUser(ItemNewHomeoneDataRelative.getGender());
                    MyApplication.getMyApplication().getDataUser().setHomeOnePhoneUser(ItemNewHomeoneDataRelative.getPhone());
                    MyApplication.getMyApplication().getDataUser().setHomeTowAddress(ItemNewHomeoneDataRelative.getAddress());
                    mContext1.startActivity(new Intent(view.getContext(), TestAtHomeCheckAddress.class));
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneNameUser(ItemNewHomeoneDataRelative.getName());
                    MyApplication.getMyApplication().getDataUser().setHomeOneAgeUser(ItemNewHomeoneDataRelative.getAge());
                    MyApplication.getMyApplication().getDataUser().setHomeOneGenderUser(ItemNewHomeoneDataRelative.getGender());
                    MyApplication.getMyApplication().getDataUser().setHomeOnePhoneUser(ItemNewHomeoneDataRelative.getPhone());
                    MyApplication.getMyApplication().getDataUser().setHomeOneAddressUser(ItemNewHomeoneDataRelative.getAddress());
                    mContext1.startActivity(new Intent(view.getContext(), Specialist.class));

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
        if(mListItemNewHomeoneDataRelative!=null){
            return mListItemNewHomeoneDataRelative.size();
        }
        return 0;
    }

    public class ItemNewHomeoneDataRelativeViewHoder extends RecyclerView.ViewHolder{
        private TextView name, gender, age , relative, phone, address;
        private Button next;





        public ItemNewHomeoneDataRelativeViewHoder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_home_home1_data_allRelative_name);
            gender = itemView.findViewById(R.id.txt_home_home1_data_allRelative_gender);
            age = itemView.findViewById(R.id.txt_home_home1_data_allRelative_age);
            relative = itemView.findViewById(R.id.txt_home_home1_data_allRelative_relative);
            phone = itemView.findViewById(R.id.txt_home_home1_data_allRelative_phone);
            address = itemView.findViewById(R.id.txt_home_home1_data_allRelative_address);
            next = itemView.findViewById(R.id.btn_home_home1_data_allRelative_book);




        }
    }
}
