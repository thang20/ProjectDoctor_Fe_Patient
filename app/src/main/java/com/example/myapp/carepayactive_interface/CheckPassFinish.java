package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.BuildConfig;
import com.example.myapp.Home;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;

import java.util.Calendar;
import java.util.Date;

public class CheckPassFinish extends AppCompatActivity {
    ImageView home;
    TextView money, inf, time;
    Button share, transfer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pass_finish);
        home = findViewById(R.id.imv_carepay_active_trading_home);
        money = findViewById(R.id.txt_carepay_active_trading_money);
        inf = findViewById(R.id.txt_carepay_active_trading_inf);
        time = findViewById(R.id.txt_carepay_active_trading_time);
        share = findViewById(R.id.btn_carepay_active_trading_share);
        transfer = findViewById(R.id.btn_carepay_active_trading_more);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckPassFinish.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
        if(MyApplication.getMyApplication().getDataUser().getCheckIsBank()==0) {
            money.setText( MyApplication.getMyApplication().getDataUser().getTransferCarePayAM() + ".00 USD");
            inf.setText("You have successfully transferred the amount of " + MyApplication.getMyApplication().getDataUser().getTransferCarePayAM() + ".00 USD to account holder " + MyApplication.getMyApplication().getDataUser().getTransferCarePayName() + " CarePay(" + MyApplication.getMyApplication().getDataUser().getTransferCarePayPhone() + ")");
        }else if(MyApplication.getMyApplication().getDataUser().getCheckIsBank()==1){
            money.setText(MyApplication.getMyApplication().getDataUser().getTransferBankAM() + ".00 USD");
            inf.setText("You have successfully transferred the amount of " + MyApplication.getMyApplication().getDataUser().getTransferBankAM() + ".00 USD to account holder " + "User" +" " + MyApplication.getMyApplication().getDataUser().getTransferCarePayName()+ " (" + MyApplication.getMyApplication().getDataUser().getTransferBankPhone() + ")");
        }
        Date currentTime = Calendar.getInstance().getTime();
        time.setText(currentTime.toString());

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My Doctor");
                    String shareMessage= "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));

                } catch(Exception e) {
                    //e.toString();

                }

            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckPassFinish.this, Transfer.class);
                startActivity(intent);
                finish();
            }
        });
    }
}