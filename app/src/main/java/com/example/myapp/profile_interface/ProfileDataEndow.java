package com.example.myapp.profile_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDataEndow extends AppCompatActivity {
    ImageView back;
    EditText code;
    Button check;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_endow);
        back = findViewById(R.id.imv_profile_endow_back);
        code = findViewById(R.id.edt_profile_relative_add_relative);
        check = findViewById(R.id.btn_profile_endow_check);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog = new ProgressDialog(ProfileDataEndow.this);
                progressDialog.show();
                progressDialog.setContentView(R.layout.process_dialog);

                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                JSONObject jsonObject = new JSONObject();
                String jsonStr = null;
                try {
                    String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonObject.put("code", code.getText().toString());
                    jsonStr = jsonObject.toString();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                Apiservice.apiservice.endow(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();
                            if(notice.equals("0")){
                                progressDialog.dismiss();
                                Toast.makeText(ProfileDataEndow.this, "Code has expired", Toast.LENGTH_LONG).show();
                            }else {
                                progressDialog.dismiss();
                                Float money = MyApplication.myApplication.getDataUser().getMoneyStatic();
                                MyApplication.myApplication.getDataUser().setMoneyStatic(money + Float.valueOf(notice));
                                Toast.makeText(ProfileDataEndow.this, "congrats you got "+ notice + " USD", Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(ProfileDataEndow.this, "Fail to call API", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}