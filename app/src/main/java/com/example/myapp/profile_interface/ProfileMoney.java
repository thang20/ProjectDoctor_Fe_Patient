package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapp.R;
import com.example.myapp.fragment.ATMFragment;
import com.example.myapp.fragment.NationalFragment;

public class ProfileMoney extends AppCompatActivity {
    LinearLayout ln1, ln2, ln3;
    TextView txt1, txt2, txt3;
    Boolean check1, check2;
    ImageView imgBack;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_money);
        ln1 = findViewById(R.id.ln_profile_money1);
        ln2 = findViewById(R.id.ln_profile_money2);
        ln3 = findViewById(R.id.ln_profile_money3);
        txt1 = findViewById(R.id.txt_profile_money1);
        txt2 = findViewById(R.id.txt_profile_money2);
        txt3 = findViewById(R.id.txt_profile_money3);
        imgBack = findViewById(R.id.imv_profile_money_back);
        check1 = true;
        check2 = true;

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        ln1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                ln1.setBackgroundResource(R.drawable.lr_money);
                ln2.setBackgroundResource(R.drawable.lr_money_notcheck);
                ln3.setBackgroundResource(R.drawable.lr_money_notcheck);
                txt1.setTextColor(Color.parseColor("#2BC4BF"));
                txt2.setTextColor(Color.BLACK);
                txt3.setTextColor(Color.BLACK);

                if(check1.equals(false)) {

                }else {

                    ATMFragment atmFragment1 = new ATMFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ln_profile_moneyadd, atmFragment1);
                    fragmentTransaction.commit();
                    check2 = true;
                    check1 = false;
                }
            }
        });

        ln2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                ln1.setBackgroundResource(R.drawable.lr_money_notcheck);
                ln2.setBackgroundResource(R.drawable.lr_money);
                ln3.setBackgroundResource(R.drawable.lr_money_notcheck);
                txt1.setTextColor(Color.BLACK);
                txt2.setTextColor(Color.parseColor("#2BC4BF"));
                txt3.setTextColor(Color.BLACK);

                if(check2.equals(false)) {

                }else {
                    NationalFragment nationalFragment = new NationalFragment();
                    Log.d("money", "1231");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ln_profile_moneyadd, nationalFragment);
                    fragmentTransaction.commit();
                    check1 = true;
                    check2 = false;
                }


            }
        });

        ln3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                ln1.setBackgroundResource(R.drawable.lr_money_notcheck);
                ln2.setBackgroundResource(R.drawable.lr_money_notcheck);
                ln3.setBackgroundResource(R.drawable.lr_money);
                txt1.setTextColor(Color.BLACK);
                txt2.setTextColor(Color.BLACK);
                txt3.setTextColor(Color.parseColor("#2BC4BF"));

                if(check2.equals(false)) {

                }else {
                    NationalFragment nationalFragment = new NationalFragment();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ln_profile_moneyadd, nationalFragment);
                    fragmentTransaction.commit();

                    check1 = true;
                    check2 = false;
                }
            }
        });
    }


}