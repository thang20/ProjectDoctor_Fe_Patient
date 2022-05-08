package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapp.MyApplication;
import com.example.myapp.ProfileFragment;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
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

public class Withdraw extends AppCompatActivity {
    ImageView back;
    Button withDraw;
    EditText money;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        back = findViewById(R.id.imv_carepay_active_dw_w_back);
        withDraw = findViewById(R.id.btn_carepay_active_dw_w_withdraw);
        money = findViewById(R.id.edt_carepay_active_dw_w_money);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        withDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (money.getText().toString().trim().length() == 0) {
                    Toast.makeText(Withdraw.this, "Please input the amount", Toast.LENGTH_LONG).show();
                } else {

                    progressDialog = new ProgressDialog(Withdraw.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);
                    Float smoney = Float.parseFloat(money.getText().toString());
                    String semail = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                    String currentDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
                    String currentTime = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());

                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {

                        jsonObject = new JSONObject();
                        jsonObject.put("email", semail);
                        jsonObject.put("money", smoney);
                        jsonObject.put("time", currentTime);
                        jsonObject.put("date", currentDate);
                        jsonObject.put("k", "0");
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.updatemoneydonation(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            progressDialog.dismiss();
                            String notice = null;
                            try {
                                notice = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (notice.equals("error")) {
                                Toast.makeText(Withdraw.this, "Error", Toast.LENGTH_LONG).show();


                            } else {
                                MyApplication.getMyApplication().getDataUser().setMoneyStatic(Float.parseFloat(notice));
                                ;
                                Toast.makeText(Withdraw.this, "Withdrawal successful", Toast.LENGTH_LONG).show();
                                ProfileFragment profileFragment = new ProfileFragment();
                                money.setText("");
                                money.setText(null);
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(Withdraw.this, "Fail call API", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
    }
}