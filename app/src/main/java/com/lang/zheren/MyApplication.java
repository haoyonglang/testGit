package com.lang.zheren;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * 管理全局状态信息，比如Context
 * Created by Administrator on 2017/8/14.
 */

public class MyApplication extends Application {
    /**
     * 用来保存当前该Application的context
     */
    private static Context sContext;
    /**
     * 用来保存最新打开页面的context
     */
    private volatile static WeakReference<Context> instanceRef = null;

    @Override
    public void onCreate() {
        super.onCreate();
        //获取context
        sContext = getApplicationContext();

    }

    //返回，外面调用这个方法返回一个context
    public static Context getContextObject() {
        return sContext;
    }
}
