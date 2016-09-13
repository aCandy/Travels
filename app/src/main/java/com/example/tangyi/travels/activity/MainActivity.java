package com.example.tangyi.travels.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.tangyi.travels.R;
import com.example.tangyi.travels.fragment.LeftMenuFragment;
import com.example.tangyi.travels.fragment.ZhihuFragment;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by aCandy on 2016/9/10.
 *
 * Window window=getWindow();
 * 透明实体键（慎用！）
 * window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
 * 透明状态栏
 * window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
 */
public class MainActivity extends Activity {
    private DrawerLayout mDwerLayout;
    private ZhihuFragment zhihuFragment;
    private FragmentManager fm;
    private LinearLayout leftLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fram);

        initFragment();
        initData();
    }
    //标题栏滑动监听
    public void stClick(View view){
        if (mDwerLayout!= null &&leftLinear!= null){
            mDwerLayout.openDrawer(leftLinear);
        }

    }


    private void initData(){
        mDwerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        leftLinear=(LinearLayout)findViewById(R.id.left_linear);
        mDwerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                View mContent =mDwerLayout.getChildAt(0);
                View mMenu = drawerView;
                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;



                float leftScale = 1 - 0.3f * scale;

                ViewHelper.setScaleX(mMenu, leftScale);
                ViewHelper.setScaleY(mMenu, leftScale);
                ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                ViewHelper.setTranslationX(mContent,
                        mMenu.getMeasuredWidth() * (1 - scale));
                ViewHelper.setPivotX(mContent, 0);
                ViewHelper.setPivotY(mContent,
                        mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);
            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDwerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }
    private void initFragment(){
        fm=getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        LeftMenuFragment menuFragment=new LeftMenuFragment();
        transaction.replace(R.id.menu_frame_layout,menuFragment);
        zhihuFragment=new ZhihuFragment();
        transaction.replace(R.id.content_frame_layout,zhihuFragment);
        transaction.commit();

    }
}
