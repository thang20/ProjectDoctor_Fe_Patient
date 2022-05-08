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
import com.example.myapp.home.CallDoctorDT;

import java.util.List;

public class ItemNewDoctorSpecAdapter extends RecyclerView.Adapter<ItemNewDoctorSpecAdapter.ItemNewDoctorSpecViewHoder> {
    List<ItemNewDoctorSpec> mListItemNewDoctorSpec;
    Context mContext1;



    public ItemNewDoctorSpecAdapter(Context context1, List<ItemNewDoctorSpec> mListItemNewDoctorSpec) {
        this.mContext1 = context1;
        this.mListItemNewDoctorSpec = mListItemNewDoctorSpec;

    }

    @NonNull
    @Override
    public ItemNewDoctorSpecViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_all_doctor_spec, parent, false);
        return new ItemNewDoctorSpecViewHoder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ItemNewDoctorSpecViewHoder holder, @SuppressLint("RecyclerView") int position) {
        ItemNewDoctorSpec ItemNewDoctorSpec = mListItemNewDoctorSpec.get(position);

        if(ItemNewDoctorSpec==null){
            return;
        }
        holder.name.setText(ItemNewDoctorSpec.getName());
        holder.hospital.setText(ItemNewDoctorSpec.getHospital());
//        holder.image.setImageURI(Uri.parse(ItemNewDoctorSpec.getImage()));
        Glide.with(mContext1).load(MyApplication.getMyApplication().getDataUser().getAPI()+ItemNewDoctorSpec.getImage()).into(holder.face);
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //image, name, hospital, address, phone, chungchi;
                MyApplication.myApplication.getDataUser().setDoctorSpecimage(ItemNewDoctorSpec.getImage());
                MyApplication.myApplication.getDataUser().setDoctorSpecname(ItemNewDoctorSpec.getName());
                MyApplication.myApplication.getDataUser().setDoctorSpechopital(ItemNewDoctorSpec.getHospital());
                MyApplication.myApplication.getDataUser().setDoctorSpecaddress(ItemNewDoctorSpec.getAddress());
                MyApplication.myApplication.getDataUser().setDoctorSpecphone(ItemNewDoctorSpec.getPhone());
                MyApplication.myApplication.getDataUser().setDoctorSpecchungchi(ItemNewDoctorSpec.getChungchi());

                mContext1.startActivity(new Intent(view.getContext(), CallDoctorDT.class));


            }
        });

    }

    public void release(){
        mContext1 = null;
    }
    @Override
    public int getItemCount() {
        if(mListItemNewDoctorSpec!=null){
            return mListItemNewDoctorSpec.size();
        }
        return 0;
    }

    public class ItemNewDoctorSpecViewHoder extends RecyclerView.ViewHolder{
        private TextView name, hospital;
        private ImageView face;
        private LinearLayout next;





        public ItemNewDoctorSpecViewHoder(@NonNull View itemView) {
            super(itemView);

            face = itemView.findViewById(R.id.rcv_all_doctor_spec1_image);
            name = itemView.findViewById(R.id.txt_all_doctor_spec1_name);
            hospital = itemView.findViewById(R.id.txt_all_doctor_spec1_spec);
            next = itemView.findViewById(R.id.ln_all_doctor_spec1_next);



        }
    }
}


