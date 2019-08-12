package com.xaehu.myapplication;

import android.app.Application;
import android.content.Context;

import com.xaehu.mvp_library.utils.ActivityUtils;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityUtils.getInstance().setContext(getApplicationContext());
    }

}
