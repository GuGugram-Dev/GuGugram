package com.blxueya.gugugram;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;

import org.telegram.messenger.ApplicationLoader;

import java.util.HashMap;

import tw.nekomimi.nekogram.helpers.remote.AnalyticsHelper;

@SuppressLint("ApplySharedPref")
public class GuGuConfig {
    private static final Object sync = new Object();
    public static final String channelAliasPrefix = "channelAliasPrefix_";

    public static boolean forceAllowCopy = false;
    public static boolean hideSponsoredMessage = false;
    public static boolean channelAlias = false;
    public static boolean alwaysSaveChatOffset = false;
    public static boolean disableChatActionSending = false;
    public static boolean showForwarderName = false;
    public static boolean showSpoilersDirectly = false;

    public static boolean showRepeatAsCopy = true;
    public static final int DOUBLE_TAP_ACTION_REPEATASCOPY = 6;

    private static boolean configLoaded;

    // analytics
    private static final SharedPreferences.OnSharedPreferenceChangeListener listener = (preferences, key) -> {
        var map = new HashMap<String, String>(1);
        map.put("key", key);
        AnalyticsHelper.trackEvent("GuGu config changed", map);
    };


    static {
        loadConfig();
    }

    public static void loadConfig() {
        synchronized (sync) {
            if (configLoaded) {
                return;
            }
            SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);

            forceAllowCopy = preferences.getBoolean("forceAllowCopy", false);
            hideSponsoredMessage = preferences.getBoolean("hideSponsoredMessage",false);
            channelAlias = preferences.getBoolean("channelAlias",false);
            alwaysSaveChatOffset = preferences.getBoolean("alwaysSaveChatOffset",false);
            disableChatActionSending = preferences.getBoolean("disableChatActionSending",false);
            showForwarderName = preferences.getBoolean("showForwarderName",false);
            showSpoilersDirectly = preferences.getBoolean("showSpoilersDirectly",false);
            showRepeatAsCopy = preferences.getBoolean("showRepeatAsCopy",false);

            preferences.registerOnSharedPreferenceChangeListener(listener);

            configLoaded = true;
        }
    }

    public static void toggleForceAllowCopy() {
        forceAllowCopy = !forceAllowCopy;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("forceAllowCopy", forceAllowCopy);
        editor.commit();
    }

    public static void toggleAlwaysSaveChatOffset() {
        alwaysSaveChatOffset = !alwaysSaveChatOffset;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("alwaysSaveChatOffset", alwaysSaveChatOffset);
        editor.commit();
    }

    public static void toggleHideSponsoredMessage() {
        hideSponsoredMessage = !hideSponsoredMessage;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("hideSponsoredMessage", hideSponsoredMessage);
        editor.commit();
    }

    public static void toggleShowSpoilersDirectly() {
        showSpoilersDirectly = !showSpoilersDirectly;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("showSpoilersDirectly", showSpoilersDirectly);
        editor.commit();
    }
    public static void toggleShowForwarderName() {
        showForwarderName = !showForwarderName;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("showForwarderName", showForwarderName);
        editor.commit();
    }

    public static void toggleDisableChatActionSending() {
        disableChatActionSending = !disableChatActionSending;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("disableChatActionSending", disableChatActionSending);
        editor.commit();
    }

    public static void toggleShowRepeatAsCopy() {
        showRepeatAsCopy = !showRepeatAsCopy;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("showRepeatAsCopy", showRepeatAsCopy);
        editor.commit();
    }

    public static void toggleChannelAlias() {
        channelAlias = !channelAlias;
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("channelAlias", channelAlias);
        editor.commit();
    }


    public static void setChannelAlias(long channelID, String name) {
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(GuGuConfig.channelAliasPrefix + channelID, name).apply();
    }

    public static void emptyChannelAlias(long channelID) {
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(GuGuConfig.channelAliasPrefix + channelID).apply();
    }

    public static String getChannelAlias(long channelID) {
        SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Activity.MODE_PRIVATE);
        return preferences.getString(GuGuConfig.channelAliasPrefix + channelID, null);
    }
}
