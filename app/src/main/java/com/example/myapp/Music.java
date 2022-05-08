package com.example.myapp;

import android.app.Service;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;


public class Music extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer = MediaPlayer.create(this, R.raw.nhac);
        mediaPlayer.start();
        Log.d("123", "toi trong music");
        return START_NOT_STICKY;
    }
}
