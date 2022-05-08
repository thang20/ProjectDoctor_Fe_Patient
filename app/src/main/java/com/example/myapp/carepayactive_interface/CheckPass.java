package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.ProfileDataChangepass;
import com.example.myapp.profile_interface.ProfileDataSchedule;
import com.example.myapp.profile_interface.ProfileDataScheduleAdd;

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

public class CheckPass extends AppCompatActivity {
    ImageView back;
    EditText pass;
    Button next;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_pass);
        back = findViewById(R.id.imv_carepay_active_check_back);
        pass = findViewById(R.id.edt_carepay_active_check_pass);
        next = findViewById(R.id.btn_carepay_active_check_next);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckPass.this, CheckTranferToCarePay.class);
                startActivity(intent);
                finish();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(CheckPass.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);
                String passCheck = pass.getText().toString();

                if (passCheck.equals(MyApplication.getMyApplication().getDataUser().getPassWordStatic())){
                    if( MyApplication.getMyApplication().getDataUser().getCheckIsBank() == 0) {
                        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                        JSONObject jsonObject = new JSONObject();
                        String jsonStr = null;
                        try {
                            String email =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                            jsonObject = new JSONObject();
                            jsonObject.put("email", email);
                            jsonObject.put("phone",  MyApplication.getMyApplication().getDataUser().getTransferCarePayPhone());
                            jsonObject.put("am",  MyApplication.getMyApplication().getDataUser().getTransferCarePayAM());
                            jsonObject.put("time", currentTime);
                            jsonObject.put("date", currentDate);
                            jsonStr = jsonObject.toString();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RequestBody body =
                                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                        Apiservice.apiservice.transfer(body).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String notice = null;

                                try {
                                    notice = response.body().string();
                                    progressDialog.dismiss();
                                    if (notice.equals("success")) {
                                        MyApplication.getMyApplication().getDataUser().setMoneyStatic(MyApplication.getMyApplication().getDataUser().getMoneyStatic() - Float.valueOf( MyApplication.getMyApplication().getDataUser().getTransferCarePayAM()));
                                        Toast.makeText(CheckPass.this, "Transfer success", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(CheckPass.this, CheckPassFinish.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (notice.equals("error")) {
                                        Toast.makeText(CheckPass.this, "You don't have enough money", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(CheckPass.this, "Transfer failed", Toast.LENGTH_LONG).show();
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(CheckPass.this, "Fail to call API", Toast.LENGTH_LONG).show();

                            }
                        });


                    }else if( MyApplication.getMyApplication().getDataUser().getCheckIsBank()==1){

                        progressDialog = new ProgressDialog(CheckPass.this);
                        progressDialog.show();
                        progressDialog.setContentView(R.layout.process_dialog);
                        String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                        String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                        JSONObject jsonObject = new JSONObject();
                        String jsonStr = null;
                        try {
                            String email =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
                            jsonObject = new JSONObject();
                            jsonObject.put("email", email);
                            jsonObject.put("time", currentTime);
                            jsonObject.put("date", currentDate);
                            jsonObject.put("stk",  MyApplication.getMyApplication().getDataUser().getTransferBankPhone());
                            jsonObject.put("am",  MyApplication.getMyApplication().getDataUser().getTransferBankAM());
                            jsonStr = jsonObject.toString();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        RequestBody body =
                                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                        Apiservice.apiservice.transferbank(body).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                String notice = null;

                                try {
                                    progressDialog.dismiss();
                                    notice = response.body().string();
                                    if (notice.equals("success")) {
                                        MyApplication.getMyApplication().getDataUser().setMoneyStatic(MyApplication.getMyApplication().getDataUser().getMoneyStatic() - Float.valueOf(MyApplication.getMyApplication().getDataUser().getTransferBankAM()));
                                        Toast.makeText(CheckPass.this, "Transfer success", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(CheckPass.this, CheckPassFinish.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (notice.equals("error")) {
                                        Toast.makeText(CheckPass.this, "You don't have enough money", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(CheckPass.this, "Transfer failed", Toast.LENGTH_LONG).show();
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(CheckPass.this, "Fail to call API", Toast.LENGTH_LONG).show();

                            }
                        });
                    }




                }else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}