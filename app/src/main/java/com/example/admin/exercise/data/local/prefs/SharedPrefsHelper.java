package com.example.admin.exercise.data.local.prefs;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPrefsHelper {
    public static String PREF_KEY_ACCESS_TOKEN = "access-token";

    private SharedPreferences mSharedPreference;

    @Inject
    public SharedPrefsHelper(SharedPreferences sharedPreferences){
        mSharedPreference = sharedPreferences;
    }
    public void put(String key,String value){
        mSharedPreference.edit().putString(key,value).apply();
    }
    public void put(String key,Integer value){
        mSharedPreference.edit().putInt(key,value).apply();
    }
    public void put(String key,float value){
        mSharedPreference.edit().putFloat(key,value).apply();
    }
    public void put(String key,boolean value){
        mSharedPreference.edit().putBoolean(key,value).apply();
    }
    public String get(String key,String defaultValue){
        return mSharedPreference.getString(key,defaultValue);
    }
    public Integer get(String key, int defaultValue){
        return mSharedPreference.getInt(key,defaultValue);
    }
    public Float get(String key,float defaultValue){
        return mSharedPreference.getFloat(key,defaultValue);
    }
    public Boolean get(String key,boolean defaultValue){
        return mSharedPreference.getBoolean(key,defaultValue);
    }
    public void deleteSavedData(String key){
        mSharedPreference.edit().remove(key).apply();
    }
}
