package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
import com.example.myapp.data.ItemProfileData01;
import com.example.myapp.data.ItemProfileData01Adapter;
import com.example.myapp.data.ItemProfileDataRelative;
import com.example.myapp.data.ItemProfileDataRelativeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRelativeData extends AppCompatActivity {
    RecyclerView recyclerViewRelative;
    ImageView back;
    TextView add;
    ItemProfileDataRelativeAdapter itemProfileDataRelativeAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_relative_data);
        recyclerViewRelative = findViewById(R.id.rcv_profile_relative);
        add = findViewById(R.id.txt_profile_relative_add);
        recyclerViewRelative.setLayoutManager(new LinearLayoutManager(ProfileRelativeData.this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProfileRelativeData.this, DividerItemDecoration.VERTICAL);
        recyclerViewRelative.addItemDecoration(itemDecoration);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileRelativeDataAdd.class));
                finish();
            }
        });
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ProfileRelativeData.this);
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

        Apiservice.apiservice.profilerelative(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = null;
                try {
                    notice = response.body().string();
                    List<ItemProfileDataRelative> list = new ArrayList<>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String relative = obj.get("relative").toString();
                        String fullname = obj.get("fullname").toString();
                        String relativedateofbirth = obj.get("relativedateofbirth").toString();
                        String relativephone = obj.get("relativephone").toString();
                        String relativecity = obj.get("relativecity").toString();
                        String relativedistrict = obj.get("relativedistrict").toString();
                        String relativeward = obj.get("relativeward").toString();
                        String relativeaddress = obj.get("relativeaddress").toString();


                        String nameRelative = fullname + " (" + relative + ")";
                        String address = "House number : " + relativeaddress + " Ward : " + relativeward + " District : " + relativedistrict + " Province: " +relativecity;

                        list.add(new ItemProfileDataRelative(nameRelative,
                                relativedateofbirth,relativephone, address ));


                        itemProfileDataRelativeAdapter = new ItemProfileDataRelativeAdapter(ProfileRelativeData.this ,list);
                        recyclerViewRelative.setAdapter(itemProfileDataRelativeAdapter);
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

        back = findViewById(R.id.txt_profile_data_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();

        progressDialog = new ProgressDialog(ProfileRelativeData.this);
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

        Apiservice.apiservice.profilerelative(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = null;
                try {
                    notice = response.body().string();
                    List<ItemProfileDataRelative> list = new ArrayList<>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String relative = obj.get("relative").toString();
                        String fullname = obj.get("fullname").toString();
                        String relativedateofbirth = obj.get("relativedateofbirth").toString();
                        String relativephone = obj.get("relativephone").toString();
                        String relativecity = obj.get("relativecity").toString();
                        String relativedistrict = obj.get("relativedistrict").toString();
                        String relativeward = obj.get("relativeward").toString();
                        String relativeaddress = obj.get("relativeaddress").toString();


                        String nameRelative = fullname + " (" + relative + ")";
                        String address = "House number : " + relativeaddress + " Ward : " + relativeward + " District : " + relativedistrict + " Province: " +relativecity;

                        list.add(new ItemProfileDataRelative(nameRelative,
                                relativedateofbirth,relativephone, address ));


                        itemProfileDataRelativeAdapter = new ItemProfileDataRelativeAdapter(ProfileRelativeData.this ,list);
                        recyclerViewRelative.setAdapter(itemProfileDataRelativeAdapter);
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

}