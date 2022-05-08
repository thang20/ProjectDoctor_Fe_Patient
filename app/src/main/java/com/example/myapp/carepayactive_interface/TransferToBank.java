package com.example.myapp.carepayactive_interface;

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

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.data.DataUser;

import java.util.ArrayList;

public class TransferToBank extends AppCompatActivity {
    ImageView back, img;
    EditText numberbank, am, sran;
    TextView inf, money, name;
    Button next;
    Spinner bank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_to_bank);
        back = findViewById(R.id.imv_carepay_active_transfer_tobank_back);
        img = findViewById(R.id.imv_carepay_active_transfer_tobank_img);
        numberbank = findViewById(R.id.edt_carepay_active_transfer_tobank_phone);
        am = findViewById(R.id.edt_carepay_active_transfer_tobank_am);
        sran = findViewById(R.id.edt_carepay_active_transfer_tobank_sran);
        inf = findViewById(R.id.txt_carepay_active_transfer_tobank_inf);
        money = findViewById(R.id.txt_carepay_active_transfer_tobank_money);
        name = findViewById(R.id.txt_carepay_active_transfer_tobank_name);
        next = findViewById(R.id.btn_carepay_active_transfer_tobank_next);
        bank = findViewById(R.id.spl_carepay_active_transfer_tobank_bank);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        inf.setText("2307205188833" +"-"+ MyApplication.getMyApplication().getDataUser().getFullNameStatic());
        sran.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic() + " transfer");
        money.setText(String.valueOf(MyApplication.getMyApplication().getDataUser().getMoneyStatic()) + " USD");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("Agribank");
        arrayList.add("VietcomBank");
        arrayList.add("Bao Viet Bank");
        arrayList.add("ACB");
        arrayList.add("ABBank");
        arrayList.add("Bac A bank");
        arrayList.add("Oceanbank");
        arrayList.add("GPBank");
        arrayList.add("Dong A bank");
        arrayList.add("Seabank");
        arrayList.add("Maritime Bank");
        arrayList.add("Kien Long bank");
        arrayList.add("Techcombank");
        arrayList.add("Nam A Bank");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bank.setAdapter(arrayAdapter);

        numberbank.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String check = numberbank.getText().toString();
                if(check.length()>=5&&check.length()<=13) {


                }else if (check.length()>=1&&check.length()<5){
                    Toast.makeText(TransferToBank.this, "Please enter the correct account number", Toast.LENGTH_LONG).show();

                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getMyApplication().getDataUser().setTransferBankName(bank.getSelectedItem().toString());
                MyApplication.getMyApplication().getDataUser().setTransferBankPhone(numberbank.getText().toString());
                MyApplication.getMyApplication().getDataUser().setTransferBankContent(sran.getText().toString());
                MyApplication.getMyApplication().getDataUser().setTransferBankAM(am.getText().toString());
                Intent intent = new Intent(TransferToBank.this, CheckTransferToBank.class);
                startActivity(intent);
                finish();

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if( MyApplication.getMyApplication().getDataUser().getTransferBankCHECK()==1) {
            numberbank.setText(MyApplication.getMyApplication().getDataUser().getTransferBankPhone());
            am.setText(MyApplication.getMyApplication().getDataUser().getTransferBankAM());
        }
    }
}