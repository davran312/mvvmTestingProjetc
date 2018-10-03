package com.example.admin.exercise.ui;

import android.app.Application;
import android.content.Context;

import com.example.admin.exercise.data.DataManager;

import javax.inject.Inject;

public class DemoApplication extends Application {

    protected ApplicationComponent applicationComponent;

    @Inject
    DataManager dataManager;

    public static DemoApplication get(Context context){
        return (DemoApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(
                new ApplicationModule(this)).build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }
}


