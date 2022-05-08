package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

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

public class ProfileDatascreenlock extends AppCompatActivity {
    ImageView back;
    Switch lock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_datascreenlock);
        back = findViewById(R.id.imv_profile_screenlock_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        lock = findViewById(R.id.profile_setlock);
        String status =  MyApplication.getMyApplication().getDataUser().getSetlock();
        if(status.equals("0")){
            lock.setChecked(false);
        }else {
            lock.setChecked(true);
        }
        lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
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

                    Apiservice.apiservice.turnonlock(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();
                                if(notice.equals("success")){
                                    Toast.makeText(ProfileDatascreenlock.this, "turned on lock", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(ProfileDatascreenlock.this, "Fail", Toast.LENGTH_LONG).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(ProfileDatascreenlock.this, "Fail to Call API", Toast.LENGTH_LONG).show();
                        }
                    });
                }else {
                    //Toast.makeText(StatusCall.this, "turned on notifications from calls", Toast.LENGTH_LONG).show();

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

                    Apiservice.apiservice.turnofflock(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();
                                if(notice.equals("success")){
                                    Toast.makeText(ProfileDatascreenlock.this, "turned off lock", Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(ProfileDatascreenlock.this, "Fail", Toast.LENGTH_LONG).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(ProfileDatascreenlock.this, "Fail to Call API", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
}