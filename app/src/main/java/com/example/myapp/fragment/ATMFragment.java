package com.example.myapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapp.R;


public class ATMFragment extends Fragment {

    Button add;
    EditText pin, seri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_atm, container, false);
        add = view.findViewById(R.id.btn_profile_money_add);
        pin = view.findViewById(R.id.edt_profile_moneypin);
        seri = view.findViewById(R.id.edt_profile_moneyseri);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("money", "1");

            }
        });
        return view;
    }
}