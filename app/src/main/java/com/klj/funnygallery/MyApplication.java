package com.klj.funnygallery;

import android.app.Application;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 娱乐人物 on 2016/11/4.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(getApplicationContext(),"18b0fcb0666f2");
    }
}
