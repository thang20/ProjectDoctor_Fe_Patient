package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.myapp.data.ItemProfileData01Adapter;
import com.example.myapp.hospital.LocationHospital;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ItemProfileData01Adapter itemProfileData01Adapter;
//    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportFragmentManager().beginTransaction().replace(R.id.main, new ProfileFragment()).commit();

//        recyclerView = (RecyclerView) findViewById(R.id.rcv_profile_image);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        itemProfileData01Adapter = new ItemProfileData01Adapter(getListData01());
//        recyclerView.setAdapter(itemProfileData01Adapter);
//        calendar = Calendar.getInstance();
//        Log.d("time111", String.valueOf(calendar.getTimeInMillis()));
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }


}