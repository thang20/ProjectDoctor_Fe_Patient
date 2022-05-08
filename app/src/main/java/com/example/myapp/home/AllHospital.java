package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.myapp.data.ItemNewDataHospital;
import com.example.myapp.data.ItemNewDataHospitalAdapter;
import com.example.myapp.data.ItemNewHomeoneDataHospital;
import com.example.myapp.data.ItemNewHomeoneDataHospitalAdapter;

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

public class AllHospital extends AppCompatActivity {
    RecyclerView rcvAllHospital;
    ImageView back;
    ItemNewHomeoneDataHospitalAdapter itemNewHomeoneDataHospitalAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_hospital);
        rcvAllHospital = findViewById(R.id.rcv_home_home1_allHospital);
        back = findViewById(R.id.imv_home_home1_allHospital_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(AllHospital.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        rcvAllHospital.setLayoutManager(new LinearLayoutManager(AllHospital.this));
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

        Apiservice.apiservice.dataonehospital(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewHomeoneDataHospital> list = new ArrayList<ItemNewHomeoneDataHospital>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);

                        String image = obj.get("image").toString();
                        String name = obj.get("name").toString();
                        String address = obj.get("address").toString();
                        list.add(new ItemNewHomeoneDataHospital(name,
                                address, image));


                    }
                    progressDialog.dismiss();
                    itemNewHomeoneDataHospitalAdapter = new ItemNewHomeoneDataHospitalAdapter(AllHospital.this ,list);
                    rcvAllHospital.setAdapter(itemNewHomeoneDataHospitalAdapter);


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