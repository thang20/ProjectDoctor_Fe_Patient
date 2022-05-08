package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.carepayactive_interface.DepositandWithdraw;
import com.example.myapp.carepayactive_interface.LinktoCard;
import com.example.myapp.carepayactive_interface.MyCarePay;
import com.example.myapp.carepayactive_interface.Transfer;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.ProfileDatascreenlock;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;


public class CarePayActiveFragment extends Fragment {
    TextView money, carepay, lock;
    ImageView scan, transfer, deposit;
    LinearLayout addCard;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_care_pay_active, container, false);
        money = (TextView) view.findViewById(R.id.txt_carepay_active_money);
        carepay = (TextView) view.findViewById(R.id.txt_carepay_active_carepay);
        lock = (TextView) view.findViewById(R.id.txt_carepay_active_lock);
        scan = (ImageView) view.findViewById(R.id.imv_carepay_active_scan);
        transfer = (ImageView) view.findViewById(R.id.imv_carepay_active_ct);
        deposit = (ImageView) view.findViewById(R.id.imv_carepay_active_pay);
        addCard = (LinearLayout) view.findViewById(R.id.ln_carepay_active_add_card);
        lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileDatascreenlock.class);
                startActivity(intent);
            }
        });
        carepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyCarePay.class);
                startActivity(intent);
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkcard = MyApplication.getMyApplication().getDataUser().getAddcard();
                if(checkcard.equals("0")){
                    Toast.makeText(getContext(), "Please create a card link before using this service.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivity(intent);
                }

            }
        });
        addCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LinktoCard.class);
                startActivity(intent);
            }
        });
        Double currencyAmount = Double.parseDouble(String.valueOf( MyApplication.getMyApplication().getDataUser().getMoneyStatic()) + "0");
        Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);


        money.setText(String.valueOf( dollarFormat.format(currencyAmount)).substring(1) + " USD");
        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkcard = MyApplication.getMyApplication().getDataUser().getAddcard();
                if(checkcard.equals("0")){
                    Toast.makeText(getContext(), "Please create a card link before using this service.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), DepositandWithdraw.class);
                    startActivity(intent);
                }

            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkcard = MyApplication.getMyApplication().getDataUser().getAddcard();
                if(checkcard.equals("0")){
                    Toast.makeText(getContext(), "Please create a card link before using this service.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getActivity(), Transfer.class);
                    startActivity(intent);
                }


            }
        });

        return view;
    }
}