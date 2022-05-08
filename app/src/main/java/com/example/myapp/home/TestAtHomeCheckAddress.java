package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;

public class TestAtHomeCheckAddress extends AppCompatActivity {
    ImageView back;
    EditText address;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_at_home);
        back = findViewById(R.id.imv_home_home2_back);
        address = findViewById(R.id.edt__home_home2_address);
        next = findViewById(R.id.btn_home_home2_next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestAtHomeCheckAddress.this, TestAtHomeFinal.class));
                finish();
            }
        });
        address.setText(MyApplication.getMyApplication().getDataUser().getHomeTowAddress());
        if(address.getText().toString().equals("")){
            Toast.makeText(this, "Please Enter the Address", Toast.LENGTH_SHORT).show();
        }


    }
}