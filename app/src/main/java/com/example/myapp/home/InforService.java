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
import com.example.myapp.Login;
import com.example.myapp.MainActivity;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.profile_interface.ProfileDataChangepass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforService extends AppCompatActivity {
    ImageView back;
    TextView nameH, content, money;
    Button next;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_service);
        back = findViewById(R.id.imv_home_homefinal_backinfservice);
        nameH = findViewById(R.id.txt__home_homefinal_nameH);
        content = findViewById(R.id.txt__home_homefinal_content);
        money = findViewById(R.id.txt__home_homefinal_point);
        next = findViewById(R.id.btn_home_homefinal_next);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        nameH.setText(MyApplication.getMyApplication().getDataUser().getServicehName());
        content.setText(MyApplication.getMyApplication().getDataUser().getServiceContent());
        money.setText(MyApplication.getMyApplication().getDataUser().getServiceMoney() + " Point");

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(InforService.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);

                String smoney = MyApplication.getMyApplication().getDataUser().getServiceMoney();
                String scontent = content.getText().toString();
                String snameH = nameH.getText().toString();

                String semail = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {

                    jsonObject = new JSONObject();
                    jsonObject.put("email", semail);
                    jsonObject.put("money", smoney);
                    jsonObject.put("content", scontent);
                    jsonObject.put("nameH", snameH);
                    jsonObject.put("time", currentTime);
                    jsonObject.put("date", currentDate);
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.buyservice(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;
                        progressDialog.dismiss();

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                Toast.makeText(InforService.this, "Success", Toast.LENGTH_LONG).show();

                                startActivity(new Intent(InforService.this, Home.class));
                                finish();
                            }else {
                                Toast.makeText(InforService.this, "Fail to Register", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();

                    }
                });

            }
        });
    }
}