package com.rn_bgdownload_android;


import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.ContextCompat.getSystemService;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.facebook.react.bridge.ReactApplicationContext;

public class MyNotification {
    public String channelId = "channel";
    private static String channelName = "channel name";
    private static String channeDescription = "channel description";

    // 푸시 알림 채널을 만드는 메서드
    public void createNotificationChannel(Context context) {
        String TAG = "NativeModule";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(ReactApplicationContext.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription(channeDescription);

            notificationManager.createNotificationChannel(channel);
        }
    }

    // TODO : 푸시알림 권한 요청 /API 33이상부터 PUSH-NOTIFICATION도 유저의 권한을 요청해야 한다
    public void requestNotiPermission (){

    }
}
