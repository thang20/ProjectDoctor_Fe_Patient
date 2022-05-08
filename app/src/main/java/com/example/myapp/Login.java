package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    EditText mEmail, mPassWord;
    Button mLoginBtn;
    TextView mRegisterTxt;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.edtEmailLogin);
        mPassWord = findViewById(R.id.edtPassWordLogin);
        mLoginBtn = findViewById(R.id.btnLogin);
        mRegisterTxt = findViewById(R.id.txtcreateLogin);
        mProgressBar = findViewById(R.id.progressBarLogin);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                String password = mPassWord.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassWord.setError("PassWord is Required.");
                    return;
                }
                if(password.length() < 6){
                    mPassWord.setError("PassWord Must be >= 6 Characters.");
                    return;
                }
                mProgressBar.setVisibility(View.VISIBLE);

                JSONObject jsonObject= new JSONObject();
                String jsonStr = null;
                try {
                    jsonObject=new JSONObject();
                    jsonObject.put("email",email);
                    jsonObject.put("passWord",password);
                    jsonStr = jsonObject.toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), jsonStr);
                //http://192.168.0.106:5000/login
                Apiservice.apiservice.putLogin(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;

                        try {
                            notice = response.body().string();


                            if (notice.equals("0")) {
                                Toast.makeText(Login.this, "Email and Password Incorrect", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Login.this, "Welcome to MyDoctor", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), Home.class));
                                finish();
                            }
                            //#fullname, email, password, phone, image, dateofbirth, sex, city, township, ward, apartmentnumber, money, accumulatedpoints


                            JSONObject obj = new JSONObject(notice);
                            Log.d("care123", obj.get("email").toString() + "123");
                            MyApplication.getMyApplication().getDataUser().setFullNameStatic(obj.get("fullname").toString());
                            MyApplication.getMyApplication().getDataUser().setEmailStatic(obj.get("email").toString());
                            MyApplication.getMyApplication().getDataUser().setPassWordStatic(obj.get("password").toString());
                            MyApplication.getMyApplication().getDataUser().setPhoneStatic(obj.get("phone").toString());
                            if(obj.get("image").toString().equals("null")) {
                                MyApplication.getMyApplication().getDataUser().setByteArrayImageStatic("");
                            }else {
                                MyApplication.getMyApplication().getDataUser().setByteArrayImageStatic(obj.get("image").toString());

                            }

                            if(obj.get("dateofbirth").toString().equals("null")) {
                                MyApplication.getMyApplication().getDataUser().setDateOfBirthStatic("");
                            }else {
                                MyApplication.getMyApplication().getDataUser().setDateOfBirthStatic(obj.get("dateofbirth").toString());

                            }

                            if(obj.get("sex").toString().equals("null")) {
                                MyApplication.getMyApplication().getDataUser().setSexStatic("");
                            }else {
                                MyApplication.getMyApplication().getDataUser().setSexStatic(obj.get("sex").toString());

                            }

                            if(obj.get("city").toString().equals("null")) {
                                MyApplication.getMyApplication().getDataUser().setCityStatic("");
                            }else {
                                MyApplication.getMyApplication().getDataUser().setCityStatic(obj.get("city").toString());

                            }

                            if(obj.get("township").toString().equals("null")) {
                                MyApplication.getMyApplication().getDataUser().setTownshipStatic("");
                            }else {
                                MyApplication.getMyApplication().getDataUser().setTownshipStatic(obj.get("township").toString());

                            }

                            if(obj.get("ward").toString().equals("null")) {
                                MyApplication.getMyApplication().getDataUser().setWardStatic("");
                            }else {

                                MyApplication.getMyApplication().getDataUser().setWardStatic(obj.get("ward").toString());

                            }

                            if(obj.get("apartmentnumber").toString().equals("null")) {

                                MyApplication.getMyApplication().getDataUser().setApartmentNumberStatic("");
                            }else {
                                MyApplication.getMyApplication().getDataUser().setApartmentNumberStatic(obj.get("apartmentnumber").toString());

                            }






                            MyApplication.getMyApplication().getDataUser().setMoneyStatic(Float.parseFloat(obj.get("money").toString()));
                            MyApplication.getMyApplication().getDataUser().setAccumulatedPointsStatic(obj.getInt("accumulatedpoints"));
                            MyApplication.getMyApplication().getDataUser().setCarepayactive(obj.getInt("carepayactive"));
                            MyApplication.getMyApplication().getDataUser().setSetlock(obj.get("setlock").toString());
                            MyApplication.getMyApplication().getDataUser().setAddcard(obj.get("addcard").toString());

//                            Log.d("care123", obj.get("setlock").toString() + "123");




                            mProgressBar.setVisibility(View.INVISIBLE);


                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Login.this, "Fail call API", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });
        mRegisterTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterTxt.setPaintFlags(mRegisterTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                startActivity(new Intent(getApplicationContext(), Register.class));
                finish();
            }
        });
    }
}