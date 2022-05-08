package com.example.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.New_interface.Create_New_Post;
import com.example.myapp.api.Apiservice;
import com.example.myapp.carepaynonactive_interface.CheckPaperDone;
import com.example.myapp.data.ItemNewDataPost;
import com.example.myapp.data.ItemNewDataPostAdapter;
import com.example.myapp.data.ItemProfileData01;
import com.example.myapp.data.ItemProfileData01Adapter;
import com.example.myapp.data.ItemProfileDataRelative;
import com.example.myapp.data.ItemProfileDataRelativeAdapter;
import com.example.myapp.profile_interface.PointFagment;
import com.example.myapp.profile_interface.ProfileRelativeData;

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

public class NewsFragment extends Fragment {
    RecyclerView imageViewNew;
    EditText editTextInf;
    ItemNewDataPostAdapter itemNewDataPostAdapter;
    ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        imageViewNew = view.findViewById(R.id.rcv_new);
        editTextInf = view.findViewById(R.id.edt_new_inf);


//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.show();
//        progressDialog.setContentView(R.layout.process_dialog);

        imageViewNew.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        imageViewNew.addItemDecoration(itemDecoration);
//        itemNewDataPostAdapter = new ItemNewDataPostAdapter(getContext() ,getListData());
//        imageViewNew.setAdapter(itemNewDataPostAdapter);
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

        Apiservice.apiservice.news(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewDataPost> list = new ArrayList<ItemNewDataPost>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String content = obj.get("content").toString();
                        String numberlike = obj.get("numberlike").toString();
                        String date = obj.get("date").toString();
                        String time = obj.get("time").toString();
                        String image = obj.get("image").toString();
                        String imageface = obj.get("imageface").toString();
                        String fulname = obj.get("fulname").toString();
                        String idpeople = obj.get("idpeople").toString();
                        String idpost = obj.get("idpost").toString();
                        String like = obj.get("like").toString();




                        list.add(new ItemNewDataPost(imageface,
                                time, date, fulname, image, numberlike, content , idpeople, idpost, like));








                    }
                    itemNewDataPostAdapter = new ItemNewDataPostAdapter(getContext() ,list);
                    imageViewNew.setAdapter(itemNewDataPostAdapter);
                    progressDialog.dismiss();


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

            }
        });



        editTextInf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Create_New_Post.class);
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


        progressDialog = new ProgressDialog(getContext());
        progressDialog.show();
        progressDialog.setContentView(R.layout.process_dialog);

        imageViewNew.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        imageViewNew.addItemDecoration(itemDecoration);
//        itemNewDataPostAdapter = new ItemNewDataPostAdapter(getContext() ,getListData());
//        imageViewNew.setAdapter(itemNewDataPostAdapter);
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

        Apiservice.apiservice.news(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String notice = "";
                try {
                    notice = response.body().string();
                    List<ItemNewDataPost> list = new ArrayList<ItemNewDataPost>();
                    List<String> data = new ArrayList<String>(Arrays.asList(notice.split(",-,")));
                    for (int i = 0; i < data.size(); i++) {

                        String datajson = data.get(i);
                        Log.d("data", String.valueOf(i));
                        JSONObject obj = new JSONObject(datajson);


                        String content = obj.get("content").toString();
                        String numberlike = obj.get("numberlike").toString();
                        String date = obj.get("date").toString();
                        String time = obj.get("time").toString();
                        String image = obj.get("image").toString();
                        String imageface = obj.get("imageface").toString();
                        String fullname = obj.get("fullname").toString();
                        String idpeople = obj.get("idpeople").toString();
                        String idpost = obj.get("idpost").toString();
                        String like = obj.get("like").toString();




                        list.add(new ItemNewDataPost(imageface,
                                time, date, fullname, image, numberlike, content , idpeople, idpost, like));








                    }
                    itemNewDataPostAdapter = new ItemNewDataPostAdapter(getContext() ,list);
                    imageViewNew.setAdapter(itemNewDataPostAdapter);
                    progressDialog.dismiss();


                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();

            }
        });





    }
}
