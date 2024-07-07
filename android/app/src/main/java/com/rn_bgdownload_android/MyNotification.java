package com.rn_bgdownload_android;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import com.facebook.react.bridge.ReactApplicationContext;

public class MyNotification {

    private static final String CHANNEL_ID = "channel";
    private static final String CHANNEL_NAME = "channel name";
    private static final String CHANNEL_DESCRIPTION = "channel description";

    // 푸시 알림 채널을 만드는 메서드
    public void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(ReactApplicationContext.NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(CHANNEL_DESCRIPTION);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // TODO : 푸시알림 권한 요청 /API 33이상부터 PUSH-NOTIFICATION도 유저의 권한을 요청해야 한다
    public void requestNotiPermission() {}

    public String getChannelId() {
        return CHANNEL_ID;
    }
}