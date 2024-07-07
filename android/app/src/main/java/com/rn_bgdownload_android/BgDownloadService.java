package com.rn_bgdownload_android;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.facebook.react.bridge.ReactApplicationContext;

public class BgDownloadService extends Service {
    private static final String TAG = "NativeModule";
    private static final int NOTIFICATION_ID = 1;
    private static ReactApplicationContext reactContext;

    private MyNotification myNotification = new MyNotification();
    private NotificationManagerCompat notificationManager;

    static void start(ReactApplicationContext context) {
        reactContext = context;
        Intent serviceIntent = new Intent(context, BgDownloadService.class);

        // API 26이상부터 startForegroundService를 사용한다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(serviceIntent);
        } else {
            context.startService(serviceIntent);
        }
    }

    static void stop() {
        Intent serviceIntent = new Intent(reactContext, BgDownloadService.class);
        reactContext.stopService(serviceIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myNotification.createNotificationChannel(reactContext);
        notificationManager = NotificationManagerCompat.from(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationCompat.Builder builder = createForegroundNotificationBuilder("다운로드를 시작합니다.", "다운로드 중...");
        startForeground(NOTIFICATION_ID, builder.build());

        new Thread(this::downloadTask).start();

        return START_STICKY;
    }

    @SuppressLint("MissingPermission")
    private void downloadTask() {
        NotificationCompat.Builder builder = createForegroundNotificationBuilder("다운로드를 시작합니다.", "다운로드 중...");

        for (int idx = 0; idx < 20; idx++) {
            builder.setProgress(20, idx, false);

            notificationManager.notify(NOTIFICATION_ID, builder.build());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Thread sleep interrupted", e);
                Thread.currentThread().interrupt();
            }
        }

        builder.setContentText("다운로드 완료")
                .setProgress(0, 0, false);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service Destroyed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null; // 서비스가 바인드 모드를 지원하지 않음
    }

    private NotificationCompat.Builder createForegroundNotificationBuilder(String title, String text) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, myNotification.getChannelId())
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.autofill_inline_suggestion_chip_background) // Progress 푸시 알림은 setSmallIcon을 해야 함
                .setProgress(20, 0, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(myNotification.getChannelId());
        }

        return builder;
    }
}
