package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.ProfileDataChangepass;

public class CheckTransferToBank extends AppCompatActivity {
    ImageView back;
    TextView myinf, toname, tophone, am, tt, ct;
    Button next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_transfer_to_bank);
        back = findViewById(R.id.imv_carepay_active_transfer_tobank_chekc_back);
        myinf = findViewById(R.id.txt_carepay_active_transfer_tobank_chekc_myinf);
        toname = findViewById(R.id.txt_carepay_active_transfer_tobank_chekc_toname);
        tophone = findViewById(R.id.txt_carepay_active_transfer_tobank_chekc_tophone);
        am = findViewById(R.id.txt_carepay_active_transfer_tobank_chekc_am);
        tt = findViewById(R.id.txt_carepay_active_transfer_tobank_chekc_tt);
        ct = findViewById(R.id.txt_carepay_active_transfer_tobank_chekc_ct);
        next = findViewById(R.id.btn_carepay_active_transfer_tobank_chekc_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckTransferToBank.this, TransferToBank.class);
                startActivity(intent);
                finish();
                MyApplication.getMyApplication().getDataUser().setTransferBankCHECK(1);
            }
        });
        toname.setText(MyApplication.getMyApplication().getDataUser().getTransferBankName());
        myinf.setText("2307205188833" + "-" + MyApplication.getMyApplication().getDataUser().getFullNameStatic());
        tophone.setText(MyApplication.getMyApplication().getDataUser().getTransferBankPhone());
        am.setText(MyApplication.getMyApplication().getDataUser().getTransferBankAM() + ".00 USD");
        tt.setText(MyApplication.getMyApplication().getDataUser().getTransferBankAM() + ".00 USD");
        ct.setText(MyApplication.getMyApplication().getDataUser().getTransferBankContent());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getMyApplication().getDataUser().setCheckIsBank(1);
                Intent intent = new Intent(CheckTransferToBank.this, CheckPass.class);
                startActivity(intent);
                finish();

            }
        });
    }
}