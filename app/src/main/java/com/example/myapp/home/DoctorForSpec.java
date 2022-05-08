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
import com.example.myapp.data.ItemNewDoctorSpec;
import com.example.myapp.data.ItemNewDoctorSpecAdapter;
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

public class DoctorForSpec extends AppCompatActivity {
    RecyclerView rcvAllHospital;
    ImageView back;
    ItemNewDoctorSpecAdapter itemNewDoctorSpecAdapter;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_for_spec);
        rcvAllHospital = findViewById(R.id.rcv_home_home1_allDoctorSpec);
        back = findViewById(R.id.imv_home_home1_allDoctorSpec_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(DoctorForSpec.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        rcvAllHospital.setLayoutManager(new LinearLayoutManager(DoctorForSpec.this));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        hospital.addItemDecoration(itemDecoration);

        String spec =  MyApplication.getMyApplication().getDataUser().getHomeOneSpecHospital();
        JSONObject jsonObject = new JSONObject();
        String jsonStr = null;
        try {

            jsonObject = new JSONObject();
            jsonObject.put("spec", spec);
            jsonStr = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

        Apiservice.apiservice.doctorspecall(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewDoctorSpec> list = new ArrayList<ItemNewDoctorSpec>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);

                        String name = obj.get("name").toString();
                        String phone = obj.get("phone").toString();
                        String address = obj.get("address").toString();
                        String face = obj.get("face").toString();
                        String hospital = obj.get("hospital").toString();
                        String chungchi = obj.get("chungchi").toString();
                        list.add(new ItemNewDoctorSpec(face,
                                name, hospital, address, phone, chungchi));







                    }
                    progressDialog.dismiss();
                    itemNewDoctorSpecAdapter = new ItemNewDoctorSpecAdapter(DoctorForSpec.this ,list);
                    rcvAllHospital.setAdapter(itemNewDoctorSpecAdapter);


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