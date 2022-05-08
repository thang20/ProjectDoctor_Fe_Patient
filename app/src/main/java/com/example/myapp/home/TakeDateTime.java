package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.profile_interface.ProfileDataChangepass;
import com.example.myapp.profile_interface.ProfileDataScheduleAdd;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeDateTime extends AppCompatActivity {
    ImageView back;
    Button next;
    TextView service, hService, time1, time2, time3, time4, time5, time6, time7, time8, time9, time10, time11, time12, time13, time14, time15, time16;
    EditText spinnerDate;
    DatePickerDialog datepicker;
    String KQ = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_date_time);
        back = findViewById(R.id.imv_home_home1_appointment_back);
        service = findViewById(R.id.txt_home_home1_appointment_service);
        hService = findViewById(R.id.txt_home_home1_appointment_hService);
        spinnerDate = findViewById(R.id.spn_home_home1_appointment_date);
        next = findViewById(R.id.btn_home_home1_appointment_next);
        time1 = findViewById(R.id.txt_home_home1_appointment_time1);
        time2 = findViewById(R.id.txt_home_home1_appointment_time2);
        time3 = findViewById(R.id.txt_home_home1_appointment_time3);
        time4 = findViewById(R.id.txt_home_home1_appointment_time4);
        time5 = findViewById(R.id.txt_home_home1_appointment_time5);
        time6 = findViewById(R.id.txt_home_home1_appointment_time6);
        time7 = findViewById(R.id.txt_home_home1_appointment_time7);
        time8 = findViewById(R.id.txt_home_home1_appointment_time8);
        time9 = findViewById(R.id.txt_home_home1_appointment_time9);
        time10 = findViewById(R.id.txt_home_home1_appointment_time10);
        time11 = findViewById(R.id.txt_home_home1_appointment_time11);
        time12 = findViewById(R.id.txt_home_home1_appointment_time12);
        time13 = findViewById(R.id.txt_home_home1_appointment_time13);
        time14 = findViewById(R.id.txt_home_home1_appointment_time14);
        time15 = findViewById(R.id.txt_home_home1_appointment_time15);
        time16 = findViewById(R.id.txt_home_home1_appointment_time16);
        //        HomeOneNameHospital



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
//        spinnerDate.setText(currentDate + " - Today");
        MyApplication.getMyApplication().getDataUser().setHomeOneDate(currentDate);
        spinnerDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final java.util.Calendar cldr = java.util.Calendar.getInstance();
                int day = cldr.get(java.util.Calendar.DAY_OF_MONTH);
                int month = cldr.get(java.util.Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(TakeDateTime.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                spinnerDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                MyApplication.getMyApplication().getDataUser().setHomeOneDate(spinnerDate.getText().toString());

                                ///////////////////
                                time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                                time1.setText("7:30");
                                time2.setText("8:30");
                                time3.setText("9:00");
                                time4.setText("9:30");
                                time5.setText("10:00");
                                time6.setText("10:30");
                                time7.setText("11:00");
                                time8.setText("11:30");
                                time9.setText("12:00");
                                time10.setText("13:00");
                                time11.setText("13:30");
                                time12.setText("14:00");
                                time13.setText("14:30");
                                time14.setText("15:00");
                                time15.setText("15:30");
                                time16.setText("16:00");
                                /////////////////////////////
                                JSONObject jsonObject = new JSONObject();
                                String jsonStr = null;
                                try {

                                    jsonObject = new JSONObject();
                                    jsonObject.put("hospital", MyApplication.myApplication.getDataUser().getHomeOneNameHospital());
                                    jsonObject.put("spec", MyApplication.myApplication.getDataUser().getHomeOneSpecHospital());
                                    jsonObject.put("date", spinnerDate.getText().toString());
                                    jsonStr = jsonObject.toString();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                RequestBody body =
                                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                                Apiservice.apiservice.checklich(body).enqueue(new Callback<ResponseBody>() {
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        String notice = null;
                                        try {
                                            notice = response.body().string();
                                            KQ = notice;
                                            take(KQ);
                                            


                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                                    }
                                });




                                ////////////////////////////////////
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });


        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getMyApplication().getDataUser().setHomeOneType(service.getText().toString());
                service.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                hService.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
            }
        });
        hService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.getMyApplication().getDataUser().setHomeOneType(hService.getText().toString());
                hService.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                service.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
            }
        });

        time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time1.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time1.getText().toString());
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });
        time2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time2.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time2.getText().toString());
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time3.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time3.getText().toString());
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });
        time4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time4.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time4.getText().toString());
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time5.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time5.getText().toString());
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });


        time6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time6.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time6.getText().toString());
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });


        time7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time7.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time7.getText().toString());
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });


        time8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time8.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time8.getText().toString());
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time9.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time9.getText().toString());
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time10.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time10.getText().toString());
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time11.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time11.getText().toString());
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time12.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time12.getText().toString());
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time13.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time13.getText().toString());
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time14.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time14.getText().toString());
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time15.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time15.getText().toString());
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });

        time16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(time16.getText().toString().equals("X")){
                    Toast.makeText(TakeDateTime.this, "This time has already been booked , Please choose another time", Toast.LENGTH_SHORT).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setHomeOneTime(time16.getText().toString());
                    time16.setBackgroundTintList(ColorStateList.valueOf(0xFF2BC4BF));
                    time2.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time3.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time4.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time5.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time6.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time7.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time8.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time9.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time10.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time11.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time12.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time13.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time14.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time15.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    time1.setBackgroundTintList(ColorStateList.valueOf(0xFFECEFEF));
                    take(KQ);
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( MyApplication.myApplication.getDataUser().getCheckTestAtHome().equals("1")){


                    if (MyApplication.getMyApplication().getDataUser().getHomeOneType().equals("")) {
                        Toast.makeText(TakeDateTime.this, "Please select service", Toast.LENGTH_SHORT).show();
                    } else if (MyApplication.getMyApplication().getDataUser().getHomeOneTime().equals("")) {
                        Toast.makeText(TakeDateTime.this, "Please select time for meet", Toast.LENGTH_SHORT).show();

                    } else {

                        startActivity(new Intent(getApplicationContext(), AllRelative.class));
                        finish();
                    }
                }
                else {
                    if (MyApplication.getMyApplication().getDataUser().getHomeOneType().equals("")) {
                        Toast.makeText(TakeDateTime.this, "Please select service", Toast.LENGTH_SHORT).show();
                    } else if (MyApplication.getMyApplication().getDataUser().getHomeOneTime().equals("")) {
                        Toast.makeText(TakeDateTime.this, "Please select time for meet", Toast.LENGTH_SHORT).show();

                    } else {

                        startActivity(new Intent(getApplicationContext(), CheckAll.class));
                        finish();
                    }
                }
            }
        });






    }
    public void take(String KQ) {
        boolean isFound = KQ.contains("7:30");
        if(isFound == true){
            time1.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time1.setText("X");
        }


        isFound = KQ.contains("8:30");
        if(isFound == true){
            time2.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time2.setText("X");
        }

        isFound = KQ.contains("9:00");
        if(isFound == true){
            time3.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time3.setText("X");
        }

        isFound = KQ.contains("9:30");
        if(isFound == true){
            time4.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time4.setText("X");
        }

        isFound = KQ.contains("10:00");
        if(isFound == true){
            time5.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time5.setText("X");
        }

        isFound = KQ.contains("10:30");
        if(isFound == true){
            time6.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time6.setText("X");
        }

        isFound = KQ.contains("11:00");
        if(isFound == true){
            time7.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time7.setText("X");
        }

        isFound = KQ.contains("11:30");
        if(isFound == true){
            time8.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time8.setText("X");
        }

        isFound = KQ.contains("12:00");
        if(isFound == true){
            time9.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time9.setText("X");
        }

        isFound = KQ.contains("13:00");
        if(isFound == true){
            time10.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time10.setText("X");
        }

        isFound = KQ.contains("13:30");
        if(isFound == true){
            time11.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time11.setText("X");
        }

        isFound = KQ.contains("14:00");
        if(isFound == true){
            time12.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time12.setText("X");
        }

        isFound = KQ.contains("14:30");
        if(isFound == true){
            time13.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time13.setText("X");
        }

        isFound = KQ.contains("15:00");
        if(isFound == true){
            time14.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time14.setText("X");
        }

        isFound = KQ.contains("15:30");
        if(isFound == true){
            time15.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time15.setText("X");
        }

        isFound = KQ.contains("16:00");
        if(isFound == true){
            time16.setBackgroundTintList(ColorStateList.valueOf(0xFFd1462e));
            time16.setText("X");
        }


    }

}