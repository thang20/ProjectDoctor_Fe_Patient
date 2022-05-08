package com.example.myapp.carepaynonactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.UpdateProfile;

import java.util.ArrayList;

public class IDCard extends AppCompatActivity {
    ImageView back;
    TextView phone, name;
    Button update, comtinue;
    Spinner typecard;
    EditText idnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idcard);
        back = findViewById(R.id.imv_carepay_non_cardid_back);
        phone = findViewById(R.id.txt_carepay_non_cardid_phone);
        name = findViewById(R.id.txt_carepay_non_cardid_name);
        update = findViewById(R.id.btn_carepay_non_cardid_update);
        typecard = findViewById(R.id.sql_carepay_non_cardid_typecard);
        idnumber = findViewById(R.id.edt_carepay_non_cardid_numberid);
        comtinue = findViewById(R.id.btn_carepay_non_cardid_next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        phone.setText(MyApplication.getMyApplication().getDataUser().getPhoneStatic());
        name.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic());
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Citizen ID");
        arrayList.add("Identity card");
        arrayList.add("Passport");
        arrayList.add("Military ID card");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typecard.setAdapter(arrayAdapter);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IDCard.this, UpdateProfile.class);
                MyApplication.getMyApplication().getDataUser().setCarepay(true);
                startActivity(intent);
                finish();
            }
        });

        idnumber.setText(MyApplication.getMyApplication().getDataUser().getIdcard());
        comtinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(idnumber.getText().toString().length()==0){
                    Toast.makeText(IDCard.this, "Please input ID number", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(IDCard.this, CheckFace.class);
                    startActivity(intent);
                    MyApplication.getMyApplication().getDataUser().setIdcard(idnumber.getText().toString());
                    finish();
                }

            }
        });
    }
}