package com.example.myapp.profile_interface;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileDataChangepass extends AppCompatActivity {
    ImageView back;
    Button change;
    EditText passold, passnew, passrenew;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data_changepass);
        back = findViewById(R.id.imv_profile_pass_back);
        change = findViewById(R.id.btn_profile_pass_change);
        passold = findViewById(R.id.edt_profile_pass_old);
        passnew = findViewById(R.id.edt_profile_pass_new);
        passrenew = findViewById(R.id.edt_profile_pass_re_new);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PASSOLD = passold.getText().toString();
                String PASSNEW = passnew.getText().toString();
                String PASSRENEW = passrenew.getText().toString();
                if(PASSOLD.length()==0||PASSNEW.length()==0||PASSRENEW.length()==0){
                    Toast.makeText(ProfileDataChangepass.this, "Please enter information", Toast.LENGTH_LONG).show();
                }else if(!(PASSOLD.equals(MyApplication.getMyApplication().getDataUser().getPassWordStatic()))){
                    Toast.makeText(ProfileDataChangepass.this, "Wrong password", Toast.LENGTH_LONG).show();

                }else if(PASSOLD.length()<6||PASSNEW.length()<6||PASSRENEW.length()<6){
                    Toast.makeText(ProfileDataChangepass.this, "Password must be more than 6 characters", Toast.LENGTH_LONG).show();

                }else if(PASSNEW.equals(PASSRENEW)){

                    progressDialog = new ProgressDialog(ProfileDataChangepass.this);
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);

                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {
                        String email = MyApplication.getMyApplication().getDataUser().getEmailStatic();
                        jsonObject = new JSONObject();
                        jsonObject.put("email", email);
                        jsonObject.put("passnew", passrenew.getText().toString());
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.profilepasschange(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            String notice = null;

                            try {
                                notice = response.body().string();
                                if(notice.equals("success")){
                                    Toast.makeText(ProfileDataChangepass.this, "Success to change password", Toast.LENGTH_LONG).show();
                                    progressDialog.dismiss();
                                    onBackPressed();
                                }else {
                                    progressDialog.dismiss();
                                    Toast.makeText(ProfileDataChangepass.this, "Fail to change password", Toast.LENGTH_LONG).show();
                                }

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(ProfileDataChangepass.this, "Fail to call API", Toast.LENGTH_LONG).show();

                        }
                    });


                }else {
                    Toast.makeText(ProfileDataChangepass.this, "You have re-entered the wrong new password", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}