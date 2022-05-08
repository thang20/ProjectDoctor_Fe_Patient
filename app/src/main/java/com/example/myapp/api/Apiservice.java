package com.example.myapp.api;

import com.example.myapp.MyApplication;
import com.example.myapp.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Apiservice {
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    Apiservice apiservice = new Retrofit.Builder()
            .baseUrl(MyApplication.getMyApplication().getDataUser().getAPI())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(Apiservice.class);

    @POST("register")
    Call<ResponseBody> putRegister(@Body RequestBody user);
    @POST("login")
    Call<ResponseBody> putLogin(@Body RequestBody user);
    @POST("updateprofile")
    Call<ResponseBody> updateprofile(@Body RequestBody user);
    @POST("updatemoney")
    Call<ResponseBody> updatemoney(@Body RequestBody user);
    @POST("updatpoint")
    Call<ResponseBody> updatepoint(@Body RequestBody user);
    @POST("profilerelative")
    Call<ResponseBody> profilerelative(@Body RequestBody user);
    @POST("profilerelativedetail")
    Call<ResponseBody> profilerelativedetail(@Body RequestBody user);
    @POST("profilerelativedetailupdate")
    Call<ResponseBody> profilerelativedetailupdate(@Body RequestBody user);
    @POST("profilerelativeadd")
    Call<ResponseBody> profilerelativeadd(@Body RequestBody user);
    @POST("updatemoneydonation")
    Call<ResponseBody> updatemoneydonation(@Body RequestBody user);
    @POST("updatpointdonation")
    Call<ResponseBody> updatepointdonation(@Body RequestBody user);
    @POST("profileschedule")
    Call<ResponseBody> profileschedule(@Body RequestBody user);
    @POST("profilescheduleadd")
    Call<ResponseBody> profilescheduleadd(@Body RequestBody user);
    @POST("profilescheduledelete")
    Call<ResponseBody> profilescheduledelete(@Body RequestBody user);
    @POST("profilepasschange")
    Call<ResponseBody> profilepasschange(@Body RequestBody user);
    @POST("activecarepay")
    Call<ResponseBody> activecarepay(@Body RequestBody user);
    @POST("takeinftranfer")
    Call<ResponseBody> takeinftranfer(@Body RequestBody user);
    @POST("transfer")
    Call<ResponseBody> transfer(@Body RequestBody user);
    @POST("transferbank")
    Call<ResponseBody> transferbank(@Body RequestBody user);
    @POST("postanew")
    Call<ResponseBody> postanew(@Body RequestBody user);
    @POST("news")
    Call<ResponseBody> news(@Body RequestBody user);
    @POST("postacomment")
    Call<ResponseBody> postacomment(@Body RequestBody user);
    @POST("allcomment")
    Call<ResponseBody> allcomment(@Body RequestBody user);
    @POST("hospital")
    Call<ResponseBody> hospital(@Body RequestBody user);
    @POST("dataonehospital")
    Call<ResponseBody> dataonehospital(@Body RequestBody user);
    @POST("dataonerelative")
    Call<ResponseBody> dataonerelative(@Body RequestBody user);
    @POST("dataonespec")
    Call<ResponseBody> dataonespec(@Body RequestBody user);
    @POST("profilesremind")
    Call<ResponseBody> profilesremind(@Body RequestBody user);
    @POST("postremind")
    Call<ResponseBody> postremind(@Body RequestBody user);
    @POST("postpharmacy")
    Call<ResponseBody> postpharmacy(@Body RequestBody user);
    @POST("profileshistory")
    Call<ResponseBody> profileshistory(@Body RequestBody user);
    @POST("qa")
    Call<ResponseBody> qa(@Body RequestBody user);
    @POST("xray")
    Call<ResponseBody> xray(@Body RequestBody user);
    @POST("qadoctor")
    Call<ResponseBody> qadoctor(@Body RequestBody user);
    @POST("postanewqa")
    Call<ResponseBody> postanewqa(@Body RequestBody user);
    @POST("allservice")
    Call<ResponseBody> allservice(@Body RequestBody user);
    @POST("buyservice")
    Call<ResponseBody> buyservice(@Body RequestBody user);
    @POST("skin")
    Call<ResponseBody> skin(@Body RequestBody user);
    @POST("postacommentdt")
    Call<ResponseBody> postacommentdt(@Body RequestBody user);
    @POST("allcommentdt")
    Call<ResponseBody> allcommentdt(@Body RequestBody user);
    @POST("allrecodmedical")
    Call<ResponseBody> allrecodmedical(@Body RequestBody user);
    @POST("allspeccall")
    Call<ResponseBody> allspeccall(@Body RequestBody user);
    @POST("doctorspecall")
    Call<ResponseBody> doctorspecall(@Body RequestBody user);
    @POST("endow")
    Call<ResponseBody> endow(@Body RequestBody user);
    @POST("healcv")
    Call<ResponseBody> healcv(@Body RequestBody user);
    @POST("turnonlock")
    Call<ResponseBody> turnonlock(@Body RequestBody user);
    @POST("turnofflock")
    Call<ResponseBody> turnofflock(@Body RequestBody user);
    @POST("addcard")
    Call<ResponseBody> addcard(@Body RequestBody user);
    @POST("like")
    Call<ResponseBody> like(@Body RequestBody user);
    @POST("checklich")
    Call<ResponseBody> checklich(@Body RequestBody user);








}
