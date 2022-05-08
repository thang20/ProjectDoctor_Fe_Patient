package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.ItemNewHomeoneDataHospital;
import com.example.myapp.data.ItemNewHomeoneDataHospitalAdapter;
import com.example.myapp.data.ItemNewHomeoneDataMedical;
import com.example.myapp.data.ItemNewHomeoneDataMedicalAdapter;
import com.example.myapp.home.AllHospital;

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

public class ProfileRecodMedical extends AppCompatActivity {
    RecyclerView rcvAllMedical;
    ImageView back;
    ItemNewHomeoneDataMedicalAdapter itemNewHomeoneDataMedicalAdapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_recod_medical);
        rcvAllMedical = findViewById(R.id.rcv_profile_Mymedicalrecord);
        back = findViewById(R.id.imv_profile_Mymedicalrecord_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(ProfileRecodMedical.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        rcvAllMedical.setLayoutManager(new LinearLayoutManager(ProfileRecodMedical.this));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProfileRecodMedical.this, DividerItemDecoration.VERTICAL);
//        rcvAllMedical.addItemDecoration(itemDecoration);
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

        Apiservice.apiservice.allrecodmedical(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewHomeoneDataMedical> list = new ArrayList<ItemNewHomeoneDataMedical>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        JSONObject obj = new JSONObject(datajson);

                        String test = obj.get("test").toString();
                        String kq = obj.get("kq").toString();
                        String drug = obj.get("drug").toString();
                        String namedt = obj.get("namedt").toString();
                        String phonedt = obj.get("phonedt").toString();
                        String hospital = obj.get("hospital").toString();
                        String spec = obj.get("spec").toString();
                        String time = obj.get("time").toString();


                        list.add(new ItemNewHomeoneDataMedical(test,
                                kq, drug, hospital, spec, time, namedt, phonedt));







                    }
                    progressDialog.dismiss();
                    itemNewHomeoneDataMedicalAdapter = new ItemNewHomeoneDataMedicalAdapter(ProfileRecodMedical.this ,list);
                    rcvAllMedical.setAdapter(itemNewHomeoneDataMedicalAdapter);


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