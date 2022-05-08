package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;

public class Transfer extends AppCompatActivity {
    ImageView back;
    LinearLayout carepay, bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        back = findViewById(R.id.imv_carepay_active_transfer_back);
        carepay = findViewById(R.id.ln_carepay_active_transfer_tocarepay);
        bank = findViewById(R.id.ln_carepay_active_transfer_tobank);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        carepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transfer.this, TranferToCarePay.class);
                startActivity(intent);
                MyApplication.getMyApplication().getDataUser().setTransferCarePayCHECK(0);

            }
        });
        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Transfer.this, TransferToBank.class);
                startActivity(intent);
                MyApplication.getMyApplication().getDataUser().setTransferBankCHECK(0);



            }
        });
    }
}