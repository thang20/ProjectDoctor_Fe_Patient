package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapp.MyApplication;
import com.example.myapp.R;

public class RSXray extends AppCompatActivity {
    ImageView back, rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsxray);
        back = findViewById(R.id.imv_home_home4_backrsxray);
        rs = findViewById(R.id.rsxray);
        Glide.with(RSXray.this).load(MyApplication.getMyApplication().getDataUser().getResultXray()).skipMemoryCache(false).into(rs);
        Log.d("xray", MyApplication.getMyApplication().getDataUser().getResultXray());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}