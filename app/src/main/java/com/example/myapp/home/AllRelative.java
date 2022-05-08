package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.ItemNewHomeoneDataHospital;
import com.example.myapp.data.ItemNewHomeoneDataHospitalAdapter;
import com.example.myapp.data.ItemNewHomeoneDataRelative;
import com.example.myapp.data.ItemNewHomeoneDataRelativeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllRelative extends AppCompatActivity {
    RecyclerView rcvAllRelative;
    ImageView back;
    ItemNewHomeoneDataRelativeAdapter itemNewHomeoneDataRelativeAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_relative);

        rcvAllRelative = findViewById(R.id.rcv_home_home1_allRelative);
        back = findViewById(R.id.imv_home_home1_allRelative_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( MyApplication.myApplication.getDataUser().getCheckTestAtHome().equals("1")){
                    startActivity(new Intent(getApplicationContext(), TakeDateTime.class));
                    finish();
                }
                else {
                    onBackPressed();
                }
            }
        });

        progressDialog = new ProgressDialog(AllRelative.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        rcvAllRelative.setLayoutManager(new LinearLayoutManager(AllRelative.this));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        hospital.addItemDecoration(itemDecoration);

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

        Apiservice.apiservice.dataonerelative(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewHomeoneDataRelative> list = new ArrayList<ItemNewHomeoneDataRelative>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);

                        String name = obj.get("name").toString();
                        String year = obj.get("age").toString();
                        String address = obj.get("address").toString();
                        String gender = obj.get("gender").toString();
                        String relative = obj.get("relative").toString();
                        String phone = obj.get("phone").toString();


                        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        String[] parts = currentDate.split("/");
                        String result = parts[2];

                        int tt = Integer.parseInt(result) - Integer.parseInt(year);
                        String age = String.valueOf(tt);

                        list.add(new ItemNewHomeoneDataRelative(name,
                                gender, age, relative, phone, address));






                    }
                    progressDialog.dismiss();
                    itemNewHomeoneDataRelativeAdapter = new ItemNewHomeoneDataRelativeAdapter(AllRelative.this ,list);
                    rcvAllRelative.setAdapter(itemNewHomeoneDataRelativeAdapter);


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
}