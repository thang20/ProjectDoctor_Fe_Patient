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
import com.example.myapp.data.ItemNewDataService;
import com.example.myapp.data.ItemNewDataServiceAdapter;
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

public class Services extends AppCompatActivity {
    ImageView back;
    RecyclerView allServices;
    ItemNewDataServiceAdapter itemNewDataServiceAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        back = findViewById(R.id.imv_home_home8_back);
        allServices = findViewById(R.id.rcv_home_home8_allServices);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        progressDialog = new ProgressDialog(Services.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        allServices.setLayoutManager(new LinearLayoutManager(Services.this));
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

        Apiservice.apiservice.allservice(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                String notice = "";
                progressDialog.dismiss();
                try {
                    notice = response.body().string();
                    List<ItemNewDataService> list = new ArrayList<ItemNewDataService>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);

                        String content = obj.get("content").toString();
                        String money = obj.get("money").toString();
                        String image = obj.get("image").toString();
                        String hName = obj.get("hName").toString();
                        list.add(new ItemNewDataService(content,
                                money, image, hName));


                    }
                    itemNewDataServiceAdapter = new ItemNewDataServiceAdapter(Services.this ,list);
                    allServices.setAdapter(itemNewDataServiceAdapter);


                } catch (IOException | JSONException e) {
                    e.printStackTrace();

                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }
}