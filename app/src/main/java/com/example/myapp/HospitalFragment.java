package com.example.myapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.api.Apiservice;
import com.example.myapp.data.ItemNewDataHospital;
import com.example.myapp.data.ItemNewDataHospitalAdapter;
import com.example.myapp.data.ItemNewDataPost;
import com.example.myapp.data.ItemNewDataPostAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HospitalFragment extends Fragment {
    RecyclerView hospital;
    ItemNewDataHospitalAdapter itemNewDataHospitalAdapter;
    ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        hospital = view.findViewById(R.id.rcv_hospital);
        hospital.setLayoutManager(new LinearLayoutManager(getContext()));
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        hospital.addItemDecoration(itemDecoration);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);

        String semail =  MyApplication.getMyApplication().getDataUser().getEmailStatic();
        JSONObject jsonObject = new JSONObject();
        String jsonStr = null;
        try {

            jsonObject = new JSONObject();
            jsonObject.put("email", semail);
            jsonStr = jsonObject.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body =
                RequestBody.create(MediaType.parse("text/plain"), jsonStr);

        Apiservice.apiservice.hospital(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewDataHospital> list = new ArrayList<ItemNewDataHospital>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String name = obj.get("name").toString();
                        String address = obj.get("address").toString();






                        list.add(new ItemNewDataHospital(name,
                                address));







                    }
                    itemNewDataHospitalAdapter = new ItemNewDataHospitalAdapter(getContext() ,list);
                    hospital.setAdapter(itemNewDataHospitalAdapter);
                    progressDialog.dismiss();


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

        return view;
    }
}
