package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.ItemProfileDataHistory;
import com.example.myapp.data.ItemProfileDataHistoryAdapter;
import com.example.myapp.data.ItemProfileDataRemind;
import com.example.myapp.data.ItemProfileDataRemindAdapter;

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

public class ProfileHistory extends AppCompatActivity {
    RecyclerView recyclerViewSchedule;
    ImageView back;
    ItemProfileDataHistoryAdapter itemProfileDataHistoryAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_history);

        recyclerViewSchedule = findViewById(R.id.rcv_profile_history);
        back = findViewById(R.id.imw_profile_history_back);
        recyclerViewSchedule.setLayoutManager(new LinearLayoutManager(ProfileHistory.this));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ProfileDataRemind.this, DividerItemDecoration.VERTICAL);
//        recyclerViewSchedule.addItemDecoration(itemDecoration);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        progressDialog = new ProgressDialog(ProfileHistory.this);
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

        Apiservice.apiservice.profileshistory(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = null;
                try {
                    notice = response.body().string();
                    List<ItemProfileDataHistory> list = new ArrayList<>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String remind = obj.get("remind").toString();
                        String time = obj.get("time").toString();
                        String date = obj.get("date").toString();

                        String type = obj.get("type").toString();
                        String money = obj.get("money").toString();
                        String moneyadd = obj.get("moneyadd").toString();



                        list.add(new ItemProfileDataHistory(remind,
                                time,date, money, moneyadd, type ));



                        itemProfileDataHistoryAdapter = new ItemProfileDataHistoryAdapter(ProfileHistory.this ,list);
                        recyclerViewSchedule.setAdapter(itemProfileDataHistoryAdapter);
                        progressDialog.dismiss();



                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}