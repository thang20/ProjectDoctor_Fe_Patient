package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.New_interface.Create_New_Post;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
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

public class PostQAToDoctor extends AppCompatActivity {
    ImageView back;
    TextView post;
    EditText qa;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_qato_doctor);
        back = findViewById(R.id.imv_home_home7_backtoallqa);
        post = findViewById(R.id.txt_home_home7_postaqa);
        qa = findViewById(R.id.edt_home_home7_qatext);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();


            }
        });
        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post.setTextColor(Color.parseColor("#2BC4BF"));
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(PostQAToDoctor.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);

                JSONObject jsonObject= new JSONObject();
                String jsonStr = null;
                String email =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                try {

                    jsonObject=new JSONObject();
                    jsonObject.put("content",qa.getText().toString());

                    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());


                    jsonObject.put("email",email);
                    jsonObject.put("date",currentDate);
                    jsonObject.put("time",currentTime);



                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.postanewqa(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;
                        progressDialog.dismiss();

                        try {
                            notice = response.body().string();
                            if(notice.equals("success")){
                                Toast.makeText(PostQAToDoctor.this, "Success", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(PostQAToDoctor.this, QADoctor.class));
                                finish();

                            }else {
                                Toast.makeText(PostQAToDoctor.this, "Fail to Post", Toast.LENGTH_LONG).show();
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(PostQAToDoctor.this, "Fail to Call API", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });
    }
}