package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapp.MyApplication;
import com.example.myapp.R;

public class RSSkin extends AppCompatActivity {
    ImageView back, rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsskin);

        back = findViewById(R.id.imv_home_home4_backrsskin);
        rs = findViewById(R.id.rsskin);
        Glide.with(RSSkin.this).load(MyApplication.getMyApplication().getDataUser().getResultXray()+"/dt1").skipMemoryCache(false).into(rs);
        Log.d("skin", MyApplication.getMyApplication().getDataUser().getResultXray());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}