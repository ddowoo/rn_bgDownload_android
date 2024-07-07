package com.rn_bgdownload_android;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;


public class BgDownloadService extends Service {

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private int notificationId = 1;

    private static final String TAG = "NativeModule";
    MyNotification myNotification = new MyNotification();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service Started");
        // 서비스가 수행할 작업을 여기에 작성합니다.

        Log.d(TAG, "Service Created");
        Notification notification = null;

        myNotification.createNotificationChannel(this.getApplicationContext());
        // API 26이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this, myNotification.channelId)
                    .setContentTitle("다운로드 실행")
                    .setContentText("~~~~ 다운로드 중")
                    .setChannelId(myNotification.channelId)
                    .build();
        }else{
            notification = new Notification.Builder(this)
                    .setContentTitle("다운로드 실행")
                    .setContentText("~~~~ 다운로드 중")
                    .build();
        }
        Log.d(TAG, String.valueOf(notification));
        startForeground(1, notification);
        // 서비스가 종료되지 않고 계속 실행되도록 START_STICKY를 반환합니다.

       
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service Destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // 서비스가 바인드 모드를 지원하지 않으면 null을 반환합니다.
        return null;
    }

}
