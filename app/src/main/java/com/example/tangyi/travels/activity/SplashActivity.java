package com.example.tangyi.travels.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.tangyi.travels.R;
import com.example.tangyi.travels.fragment.SplashFragment;

/**
 * Created by aCandy on 2016/9/10.
 */
public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initFragment();

    }
    private void initFragment(){
        SplashFragment splashFragment=new SplashFragment();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.replace(R.id.splash_frame,splashFragment);
        transaction.commit();
    }

}
