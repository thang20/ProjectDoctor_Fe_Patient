package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;

public class CheckTranferToCarePay extends AppCompatActivity {
    ImageView back;
    TextView myinf, toname, tophone, am, tt, ct;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_tranfer_to_care_pay);
        back = findViewById(R.id.imv_carepay_active_transfer_tocarepay_chekc_back);
        myinf = findViewById(R.id.txt_carepay_active_transfer_tocarepay_chekc_myinf);
        toname = findViewById(R.id.txt_carepay_active_transfer_tocarepay_chekc_toname);
        tophone = findViewById(R.id.txt_carepay_active_transfer_tocarepay_chekc_tophone);
        am = findViewById(R.id.txt_carepay_active_transfer_tocarepay_chekc_am);
        tt = findViewById(R.id.txt_carepay_active_transfer_tocarepay_chekc_tt);
        ct = findViewById(R.id.txt_carepay_active_transfer_tocarepay_chekc_ct);
        next = findViewById(R.id.btn_carepay_active_transfer_tocarepay_chekc_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckTranferToCarePay.this, TranferToCarePay.class);
                startActivity(intent);
                finish();
                MyApplication.getMyApplication().getDataUser().setTransferCarePayCHECK(1);
            }
        });
        myinf.setText(String.valueOf(MyApplication.getMyApplication().getDataUser().getPhoneStatic()) + "-" + MyApplication.getMyApplication().getDataUser().getFullNameStatic());
        toname.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayName());
        tophone.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayPhone());
        am.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayAM() + ".00 USD");
        tt.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayAM() + ".00 USD");
        ct.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayContent());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getMyApplication().getDataUser().setCheckIsBank(0);
                Intent intent = new Intent(CheckTranferToCarePay.this, CheckPass.class);
                startActivity(intent);
                finish();

            }
        });


    }
}