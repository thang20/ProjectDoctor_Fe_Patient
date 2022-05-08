package com.example.myapp.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.ItemNewDataPost;
import com.example.myapp.data.ItemNewDataPostAdapter;
import com.example.myapp.data.ItemNewDataQADoctor;
import com.example.myapp.data.ItemNewDataQADoctorAdapter;

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

public class QADoctor extends AppCompatActivity {
    RecyclerView allQA;
    ImageView back;
    ItemNewDataQADoctorAdapter  itemNewDataQADoctorAdapter;
    EditText qaForDoctor;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qadoctor);
        back = findViewById(R.id.imv_home_home7_back);
        allQA = findViewById(R.id.rcv_home_home7_QA);
        qaForDoctor = findViewById(R.id.edt_home_home7_qaForDoctor);
        qaForDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QADoctor.this, PostQAToDoctor.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        progressDialog = new ProgressDialog(QADoctor.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);

        allQA.setLayoutManager(new LinearLayoutManager(QADoctor.this));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(QADoctor.this, DividerItemDecoration.VERTICAL);
        allQA.addItemDecoration(itemDecoration);

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

        Apiservice.apiservice.qadoctor(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewDataQADoctor> list = new ArrayList<ItemNewDataQADoctor>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String content = obj.get("content").toString();
                        String date = obj.get("date").toString();
                        String time = obj.get("time").toString();
                        String imageface = obj.get("imageface").toString();
                        String fulname = obj.get("fullname").toString();
                        String idpeople = obj.get("idpeople").toString();
                        String idpost = obj.get("idpost").toString();



                        list.add(new ItemNewDataQADoctor(imageface,
                                time,date, fulname, content , idpeople, idpost));







                    }
                    itemNewDataQADoctorAdapter = new ItemNewDataQADoctorAdapter(QADoctor.this ,list);
                    allQA.setAdapter(itemNewDataQADoctorAdapter);
                    progressDialog.dismiss();


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