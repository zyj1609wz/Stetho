package com.zyj.stetho;
import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by ${zhaoyanjun} on 2017/3/13.
 */

public class MyApplication extends Application {

    private static Context context ;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);

        context = getApplicationContext() ;
    }

    public static Context getContext(){
        return context ;
    }
}
