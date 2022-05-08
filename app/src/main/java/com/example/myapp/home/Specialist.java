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
import com.example.myapp.data.ItemNewHomeoneDataRelative;
import com.example.myapp.data.ItemNewHomeoneDataRelativeAdapter;
import com.example.myapp.data.ItemNewHomeoneDataSpec;
import com.example.myapp.data.ItemNewHomeoneDataSpecAdapter;

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

public class Specialist extends AppCompatActivity {
    RecyclerView specialist;
    ImageView back;
    ItemNewHomeoneDataSpecAdapter itemNewHomeoneDataRelativeAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist);
        specialist = findViewById(R.id.rcv_home_home1_specialist);
        back = findViewById(R.id.imv_home_home1_spec_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        progressDialog = new ProgressDialog(Specialist.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);
        specialist.setLayoutManager(new LinearLayoutManager(Specialist.this));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        hospital.addItemDecoration(itemDecoration);

        String hospital =  MyApplication.getMyApplication().getDataUser().getHomeOneNameHospital();
        JSONObject jsonObject = new JSONObject();
        String jsonStr = null;
        try {

            jsonObject = new JSONObject();
            jsonObject.put("hospital", hospital);
            jsonStr = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

        Apiservice.apiservice.dataonespec(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                progressDialog.dismiss();
                try {
                    notice = response.body().string();
                    List<ItemNewHomeoneDataSpec> list = new ArrayList<ItemNewHomeoneDataSpec>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);

                        String name1 = obj.get("name1").toString();
                        String name2 = obj.get("name2").toString();





                        list.add(new ItemNewHomeoneDataSpec(name1, name2));






                    }

                    itemNewHomeoneDataRelativeAdapter = new ItemNewHomeoneDataSpecAdapter(Specialist.this ,list);
                    specialist.setAdapter(itemNewHomeoneDataRelativeAdapter);


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