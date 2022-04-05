package com.blxueya.gugugram;

import static com.blxueya.gugugram.helper.ConfigItem.configTypeBool;
import static com.blxueya.gugugram.helper.ConfigItem.configTypeFloat;
import static com.blxueya.gugugram.helper.ConfigItem.configTypeInt;
import static com.blxueya.gugugram.helper.ConfigItem.configTypeLong;
import static com.blxueya.gugugram.helper.ConfigItem.configTypeMapIntInt;
import static com.blxueya.gugugram.helper.ConfigItem.configTypeSetInt;
import static com.blxueya.gugugram.helper.ConfigItem.configTypeString;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.blxueya.gugugram.helper.ConfigItem;

import org.telegram.messenger.ApplicationLoader;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import tw.nekomimi.nekogram.helpers.remote.AnalyticsHelper;

@SuppressLint("ApplySharedPref")
public class GuGuConfig {
    public static final SharedPreferences preferences = ApplicationLoader.applicationContext.getSharedPreferences("guguconfig", Context.MODE_PRIVATE);
    public static final Object sync = new Object();
    public static final String channelAliasPrefix = "channelAliasPrefix_";

    private static boolean configLoaded = false;
    private static final ArrayList<ConfigItem> configs = new ArrayList<>();

    // configs
    public static ConfigItem forceAllowCopy = addConfig("forceAllowCopy",configTypeBool,false);
    public static ConfigItem hideSponsoredMessage = addConfig("hideSponsoredMessage",configTypeBool,false);
    public static ConfigItem channelAlias = addConfig("channelAlias",configTypeBool,false);
    public static ConfigItem alwaysSaveChatOffset = addConfig("alwaysSaveChatOffset",configTypeBool,false);
    public static ConfigItem disableChatActionSending = addConfig("disableChatActionSending",configTypeBool,false);
    public static ConfigItem showForwarderName = addConfig("showForwarderName",configTypeBool,false);
    public static ConfigItem showSpoilersDirectly = addConfig("showSpoilersDirectly",configTypeBool,false);

    // extra
    public static ConfigItem showRepeatAsCopy = addConfig("showRepeatAsCopy",configTypeBool,true);
    public static final int DOUBLE_TAP_ACTION_REPEATASCOPY = 6;



    // analytics
    private static final SharedPreferences.OnSharedPreferenceChangeListener listener = (preferences, key) -> {
        var map = new HashMap<String, String>(1);
        map.put("key", key);
        AnalyticsHelper.trackEvent("GuGu config changed", map);
    };

    static {
        loadConfig(false);
    }

    public static ConfigItem addConfig(String k, int t, Object d) {
        ConfigItem a = new ConfigItem(k, t, d);
        configs.add(a);
        return a;
    }

    public static void loadConfig(boolean force) {
        synchronized (sync) {
            if (configLoaded && !force) {
                return;
            }
            for (int i = 0; i < configs.size(); i++) {
                ConfigItem o = configs.get(i);

                if (o.type == configTypeBool) {
                    o.value = preferences.getBoolean(o.key, (boolean) o.defaultValue);
                }
                if (o.type == configTypeInt) {
                    o.value = preferences.getInt(o.key, (int) o.defaultValue);
                }
                if (o.type == configTypeLong) {
                    o.value = preferences.getLong(o.key, (Long) o.defaultValue);
                }
                if (o.type == configTypeFloat) {
                    o.value = preferences.getFloat(o.key, (Float) o.defaultValue);
                }
                if (o.type == configTypeString) {
                    o.value = preferences.getString(o.key, (String) o.defaultValue);
                }
                if (o.type == configTypeSetInt) {
                    Set<String> ss = preferences.getStringSet(o.key, new HashSet<>());
                    HashSet<Integer> si = new HashSet<>();
                    for (String s : ss) {
                        si.add(Integer.parseInt(s));
                    }
                    o.value = si;
                }
                if (o.type == configTypeMapIntInt) {
                    String cv = preferences.getString(o.key, "");
                    // Log.e("NC", String.format("Getting pref %s val %s", o.key, cv));
                    if (cv.length() == 0) {
                        o.value = new HashMap<Integer, Integer>();
                    } else {
                        try {
                            byte[] data = Base64.decode(cv, Base64.DEFAULT);
                            ObjectInputStream ois = new ObjectInputStream(
                                    new ByteArrayInputStream(data));
                            o.value = ois.readObject();
                            if (o.value == null) {
                                o.value = new HashMap<Integer, Integer>();
                            }
                            ois.close();
                        } catch (Exception e) {
                            o.value = new HashMap<Integer, Integer>();
                        }
                    }
                }
            }
            configLoaded = true;
        }
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
