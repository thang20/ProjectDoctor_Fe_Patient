package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.ProfileFragment;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;

import org.json.JSONException;
import org.json.JSONObject;

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

public class PointFagment extends AppCompatActivity {
    ImageView imvback;
    Button btnwd;
    EditText edtpoint;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_fagment);
        imvback = findViewById(R.id.imv_profile_point_back);
        btnwd = findViewById(R.id.btn_profile_point_wd);
        edtpoint = findViewById(R.id.edt_profile_pointwd);

        imvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtpoint.getText().toString().trim().length() == 0) {
                    Toast.makeText(PointFagment.this, "Please input the points", Toast.LENGTH_LONG).show();
                } else {
                    ProgressDialog progressDialog;
                    progressDialog = new ProgressDialog(PointFagment.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);

                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    Integer spoints = Integer.parseInt(edtpoint.getText().toString());
                    String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {

                        jsonObject = new JSONObject();
                        jsonObject.put("email", semail);
                        jsonObject.put("points", spoints);
                        jsonObject.put("time", currentTime);
                        jsonObject.put("date", currentDate);
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.updatepoint(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            String notice = null;
                            try {
                                notice = response.body().string();
                                if (notice.equals("error")) {
                                    Toast.makeText(PointFagment.this, "Error", Toast.LENGTH_LONG).show();


                                } else if(notice.equals("fail")){

                                    Toast.makeText(PointFagment.this, "You don't have enough money to redeem, please top up more", Toast.LENGTH_LONG).show();
                                }
                                else {

                                    JSONObject obj = new JSONObject(notice);
                                    MyApplication.getMyApplication().getDataUser().setMoneyStatic(Float.parseFloat(obj.get("money").toString()));
                                    MyApplication.getMyApplication().getDataUser().setAccumulatedPointsStatic(Integer.parseInt(obj.get("accumulatedpoints").toString()));


                                    Toast.makeText(PointFagment.this, "Success", Toast.LENGTH_LONG).show();
                                    ProfileFragment profileFragment = new ProfileFragment();
                                    edtpoint.setText("");
                                    progressDialog.dismiss();
                                    onBackPressed();

                                }
                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PointFagment.this, "Fail call API", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
    }
}