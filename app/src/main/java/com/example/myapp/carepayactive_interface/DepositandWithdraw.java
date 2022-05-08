package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.ProfileMoney;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class DepositandWithdraw extends AppCompatActivity {
    ImageView back;
    TextView withDraw, money;
    LinearLayout card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositand_withdraw);
        back = findViewById(R.id.imv_carepay_active_dw_back);
        withDraw = findViewById(R.id.imv_carepay_active_dw_withdraw);
        money = findViewById(R.id.txt_carepay_active_dw_money);
        card = findViewById(R.id. ln_carepay_active_dw_card);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Double currencyAmount = Double.parseDouble(String.valueOf( MyApplication.getMyApplication().getDataUser().getMoneyStatic()) + "0");
        Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);


        money.setText(String.valueOf( dollarFormat.format(currencyAmount)).substring(1) + " USD");
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DepositandWithdraw.this, ProfileMoney.class);
                startActivity(intent);
            }
        });
        withDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DepositandWithdraw.this, Withdraw.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Double currencyAmount = Double.parseDouble(String.valueOf( MyApplication.getMyApplication().getDataUser().getMoneyStatic()) + "0");
        Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);


        money.setText(String.valueOf( dollarFormat.format(currencyAmount)).substring(1) + " USD");
    }
}