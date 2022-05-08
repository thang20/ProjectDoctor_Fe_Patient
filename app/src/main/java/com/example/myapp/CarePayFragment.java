package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapp.carepayactive_interface.CarepayRegister;
import com.example.myapp.carepaynonactive_interface.IDCard;
import com.example.myapp.data.DataUser;

public class CarePayFragment extends Fragment {
    LinearLayout start;
    TextView name;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carepay, container, false);
        name = (TextView) view.findViewById(R.id.txt_carepay_non_name);
        start = (LinearLayout) view.findViewById(R.id.ln_carepay_non_start);
        name.setText("Hello " + MyApplication.getMyApplication().getDataUser().getFullNameStatic() + "!");
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IDCard.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
