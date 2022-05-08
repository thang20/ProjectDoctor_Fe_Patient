package com.example.myapp;

import android.app.Application;

import com.example.myapp.data.DataUser;

public class MyApplication  extends Application {
    public static MyApplication myApplication;

    private DataUser dataUser;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication=this;
        dataUser =new DataUser();
    }

    public DataUser getDataUser() {
        return dataUser;
    }

    public void setDataUser(DataUser dataUser) {
        this.dataUser = dataUser;
    }

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static void setMyApplication(MyApplication myApplication) {
        MyApplication.myApplication = myApplication;
    }
}
