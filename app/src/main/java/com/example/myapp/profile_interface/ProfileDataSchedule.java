package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapp.Adam;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.ItemProfileDataSchedule;
import com.example.myapp.data.ItemProfileDataScheduleAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDataSchedule extends AppCompatActivity {
    RecyclerView recyclerViewSchedule;
    ImageView back;
    TextView add;
    ItemProfileDataScheduleAdapter itemProfileDataScheduleAdapter;
    ProgressDialog progressDialog;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_schedule);

        recyclerViewSchedule = findViewById(R.id.rcv_profile_schedule);
        back = findViewById(R.id.imw_profile_schedule_back);
        add = findViewById(R.id.txt_profile_schedule_add);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(ProfileDataSchedule.this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProfileDataSchedule.this, DividerItemDecoration.VERTICAL);
        recyclerViewSchedule.addItemDecoration(itemDecoration);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(ProfileDataSchedule.this, Adam.class);
        calendar = Calendar.getInstance();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });




        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileDataScheduleAdd.class));
                finish();
            }
        });

        progressDialog = new ProgressDialog(ProfileDataSchedule.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();

        JSONObject jsonObject = new JSONObject();
        String jsonStr = null;
        try {

            jsonObject = new JSONObject();
            jsonObject.put("email", semail);
            jsonStr = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

        Apiservice.apiservice.profileschedule(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = null;
                try {
                    notice = response.body().string();
                    List<ItemProfileDataSchedule> list = new ArrayList<>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String remind = obj.get("remind").toString();
                        String time = obj.get("time").toString();
                        String date = obj.get("date").toString();
                        String gio = obj.get("gio").toString();
                        String phut = obj.get("phut").toString();
                        if(i == 2){
                            Log.d("123", gio);
                            Log.d("123", phut);
                            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(gio));
                            calendar.set(Calendar.MINUTE,  Integer.valueOf(phut));
                            String currentTime = new SimpleDateFormat("mm", Locale.getDefault()).format(new Date());
                            Log.d("123", currentTime);
                            if(currentTime.equals(String.valueOf(Integer.valueOf(phut)- 1))) {
                                pendingIntent = PendingIntent.getBroadcast(ProfileDataSchedule.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                                        0, pendingIntent);
                            }
                        }


                        time = " " + time;
                        date = "Date: " + date;

                        list.add(new ItemProfileDataSchedule(remind,
                                time,date ));


                        itemProfileDataScheduleAdapter = new ItemProfileDataScheduleAdapter(ProfileDataSchedule.this ,list);
                        recyclerViewSchedule.setAdapter(itemProfileDataScheduleAdapter);
                        progressDialog.dismiss();



                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    @Override
    public void onRestart() {
        super.onRestart();


        String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();

        JSONObject jsonObject = new JSONObject();
        String jsonStr = null;
        try {

            jsonObject = new JSONObject();
            jsonObject.put("email", semail);
            jsonStr = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

        Apiservice.apiservice.profileschedule(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = null;
                try {
                    notice = response.body().string();
                    List<ItemProfileDataSchedule> list = new ArrayList<>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String remind = obj.get("remind").toString();
                        String time = obj.get("time").toString();
                        String date = obj.get("date").toString();

                        time = " " + time;
                        date = "Date: " + date;

                        list.add(new ItemProfileDataSchedule(remind,
                                time,date ));


                        itemProfileDataScheduleAdapter = new ItemProfileDataScheduleAdapter(ProfileDataSchedule.this ,list);
                        recyclerViewSchedule.setAdapter(itemProfileDataScheduleAdapter);
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


}