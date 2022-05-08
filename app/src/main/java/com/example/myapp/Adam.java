package com.example.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Adam extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("123", "okkkkkkkkkkkkkkkk");
        Intent myIntent = new Intent(context, Music.class);
        context.startService(myIntent);

    }
}
