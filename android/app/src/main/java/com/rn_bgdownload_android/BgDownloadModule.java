package com.rn_bgdownload_android;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class BgDownloadModule extends ReactContextBaseJavaModule {

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
        BgDownloadService.start(reactContext);
    }

}