package com.blxueya.gugugram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

import org.telegram.messenger.ApplicationLoader;

@SuppressLint("ApplySharedPref")
public class GuGuConfig {
    private static final Object sync = new Object();

    public static boolean ForceAllowCopy = false;
    public static boolean HideSponsoredMessage = false;
    public static boolean AlwaysSaveChatOffset = false;
    public static boolean DisableChatActionSending = false;
    public static boolean ShowForwarderName = false;
    public static boolean ShowSpoilersDirectly = false;

    private static boolean configLoaded;

    static {
        loadConfig();
    }

    public static void loadConfig() {
        synchronized (sync) {
            if (configLoaded) {
                return;
            }
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);

            ForceAllowCopy = preferences.getBoolean("ForceAllowCopy", false);

            configLoaded = true;
        }
    }

    public static void toggleForceAllowCopy() {
        ForceAllowCopy = !ForceAllowCopy;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ForceAllowCopy", ForceAllowCopy);
        editor.commit();
    }

    public static void toggleAlwaysSaveChatOffset() {
        AlwaysSaveChatOffset = !AlwaysSaveChatOffset;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("AlwaysSaveChatOffset", AlwaysSaveChatOffset);
        editor.commit();
    }

    public static void toggleHideSponsoredMessage() {
        HideSponsoredMessage = !HideSponsoredMessage;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("HideSponsoredMessage", HideSponsoredMessage);
        editor.commit();
    }

    public static void toggleShowSpoilersDirectly() {
        ShowSpoilersDirectly = !ShowSpoilersDirectly;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ShowSpoilersDirectly", ShowSpoilersDirectly);
        editor.commit();
    }
    public static void toggleShowForwarderName() {
        ShowForwarderName = !ShowForwarderName;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ShowForwarderName", ShowForwarderName);
        editor.commit();
    }

    public static void toggleDisableChatActionSending() {
        DisableChatActionSending = !DisableChatActionSending;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("DisableChatActionSending", DisableChatActionSending);
        editor.commit();
    }
}
