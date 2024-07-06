package com.rn_bgdownload_android;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class BgDownloadModule extends ReactContextBaseJavaModule {

    BgDownloadModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "BgDownloadModule";
    }

    @ReactMethod
    public void downloadOnBackground() {
        Log.d("Native Module", "downloading..." );
    }

}