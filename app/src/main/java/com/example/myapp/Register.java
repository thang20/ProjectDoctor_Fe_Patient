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
import com.example.myapp.model.User;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText mFullName, mEmail, mPassWord, mPhone;
    Button mRegisterBtn;
    TextView mLoginTxt;
    ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("aa", "strUser1");
        setContentView(R.layout.activity_register);
        mFullName = findViewById(R.id.edtFullName);
        mEmail = findViewById(R.id.edtEmail);
        mPassWord = findViewById(R.id.edtPassWord);
        mPhone = findViewById(R.id.edtPhone);
        mRegisterBtn = findViewById(R.id.btnregister);
        mLoginTxt = findViewById(R.id.txtcreate);
        mProgressBar = findViewById(R.id.progressBar);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = mFullName.getText().toString();
                String email = mEmail.getText().toString().trim();
                String password = mPassWord.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                if(TextUtils.isEmpty(fullname)){
                    mFullName.setError("FullName is Required.");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassWord.setError("PassWord is Required.");
                    return;
                }
                if(TextUtils.isEmpty(phone)){
                    mPhone.setError("Phone is Required.");
                    return;
                }
                if(password.length() < 6){
                    mPassWord.setError("PassWord Must be >= 6 Characters.");
                    return;
                }
                mProgressBar.setVisibility(View.VISIBLE);
                User user = new User(fullname, email, password, phone , null,
                        null, null, null, null, null, null, 0.00f, 0);
                Gson gson = new Gson();
                String strUser = gson.toJson(user);
                Log.e("aa", strUser);

                RequestBody body =
                        RequestBody.create(MediaType.parse("text/plain"), strUser);
                //http://192.168.0.106:5000/register
                Apiservice.apiservice.putRegister(body).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String notice = null;
                        try {
                            notice = response.body().string();
                            Log.e("aa", notice);
                            Toast.makeText(Register.this, notice, Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Login.class));
                            finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(Register.this, "Fail call API", Toast.LENGTH_LONG).show();
                    }
                });



            }
        });
        mLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLoginTxt.setPaintFlags(mLoginTxt.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }
}