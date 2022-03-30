package com.blxueya.GuGugram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

import org.checkerframework.checker.units.qual.A;
import org.telegram.messenger.ApplicationLoader;

@SuppressLint("ApplySharedPref")
public class GuGuConfig {
    private static final Object sync = new Object();

    public static boolean ForceAllowCopy = false;
    public static boolean AlwaysSaveChatOffset = false;

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
}
