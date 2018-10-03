package com.example.admin.exercise.data;

import android.content.Context;
import android.content.res.Resources;

import com.example.admin.exercise.data.local.db.DbHelper;
import com.example.admin.exercise.data.local.prefs.SharedPrefsHelper;
import com.example.admin.exercise.model.ApplicationContext;
import com.example.admin.exercise.model.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {
    private Context mContext;
    private DbHelper mDbHelper;
    private SharedPrefsHelper mSharedPrefsHelper;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       DbHelper dbHelper,
                       SharedPrefsHelper sharedPrefsHelper){
        mContext = context;
        mDbHelper = dbHelper;
        mSharedPrefsHelper = sharedPrefsHelper;
    }

    public void saveAccessToken(String accessToken){
        mSharedPrefsHelper.put(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN,accessToken);
    }
    public String getAccessToken(){
        return mSharedPrefsHelper.get(SharedPrefsHelper.PREF_KEY_ACCESS_TOKEN,
                null);
    }

    public Long createUser(User user) throws  Exception{
       return mDbHelper.insertUser(user);
    }
    public User getUser(Long userId) throws Resources.NotFoundException,NullPointerException{
        return mDbHelper.getUser(userId);
    }
}
