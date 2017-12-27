package com.alpha.baiduduerdemo.nim.model;

import android.content.Context;

import com.netease.nimlib.sdk.StatusBarNotificationConfig;

public class DemoCache {
    private static Context context;

    private static String account;

    private static StatusBarNotificationConfig config;

    public static void clear() {
        account = null;
    }

    public static String getAccount() {
        return account;
    }

    public static void setAccount(String account) {
        DemoCache.account = account;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        DemoCache.context = context.getApplicationContext();
    }

    public static StatusBarNotificationConfig getConfig() {
        return config;
    }

    public static void setConfig(StatusBarNotificationConfig config) {
        DemoCache.config = config;
    }
}
