package com.example.admin.exercise.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.admin.exercise.R;
import com.example.admin.exercise.data.DataManager;
import com.example.admin.exercise.model.User;
import com.example.admin.exercise.ui.DemoApplication;

public class MainActivity extends AppCompatActivity {
    DataManager mDataManager;
    private ActivityComponent activityComponent;
    private TextView mTvUserInfo;
    private TextView mTvAccessToken;

    public ActivityComponent getActivityComponent() {
        if(activityComponent == null){
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(DemoApplication.get(this)
                    .getComponent()).build();
        }
        return activityComponent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        mTvUserInfo = findViewById(R.id.tv_user_info);
        mTvAccessToken = findViewById(R.id.tv.access_token);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
       super.onPostCreate(savedInstanceState);
       createUser();
       getUser();
    }

    private void createUser() {

    }
    private void getUser(){
        try{
            User user =  mDataManager.getUser(1L);
            mTvUserInfo.setText(user.toString());
        }catch (Exception e){e.printStackTrace();}
    }
}
