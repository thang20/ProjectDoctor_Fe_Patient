package com.example.myapp.profile_interface;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDataDeclaration extends AppCompatActivity {
    EditText fullName, dateOfBirth, province, district, wards, address, phone;
    ImageView imageViewBack;
    Spinner spinnerGender;
    Button send;
    RadioGroup signal1, signal2, signal3, signal4, signal5, signal6, signal7, ep1, ep2, ep3, ep4, ep5, ep6;
    String s1 ="0" , s2 = "0", s3 = "0", s4 = "0", s5 = "0", s6 = "0", s7 = "0", s8 = "0", s9 = "0", s10 = "0", s11 = "0", s12 = "0", s13 = "0";
    ProgressDialog progressDialog;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4, radioButton5, radioButton6, radioButton7, radioButton8, radioButton9, radioButton10, radioButton11, radioButton12, radioButton13;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_declaration);
        signal1 = findViewById(R.id.rdg_fever);
        signal2 = findViewById(R.id.rgr_cou);
        signal3 = findViewById(R.id.rgr_so);
        signal4 = findViewById(R.id.rgr_lo);
        signal5 = findViewById(R.id.rgr_fee);
        signal6 = findViewById(R.id.rgr_sho);
        signal7 = findViewById(R.id.rgr_oth);

        ep1 = findViewById(R.id.rgr_ar);
        ep2 = findViewById(R.id.rgr_cl);
        ep3 = findViewById(R.id.rgr_tra);
        ep4 = findViewById(R.id.rgr_ha);
        ep5 = findViewById(R.id.rgr_in);
        ep6 = findViewById(R.id.rgr_have);

        radioButton1 = findViewById(R.id.rdg_fever2);
        radioButton2 = findViewById(R.id.rgr_cou2);
        radioButton3 = findViewById(R.id.rgr_so2);
        radioButton4 = findViewById(R.id.rgr_lo2);
        radioButton5 = findViewById(R.id.rgr_fee2);
        radioButton6 = findViewById(R.id.rgr_sho2);
        radioButton7 = findViewById(R.id.rgr_oth2);
        radioButton8 = findViewById(R.id.rgr_ar2);
        radioButton9 = findViewById(R.id.rgr_cl2);
        radioButton10 = findViewById(R.id.rgr_tra2);
        radioButton11 = findViewById(R.id.rgr_ha2);
        radioButton12 = findViewById(R.id.rgr_in2);
        radioButton13 = findViewById(R.id.rgr_have2);

        radioButton1.setChecked(true);
        radioButton2.setChecked(true);
        radioButton3.setChecked(true);
        radioButton4.setChecked(true);
        radioButton5.setChecked(true);
        radioButton6.setChecked(true);
        radioButton7.setChecked(true);
        radioButton8.setChecked(true);
        radioButton9.setChecked(true);
        radioButton10.setChecked(true);
        radioButton11.setChecked(true);
        radioButton12.setChecked(true);
        radioButton13.setChecked(true);




        fullName = (EditText) findViewById(R.id.edt_profile_declaration_fullName);
        phone = (EditText) findViewById(R.id.edt_profile_declaration_phone);
        dateOfBirth = (EditText) findViewById(R.id.edt_profile_declaration_dataOfBirth);
        province = (EditText) findViewById(R.id.edt_profile_declaration_province);
        district = (EditText) findViewById(R.id.edt_profile_declaration_district);
        wards = (EditText) findViewById(R.id.edt_profile_declaration_wards);
        address = (EditText) findViewById(R.id.edt_profile_declaration_address);
        spinnerGender = (Spinner) findViewById(R.id.spl_profile_declaration_gender);
        imageViewBack = (ImageView) findViewById(R.id.imv_profile_declaration_back);
        send = findViewById(R.id.btn_profile_declaration_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ProfileDataDeclaration.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {
                    String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonObject.put("s1", s1);
                    jsonObject.put("s2", s2);
                    jsonObject.put("s3", s3);
                    jsonObject.put("s4", s4);
                    jsonObject.put("s5", s5);
                    jsonObject.put("s6", s6);
                    jsonObject.put("s7", s7);
                    jsonObject.put("s8", s8);
                    jsonObject.put("s9", s9);
                    jsonObject.put("s10", s10);
                    jsonObject.put("s11", s11);
                    jsonObject.put("s12", s12);
                    jsonObject.put("s13", s13);
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.healcv(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                Toast.makeText(ProfileDataDeclaration.this, "Successful medical declaration", Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }else {
                                Toast.makeText(ProfileDataDeclaration.this, "Fail medical declaration", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProfileDataDeclaration.this, "Fail to call API", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        dateOfBirth.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        //This part makes sure that when we finish entering numbers
                        //the date is correct, fixing it otherwise
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2100:year;
                        cal.set(Calendar.YEAR, year);
                        // ^ first set year for the line below to work correctly
                        //with leap years - otherwise, date e.g. 29/02/2012
                        //would be automatically corrected to 28/02/2012

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    dateOfBirth.setText(current);
                    dateOfBirth.setSelection(sel < current.length() ? sel : current.length());



                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });


        fullName.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic());


        dateOfBirth.setText( MyApplication.getMyApplication().getDataUser().getDateOfBirthStatic());

        province.setText( MyApplication.getMyApplication().getDataUser().getCityStatic());

        district.setText( MyApplication.getMyApplication().getDataUser().getTownshipStatic());

        wards.setText( MyApplication.getMyApplication().getDataUser().getWardStatic());

        address.setText( MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic());
        phone.setText( MyApplication.getMyApplication().getDataUser().getPhoneStatic());

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("male");
        arrayList.add("female");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(arrayAdapter);

        if( MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic().equals("null")|| MyApplication.getMyApplication().getDataUser().getApartmentNumberStatic().equals("male"))
        {

        }else {
            spinnerGender.setSelection(1);
        }

        signal1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdg_fever1:
                        s1 = "1";
                        break;
                    case R.id.rdg_fever2:
                        s1 = "0";
                        break;
                }
            }
        });
        signal2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_cou1:
                        s2 = "1";
                        break;
                    case R.id.rgr_cou2:
                        s2 = "0";
                        break;
                }
            }
        });
        signal3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_so1:
                        s3 = "1";
                        break;
                    case R.id.rgr_so2:
                        s3 = "0";
                        break;
                }
            }
        });
        signal4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_lo1:
                        s4 = "1";
                        break;
                    case R.id.rgr_lo2:
                        s4 = "0";
                        break;
                }
            }
        });
        signal5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_fee1:
                        s5 = "1";
                        break;
                    case R.id.rgr_fee:
                        s5 = "0";
                        break;
                }
            }
        });
        signal6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_sho1:
                        s6 = "1";
                        break;
                    case R.id.rgr_sho2:
                        s6 = "0";
                        break;
                }
            }
        });
        signal7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_oth1:
                        s7 = "1";
                        break;
                    case R.id.rgr_oth2:
                        s7 = "0";
                        break;
                }
            }
        });

        ep1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_ar1:
                        s8 = "1";
                        break;
                    case R.id.rgr_ar2:
                        s8 = "0";
                        break;
                }
            }
        });
        ep2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_cl1:
                        s9 = "1";
                        break;
                    case R.id.rgr_cl2:
                        s9 = "0";
                        break;
                }
            }
        });
        ep3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_tra1:
                        s10 = "1";
                        break;
                    case R.id.rgr_tra2:
                        s10 = "0";
                        break;
                }
            }
        });
        ep4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_ha1:
                        s11 = "1";
                        break;
                    case R.id.rgr_ha2:
                        s11 = "0";
                        break;
                }
            }
        });
        ep5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_in1:
                        s12 = "1";
                        break;
                    case R.id.rgr_in2:
                        s12 = "0";
                        break;
                }
            }
        });
        ep6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rgr_have1:
                        s13 = "1";
                        break;
                    case R.id.rgr_have2:
                        s13 = "0";
                        break;
                }
            }
        });


    }
}