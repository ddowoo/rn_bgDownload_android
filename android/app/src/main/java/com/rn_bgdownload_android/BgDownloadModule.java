package com.rn_bgdownload_android;

import android.content.Intent;
import android.os.Build;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class BgDownloadModule extends ReactContextBaseJavaModule {

    String TAG = "NativeModule";

    ReactApplicationContext reactContext;

    BgDownloadModule(ReactApplicationContext context) {
        super(context);
        this.reactContext = context;
    }

    @Override
    public String getName() {
        return "BgDownloadModule";
    }

    @ReactMethod
    public void downloadOnBackground() {
        ReactApplicationContext context = getReactApplicationContext();

        Log.d(TAG, "open service..." );
        Log.d(TAG, String.valueOf(context));
        Log.d(TAG, String.valueOf(reactContext));

        // 포그라운드 서비스 시작
        Intent serviceIntent = new Intent(context, BgDownloadService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d(TAG, "OVER API 26");

            context.startForegroundService(serviceIntent);
        }else{
            Log.d(TAG, "UNDER API 26");

            context.startService(serviceIntent);
        }
    }

}