package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.New_interface.Create_New_Post;
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

public class ProfileDataCommet extends AppCompatActivity {
    Button send;
    ImageView back;
    EditText qa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_commet);
        send = findViewById(R.id.btn_profile_comment_send);
        back = findViewById(R.id.imv_profile_comment_back);
        qa = findViewById(R.id.edt_profile_schedule_remind);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                String hoi = qa.getText().toString();
                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {

                    jsonObject = new JSONObject();
                    jsonObject.put("email", semail);
                    jsonObject.put("hoi", hoi);
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.qa(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                Toast.makeText(ProfileDataCommet.this, "Success", Toast.LENGTH_LONG).show();
                                onBackPressed();
                                finish();
                            }else {
                                Toast.makeText(ProfileDataCommet.this, "Fail to Post", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ProfileDataCommet.this, "Fail to Call API", Toast.LENGTH_LONG).show();

                    }
                });



            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}