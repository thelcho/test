package com.lch.test.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.lch.test.R;
import com.lch.test.activity.MyServiceActivity;

public class MyService extends Service {

    private DownloadBinder mBinder = new DownloadBinder();
    public class DownloadBinder extends Binder {
        public void startDownload() {
            Log.d("lch", "startDownload executed");
        }
        public int getProgress() {
            Log.d("lch", "getProgress executed");
            return 0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("lch", "onBind");
        return mBinder;
    }
    @Override
    public void onCreate() {
        super.onCreate();

        //前台服务
//        Notification notification = new Notification(R.mipmap.ic_launcher,
//                "Notification comes", System. currentTimeMillis());
//        Intent notificationIntent = new Intent(this, MyServiceActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
//                notificationIntent, 0);
//        //notification.setLatestEventInfo(this, "This is title", "This is content", pendingIntent);
//        startForeground(1, notification);

        Log.d("lch", "onCreate executed");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("lch", "onStartCommand executed");

        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lch", "onDestroy executed");
    }
}
