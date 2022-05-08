package com.example.myapp.fragment;

import android.app.ProgressDialog;
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

import com.example.myapp.MyApplication;
import com.example.myapp.ProfileFragment;
import com.example.myapp.R;
import com.example.myapp.api.Apiservice;
import com.example.myapp.data.DataUser;
import com.example.myapp.profile_interface.UpdateProfile;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NationalFragmentDonationP extends Fragment {

    Button add;
    EditText point;
    ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donation_national_p, container, false);
        add = view.findViewById(R.id.btn_profile_money_adddonation1p);
        point = view.findViewById(R.id.edt_profile_donation1pam);
        point.setText(null);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (point.getText().toString().trim().length() == 0) {
                    Toast.makeText(getContext(), "Please input the point", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.process_dialog);

                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    Integer spoint = Integer.parseInt(point.getText().toString());
                    String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();

                    JSONObject jsonObject = new JSONObject();
                    String jsonStr = null;
                    try {

                        jsonObject = new JSONObject();
                        jsonObject.put("email", semail);
                        jsonObject.put("point", spoint);
                        jsonStr = jsonObject.toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    RequestBody body =
                            RequestBody.create(MediaType.parse("text/plain"), jsonStr);

                    Apiservice.apiservice.updatepointdonation(body).enqueue(new Callback<ResponseBody>() {
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
                                progressDialog.dismiss();


                            } else {
                                MyApplication.getMyApplication().getDataUser().setAccumulatedPointsStatic(Integer.parseInt(notice));
                                ;
                                Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();
                                point.setText("");
                                point.setText(null);
                                progressDialog.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getContext(), "Fail call API", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();

                        }
                    });
                }
            }
        });
        return view;
    }
}