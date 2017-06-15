package com.mobewash.mobewash;

import android.app.Application;

import com.mobewash.mobewash.utils.RequestQueueSingleton;

/**
 * Created by sidney on 6/14/17.
 */

public class MobeWashApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RequestQueueSingleton.getInstance(getApplicationContext());
    }
}
