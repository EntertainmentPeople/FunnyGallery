package com.klj.funnygallery.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 娱乐人物 on 2016/11/8.
 */
public class PhoneUtils {
    private static Map<String,String> map=new HashMap<>();
    static {
        map.put("iid","6106194600");
        map.put("device_id","33893122889");
        map.put("ac","wifi");
        map.put("channel","goapk");
        map.put("app_name","funny_gallery");
        map.put("version_code","241");
        map.put("device_platform","android");
        map.put("device_type","HTC%20D816d");
        map.put("os_api","19");
        map.put("os_version","4.4.2");
        map.put("uuid","a1000032d97562");
        map.put("openudid","2935082b1d4ac037");

    }

    public static Map<String, String> getMap() {
        return map;
    }

    public static String getTelStatus(Context context) {
        TelephonyManager mTm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

        String phoneInfo =  ", VERSION_CODES.BASE: " + android.os.Build.VERSION_CODES.BASE+"\n";
        phoneInfo += ", MODEL: " + android.os.Build.MODEL+"\n";
        phoneInfo += ", SDK: " + android.os.Build.VERSION.SDK_INT+"\n";
        phoneInfo += ", VERSION.RELEASE: " + android.os.Build.VERSION.RELEASE+"\n";
        phoneInfo+= mTm.getDeviceId()+"\n";
        phoneInfo += mTm.getSubscriberId();
        return phoneInfo;
    }


}
