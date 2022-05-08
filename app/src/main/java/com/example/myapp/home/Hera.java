package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.R;

public class Hera extends AppCompatActivity {
    ImageView next1, next2, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hera);
        next1 = findViewById(R.id.imv_home_home4_next1);
        next2 = findViewById(R.id.imv_home_home4_next2);
        back = findViewById(R.id.imv_home_home4_backHera);
        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hera.this, HeraXray.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Hera.this, HeraSkin.class);
                startActivity(intent);

            }
        });
    }
}