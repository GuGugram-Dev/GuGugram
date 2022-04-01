package com.blxueya.gugugram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

import org.telegram.messenger.ApplicationLoader;

@SuppressLint("ApplySharedPref")
public class GuGuConfig {
    private static final Object sync = new Object();

    public static boolean forceAllowCopy = false;
    public static boolean hideSponsoredMessage = false;
    public static boolean alwaysSaveChatOffset = false;
    public static boolean disableChatActionSending = false;
    public static boolean showForwarderName = false;
    public static boolean showSpoilersDirectly = false;

    public static boolean showRepeatAsCopy = true;
    public static final int DOUBLE_TAP_ACTION_REPEATASCOPY = 6;


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

            forceAllowCopy = preferences.getBoolean("ForceAllowCopy", false);

            configLoaded = true;
        }
    }

    public static void toggleForceAllowCopy() {
        forceAllowCopy = !forceAllowCopy;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ForceAllowCopy", forceAllowCopy);
        editor.commit();
    }

    public static void toggleAlwaysSaveChatOffset() {
        alwaysSaveChatOffset = !alwaysSaveChatOffset;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("AlwaysSaveChatOffset", alwaysSaveChatOffset);
        editor.commit();
    }

    public static void toggleHideSponsoredMessage() {
        hideSponsoredMessage = !hideSponsoredMessage;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("HideSponsoredMessage", hideSponsoredMessage);
        editor.commit();
    }

    public static void toggleShowSpoilersDirectly() {
        showSpoilersDirectly = !showSpoilersDirectly;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ShowSpoilersDirectly", showSpoilersDirectly);
        editor.commit();
    }
    public static void toggleShowForwarderName() {
        showForwarderName = !showForwarderName;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ShowForwarderName", showForwarderName);
        editor.commit();
    }

    public static void toggleDisableChatActionSending() {
        disableChatActionSending = !disableChatActionSending;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("DisableChatActionSending", disableChatActionSending);
        editor.commit();
    }

    public static void toggleShowRepeatAsCopy() {
        showRepeatAsCopy = !showRepeatAsCopy;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("showRepeatAsCopy", showRepeatAsCopy);
        editor.commit();
    }
}
