package com.blxueya.GuGugram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

import org.telegram.messenger.ApplicationLoader;

@SuppressLint("ApplySharedPref")
public class GuGuConfig {
    private static final Object sync = new Object();

    public static boolean ForceAllowCopy = false;

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
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("pigeonconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("forceAllowCopy", ForceAllowCopy);
        editor.commit();
    }
}
