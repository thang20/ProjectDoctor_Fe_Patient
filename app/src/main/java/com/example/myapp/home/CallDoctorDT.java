package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapp.MyApplication;
import com.example.myapp.R;

public class CallDoctorDT extends AppCompatActivity {
    ImageView face, chungchi, back;
    TextView hospital, name, spec, address, namef, hospitalf;
    Button call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_doctor_dt);
        face = findViewById(R.id.rcv_doctor_detail_spec_face);
        chungchi = findViewById(R.id.imv_doctor_detail_spec_chungchi);
        hospital = findViewById(R.id.txt__doctor_detail_spec_hospital);
        name = findViewById(R.id.txt_doctor_detail_spec_name);
        spec = findViewById(R.id.txt__doctor_detail_spec_spec);
        address = findViewById(R.id.txt_doctor_detail_spec_address);
        call = findViewById(R.id.btn_doctor_detail_spec_call);
        namef = findViewById(R.id.txt_doctor_detail_spec_namefront);
        hospitalf = findViewById(R.id.txt_doctor_detail_spec_hospitalfront);
        back = findViewById(R.id.imv_doctor_detail_spec_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Glide.with(CallDoctorDT.this).load(MyApplication.getMyApplication().getDataUser().getAPI()+MyApplication.myApplication.getDataUser().getDoctorSpecimage()).into(face);
        Glide.with(CallDoctorDT.this).load(MyApplication.getMyApplication().getDataUser().getAPI()+MyApplication.myApplication.getDataUser().getDoctorSpecchungchi()).into(chungchi);
        name.setText(MyApplication.getMyApplication().getDataUser().getDoctorSpecname());
        namef.setText(MyApplication.getMyApplication().getDataUser().getDoctorSpecname());
        hospital.setText(MyApplication.getMyApplication().getDataUser().getDoctorSpechopital());
        hospitalf.setText(MyApplication.getMyApplication().getDataUser().getDoctorSpechopital());
        address.setText(MyApplication.getMyApplication().getDataUser().getDoctorSpecaddress());
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = MyApplication.getMyApplication().getDataUser().getDoctorSpecphone();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });
        spec.setText(MyApplication.getMyApplication().getDataUser().getHomeOneSpecHospital());
    }

}