package com.example.myapp.profile_interface;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
import com.example.myapp.data.ItemProfileData01;
import com.example.myapp.data.ItemProfileDataRelative;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDataRelativeDetail extends AppCompatActivity {
    String phonestatic;
    private ItemProfileDataRelative itemProfileDataRelative;
    ProgressDialog progressDialog;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_relative_detail);
        ImageView back;
        TextView save;
        EditText relative,fullname, phone, dateOfBirth, city, district, ward, address;
        Spinner gender;
        back = findViewById(R.id.imv_profile_relative_detail_back);
        save = findViewById(R.id.txt_profile_relative_detail_save);
        relative  = findViewById(R.id.edt_profile_relative_detail_relative);
        fullname  = findViewById(R.id.edt_profile_relative_detail_name);
        phone  = findViewById(R.id.edt_profile_relative_detail_phone);
        dateOfBirth  = findViewById(R.id.edt_profile_relative_detail_dataOfBirth);
        gender  = findViewById(R.id.spl_profile_relative_detail_gender);
        city  = findViewById(R.id.edt_profile_relative_detail_province);
        district  = findViewById(R.id.edt_profile_relative_detail_district);
        ward  = findViewById(R.id.edt_profile_relative_detail_wards);
        address  = findViewById(R.id.edt_profile_relative_detail_address);

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

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("male");
        arrayList.add("female");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(arrayAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(ProfileDataRelativeDetail.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);

                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {
                    String sex = "";


                    jsonObject = new JSONObject();
                    jsonObject.put("phoneold", phonestatic);
                    jsonObject.put("phonenew", phone.getText().toString());
                    jsonObject.put("relative", relative.getText().toString());
                    jsonObject.put("fullname", fullname.getText().toString());
                    jsonObject.put("dataofbirth", dateOfBirth.getText().toString());
                    jsonObject.put("gender", gender.getSelectedItem().toString());
                    jsonObject.put("city", city.getText().toString());
                    jsonObject.put("district", district.getText().toString());
                    jsonObject.put("ward", ward.getText().toString());
                    jsonObject.put("address", address.getText().toString());
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.profilerelativedetailupdate(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                            Toast.makeText(ProfileDataRelativeDetail.this, "Success", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            onBackPressed();
                            }else {
                                Toast.makeText(ProfileDataRelativeDetail.this, "Fail to Save", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ;



                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileDataRelativeDetail.this, "Call API Fail", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });

        Bundle bundle=getIntent().getExtras();

        if (bundle != null){
            itemProfileDataRelative = bundle.getParcelable("parcelable");
            if (itemProfileDataRelative!=null)
            {
                phonestatic = itemProfileDataRelative.getPhone().toString();
                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {

                    jsonObject = new JSONObject();
                    jsonObject.put("phone", phonestatic);
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.profilerelativedetail(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;
                        try {
                            notice = response.body().string();
                            JSONObject obj = new JSONObject(notice);
                            relative.setText(obj.get("relative").toString());
                            fullname.setText(obj.get("fullname").toString());
                            dateOfBirth.setText(obj.get("relativedateofbirth").toString());
                            if(obj.get("relativedateofgender").toString().equals("male"))
                            {
                            }else {
                                gender.setSelection(1);
                            }
                            phone.setText(obj.get("relativephone").toString());
                            city.setText(obj.get("relativecity").toString());
                            district.setText(obj.get("relativedistrict").toString());
                            ward.setText(obj.get("relativeward").toString());
                            address.setText(obj.get("relativeaddress").toString());
                            Toast.makeText(ProfileDataRelativeDetail.this, "Success", Toast.LENGTH_LONG).show();
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }




                        }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProfileDataRelativeDetail.this, "Call API Fail", Toast.LENGTH_LONG).show();

                    }
                });
            }

        }


    }
}