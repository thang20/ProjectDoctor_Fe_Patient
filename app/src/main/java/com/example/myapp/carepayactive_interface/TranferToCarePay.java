package com.example.myapp.carepayactive_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapp.Home;
import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.carepaynonactive_interface.CheckFaceDone;
import com.example.myapp.carepaynonactive_interface.CheckPaper;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.ProfileDataChangepass;
import com.example.myapp.profile_interface.ProfileRelativeData;
import com.example.myapp.profile_interface.ProfileRelativeDataAdd;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TranferToCarePay extends AppCompatActivity {
    ImageView back, img;
    EditText numberPhone, am, sran;
    TextView inf, money, name;
    Button next;
    ProgressDialog progressDialog;

    Integer CHECK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranfer_to_care_pay);
        back = findViewById(R.id.imv_carepay_active_transfer_tocarepay_back);
        img = findViewById(R.id.imv_carepay_active_transfer_tocarepay_img);
        numberPhone = findViewById(R.id.edt_carepay_active_transfer_tocarepay_phone);
        am = findViewById(R.id.edt_carepay_active_transfer_tocarepay_am);
        sran = findViewById(R.id.edt_carepay_active_transfer_tocarepay_sran);
        inf = findViewById(R.id.txt_carepay_active_transfer_tocarepay_inf);
        money = findViewById(R.id.txt_carepay_active_transfer_tocarepay_money);
        name = findViewById(R.id.txt_carepay_active_transfer_tocarepay_name);
        next = findViewById(R.id.btn_carepay_active_transfer_tocarepay_next);
        numberPhone.setFocusable(true);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        inf.setText(String.valueOf(MyApplication.getMyApplication().getDataUser().getPhoneStatic()) +"-"+ MyApplication.getMyApplication().getDataUser().getFullNameStatic());
        sran.setText(MyApplication.getMyApplication().getDataUser().getFullNameStatic() + " transfer");
        money.setText(String.valueOf(MyApplication.getMyApplication().getDataUser().getMoneyStatic()) + " USD");

        numberPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                String check = numberPhone.getText().toString();
                if(check.length()>=10&&check.length()<=13) {
                    CHECK = 1;
                    progressDialog = new ProgressDialog(TranferToCarePay.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);
                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                        jsonObject = new JSONObject();
                        jsonObject.put("phone", check);

                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.takeinftranfer(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                                try {
                                    progressDialog.dismiss();
                                    notice = response.body().string();
                                    if(notice.equals("fail")){
                                        CHECK = 0;
                                        Toast.makeText(TranferToCarePay.this, "CarePay unregistered phone number", Toast.LENGTH_LONG).show();

                                    }else {
                                        CHECK = 1;
                                        JSONObject obj = new JSONObject(notice);
                                        if(obj.get("image").toString().length() != 0) {
                                            //img.setImageURI(Uri.parse(obj.get("image").toString()));
                                            Glide.with(TranferToCarePay.this).load(MyApplication.getMyApplication().getDataUser().getAPI()+obj.get("image").toString()).into(img);
                                        }else {
                                            img.setImageResource(R.drawable.user);
                                        }
                                        if(obj.get("fullname").toString().length() != 0) {
                                            name.setText(obj.get("fullname").toString());
                                        }
                                    }


                                } catch (IOException | JSONException e) {
                                    e.printStackTrace();
                                }

                            }




                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(TranferToCarePay.this, "Fail to call API", Toast.LENGTH_LONG).show();

                        }
                    });
                }else {
                    CHECK = 0;
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numberPhone.getText().toString().length()==0||am.getText().toString().length()==0){
                    Toast.makeText(TranferToCarePay.this, "Please enter all the information", Toast.LENGTH_LONG).show();
                }else if(CHECK.equals(0)){
                    Toast.makeText(TranferToCarePay.this, "CarePay unregistered phone number", Toast.LENGTH_LONG).show();
                }
                else {
                    MyApplication.getMyApplication().getDataUser().setTransferCarePayPhone(numberPhone.getText().toString());
                    MyApplication.getMyApplication().getDataUser().setTransferCarePayName(name.getText().toString());
                    MyApplication.getMyApplication().getDataUser().setTransferCarePayContent(sran.getText().toString());
                    MyApplication.getMyApplication().getDataUser().setTransferCarePayAM(am.getText().toString());
                    Intent intent = new Intent(TranferToCarePay.this, CheckTranferToCarePay.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if( MyApplication.getMyApplication().getDataUser().getTransferCarePayCHECK()==1) {
            numberPhone.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayPhone());
            am.setText(MyApplication.getMyApplication().getDataUser().getTransferCarePayAM());
        }
    }
}