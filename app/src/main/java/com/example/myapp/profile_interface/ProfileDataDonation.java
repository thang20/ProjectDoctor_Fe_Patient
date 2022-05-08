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
import com.example.myapp.fragment.ATMFragmentDonation;
import com.example.myapp.fragment.NationalFragment;
import com.example.myapp.fragment.NationalFragmentDonationD;
import com.example.myapp.fragment.NationalFragmentDonationP;

public class ProfileDataDonation extends AppCompatActivity {
    LinearLayout ln1, ln2, ln3;
    TextView txt1, txt2, txt3;
    Boolean check1, check2, check3;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_donation);

        ln1 = findViewById(R.id.ln_profile_donation1);
        ln2 = findViewById(R.id.ln_profile_donation2);
        ln3 = findViewById(R.id.ln_profile_donation3);
        txt1 = findViewById(R.id.txt_profile_donation1);
        txt2 = findViewById(R.id.txt_profile_donation2);
        txt3 = findViewById(R.id.txt_profile_donation3);
        imgBack = findViewById(R.id.imv_profile_donation_back);
        check1 = true;
        check2 = true;
        check3 = true;

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

                    ATMFragmentDonation atmFragmentDonation1 = new ATMFragmentDonation();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ln_profile_donationadd, atmFragmentDonation1);
                    fragmentTransaction.commit();
                    check2 = true;
                    check3 = true;
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
                    NationalFragmentDonationD nationalFragmentDonationD = new NationalFragmentDonationD();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ln_profile_donationadd, nationalFragmentDonationD);
                    fragmentTransaction.commit();
                    check1 = true;
                    check3 = true;
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

                if(check3.equals(false)) {

                }else {
                    NationalFragmentDonationP nationalFragmentDonationP = new NationalFragmentDonationP();
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.ln_profile_donationadd, nationalFragmentDonationP);
                    fragmentTransaction.commit();
                    check3 = false;
                    check1 = true;
                    check2 = true;
                }
            }
        });
    }
}
