package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Home;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestAtHomeFinal extends AppCompatActivity {
    ImageView back;
    Button next;
    TextView name, gender, age, phone, address, hDay, hTime, hService;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_at_home_final);
        back = findViewById(R.id.imv_home_home2_checkAll_back);
        next = findViewById(R.id.btn_home_home2_checkAll_next);
        name = findViewById(R.id.txt_home_home2_checkAll_name);
        gender = findViewById(R.id.txt__home_home2_checkAll_gender);
        age = findViewById(R.id.txt__home_home2_checkAll_age);
        phone = findViewById(R.id.txt__home_home2_checkAll_phone);
        address = findViewById(R.id.txt__home_home2_checkAll_address);
        hDay = findViewById(R.id.txt__home_home2_checkAll_date);
        hTime = findViewById(R.id.txt__home_home2_checkAll_time);
        hService = findViewById(R.id.txt__home_home2_checkAll_service);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        name.setText(MyApplication.getMyApplication().getDataUser().getHomeOneNameUser());
        age.setText(MyApplication.getMyApplication().getDataUser().getHomeOneAgeUser());
        phone.setText(MyApplication.getMyApplication().getDataUser().getHomeOnePhoneUser());
        address.setText(MyApplication.getMyApplication().getDataUser().getHomeTowAddress());
        hDay.setText(MyApplication.getMyApplication().getDataUser().getHomeOneDate());
        hTime.setText(MyApplication.getMyApplication().getDataUser().getHomeOneTime());
        hService.setText(MyApplication.getMyApplication().getDataUser().getHomeOneType());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(TestAtHomeFinal.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);
                String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();

                JSONObject jsonObject = new JSONObject();
                String remind = MyApplication.myApplication.getDataUser().getHomeOneNameUser() + " have Test at home";
                String jsonStr = null;
                try {

                    jsonObject = new JSONObject();
                    jsonObject.put("tt", "testhome");
                    jsonObject.put("email", semail);
                    jsonObject.put("remind",remind);
                    jsonObject.put("date", MyApplication.myApplication.getDataUser().getHomeOneDate());
                    jsonObject.put("time", MyApplication.myApplication.getDataUser().getHomeOneTime());



                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.postremind(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        String notice = null;
                        progressDialog.dismiss();

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                Toast.makeText(TestAtHomeFinal.this, "Appointment successful", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                                finish();
                            }else {
                                Toast.makeText(TestAtHomeFinal.this, "Fail to Appointment", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(TestAtHomeFinal.this, "Fail to Call API", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }
}