package com.example.myapp.carepaynonactive_interface;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Home;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.ProfileDataChangepass;
import com.example.myapp.profile_interface.UpdateProfile;

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

public class CheckInf extends AppCompatActivity {
    EditText fullName, dateOfBirth, province, district, wards, address, card;
    ImageView imageViewBack;
    Spinner spinnerGender;
    Button ok, not;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_inf);

        fullName = (EditText) findViewById(R.id.edt_carepay_non_inf_fullName);
        dateOfBirth = (EditText) findViewById(R.id.edt_carepay_non_inf_dataOfBirth);
        province = (EditText) findViewById(R.id.edt_carepay_non_inf_province);
        district = (EditText) findViewById(R.id.edt_carepay_non_inf_district);
        wards = (EditText) findViewById(R.id.edt_carepay_non_inf_wards);
        address = (EditText) findViewById(R.id.edt_carepay_non_inf_address);
        spinnerGender = (Spinner) findViewById(R.id.spl_carepay_non_inf_gender);
        imageViewBack = (ImageView) findViewById(R.id.imv_carepay_non_inf_back);
        card = findViewById(R.id.edt_carepay_non_inf_card);
        ok = (Button) findViewById(R.id.btn_carepay_non_inf_true);
        not = findViewById(R.id.btn_carepay_non_inf_false);

        dateOfBirth.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();


            @RequiresApi(api = Build.VERSION_CODES.N)
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

        fullName.setText(MyApplication.getMyApplication().getDataUser().getGetCardFronname());

        dateOfBirth.setText(MyApplication.getMyApplication().getDataUser().getGetCardFrondob());

        province.setText(MyApplication.getMyApplication().getDataUser().getGetCardFronprovince());

        district.setText(MyApplication.getMyApplication().getDataUser().getGetCardFrondistrict());

        wards.setText(MyApplication.getMyApplication().getDataUser().getGetCardFronward());

        address.setText(MyApplication.getMyApplication().getDataUser().getGetCardFronaddress());

        card.setText(MyApplication.getMyApplication().getDataUser().getGetCardFronid());

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CheckPaperTowDone.class));
                finish();
            }
        });

        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("male");
        arrayList.add("female");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(arrayAdapter);

        if(MyApplication.getMyApplication().getDataUser().getGetCardFrongender().equals("NAM"))
        {
            spinnerGender.setSelection(0);
        }else {
            spinnerGender.setSelection(1);
        }
        not.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CheckInf.this, MyApplication.getMyApplication().getDataUser().getEmailStatic()+"Please correct any incorrect information", Toast.LENGTH_LONG).show();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {
                    String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.activecarepay(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                MyApplication.getMyApplication().getDataUser().setCarepayactive(1);
                                Toast.makeText(CheckInf.this, "Request has been sent, Please wait for a response", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                                finish();
                            }else {
                                Toast.makeText(CheckInf.this, "Request failed", Toast.LENGTH_LONG).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CheckInf.this, "Fail to call API", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });


    }
}