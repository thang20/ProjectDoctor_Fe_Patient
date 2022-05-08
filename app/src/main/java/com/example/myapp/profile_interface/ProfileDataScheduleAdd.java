package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDataScheduleAdd extends AppCompatActivity {
    EditText remind, time, date;
    DatePickerDialog datepicker;
    TimePickerDialog timepicker;
    ImageView back;
    Button add;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_schedule_add);

        date = (EditText) findViewById(R.id.edt_profile_schedule_date);
        time=(EditText) findViewById(R.id.edt_profile_schedule_time);
        remind=(EditText) findViewById(R.id.edt_profile_schedule_remind);
        add = (Button) findViewById(R.id.btn_profile_relative_add_save);
        back = (ImageView) findViewById(R.id.imv_profile_schedule_back);


        date.setInputType(InputType.TYPE_NULL);
        time.setInputType(InputType.TYPE_NULL);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final java.util.Calendar cldr = java.util.Calendar.getInstance();
                int day = cldr.get(java.util.Calendar.DAY_OF_MONTH);
                int month = cldr.get(java.util.Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                datepicker = new DatePickerDialog(ProfileDataScheduleAdd.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datepicker.show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final java.util.Calendar cldr = java.util.Calendar.getInstance();
                int hour1 = cldr.get(Calendar.HOUR_OF_DAY);
                int minute1 = cldr.get(Calendar.MINUTE);
                // date picker dialog
                timepicker = new TimePickerDialog(ProfileDataScheduleAdd.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                        cldr.set(0, 0, 0, i, i1);
                        time.setText(simpleDateFormat.format(cldr.getTime()));
                    }
                }, hour1, minute1, true);
                timepicker.show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(ProfileDataScheduleAdd.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);

                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {
                    String email =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonObject.put("day", date.getText().toString());
                    jsonObject.put("time", time.getText().toString());
                    jsonObject.put("remind", remind.getText().toString());
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.profilescheduleadd(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                Toast.makeText(ProfileDataScheduleAdd.this, "Success", Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), ProfileDataSchedule.class));
                                finish();
                            }else {
                                progressDialog.dismiss();
                                Toast.makeText(ProfileDataScheduleAdd.this, "Fail to Save", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileDataScheduleAdd.this, "Fail to call API", Toast.LENGTH_LONG).show();

                    }
                });





            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileDataSchedule.class));
                finish();
            }
        });



    }
}