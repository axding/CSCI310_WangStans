package com.example;

import android.app.Application;
import android.content.Context;

public class sharedHolder extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        sharedHolder.context = getApplicationContext();
    }



    public static Context getAppContext() {
        return sharedHolder.context;
    }

}