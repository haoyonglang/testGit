package com.lang.zheren.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharePreference封装
 */
public class SharePreferenceUtils {

    private static final String PREF_NAME = "config";

    public static final String GUIDE_SHOWED = "guide_showed";

    public static final String SAVE_HIMI = "save.himi";

    public static final String IS_LOGIN = "isLogin";

    private static Context mContext;

    //饿汉模式的单例
    private static SharePreferenceUtils instance = new SharePreferenceUtils();

    private SharePreferenceUtils() {

    }

    public static synchronized SharePreferenceUtils getInstance() {
        return instance;
    }

    public SharePreferenceUtils(Context context) {
        this.mContext = context;
    }

    /**
     * 判断用户是否是第一次使用app，是则跳转到导航页，否则跳转到主界面
     *
     * @param ctx
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Context ctx, String key,
                                     boolean defaultValue) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static void setBoolean(Context ctx, String key, boolean value) {
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME,
                Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }


    /**
     * 设置登录状态
     * false为安装后第一次登录，true为已经登录过
     */
    public static void setState(Context context) {
        SharedPreferences sp = context.getSharedPreferences("save.himi", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", true);
        editor.commit();
    }

    public static boolean getState(Context context) {
        SharedPreferences sp = context.getSharedPreferences("save.himi", Context.MODE_PRIVATE);
        boolean b = sp.getBoolean("isLogin", false);
        return b;
    }
}
