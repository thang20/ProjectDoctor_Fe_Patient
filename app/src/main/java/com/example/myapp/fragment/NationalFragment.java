package com.example.myapp.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp.Login;
import com.example.myapp.MyApplication;
import com.example.myapp.ProfileFragment;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.UpdateProfile;

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


public class NationalFragment extends Fragment {

    Button add;
    EditText money;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_money_nationnal, container, false);
        add = view.findViewById(R.id.btn_profile_money_addmoney);
        money = view.findViewById(R.id.edt_profile_moneyam);
        money.setText(null);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (money.getText().toString().trim().length() == 0) {
                    Toast.makeText(getContext(), "Please input the amount", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
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

                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.updatemoney(body).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            String notice = null;
                            try {
                                notice = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (notice.equals("error")) {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();


                            } else {
                                MyApplication.getMyApplication().getDataUser().setMoneyStatic(Float.parseFloat(notice));
                                ;
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
                                ProfileFragment profileFragment = new ProfileFragment();
                                money.setText("");
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                fragmentTransaction.replace(R.id.ln_profile_money_nationnal, profileFragment);
                                fragmentTransaction.commit();
                                money.setText(null);
                                progressDialog.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getContext(), "Fail call API", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            }
        });
        return view;
    }
}