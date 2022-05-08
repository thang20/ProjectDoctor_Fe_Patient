package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapp.home.AllHospital;
import com.example.myapp.home.AllSpecCall;
import com.example.myapp.home.Hera;
import com.example.myapp.home.OnlinePharmacy;
import com.example.myapp.home.QADoctor;
import com.example.myapp.home.Services;
import com.example.myapp.home.TakeDateTime;

public class HomeFragment extends Fragment {
    LinearLayout appointment, testHome, pharmacy, hera, call, QA, other;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        appointment = view.findViewById(R.id.ln_home1);
        testHome = view.findViewById(R.id.ln_home2);
        pharmacy = view.findViewById(R.id.ln_home3);
        hera = view.findViewById(R.id.ln_home4);
        call = view.findViewById(R.id.ln_home5);
        QA = view.findViewById(R.id.ln_home6);
        other = view.findViewById(R.id.ln_home7);

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("0");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("0");
                Intent intent = new Intent(getActivity(), AllHospital.class);
                startActivity(intent);
            }
        });
        testHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("0");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("1");
                Intent intent = new Intent(getActivity(), TakeDateTime.class);
                startActivity(intent);
            }
        });
        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("0");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("0");
                Intent intent = new Intent(getActivity(), OnlinePharmacy.class);
                startActivity(intent);

            }
        });
        hera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("0");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("0");
                Intent intent = new Intent(getActivity(), Hera.class);
                startActivity(intent);

            }
        });
        QA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("0");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("0");
                Intent intent = new Intent(getActivity(), QADoctor.class);
                startActivity(intent);
            }
        });
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("0");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("0");
                Intent intent = new Intent(getActivity(), Services.class);
                startActivity(intent);

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.myApplication.getDataUser().setCheckCall("1");
                MyApplication.myApplication.getDataUser().setCheckTestAtHome("0");
                Intent intent = new Intent(getActivity(), AllSpecCall.class);
                startActivity(intent);

            }
        });




        return view;
    }
}
