package com.example.tangyi.travels.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tangyi.travels.R;
import com.example.tangyi.travels.activity.MainActivity;
import com.example.tangyi.travels.bean.SplashImageBean;
import com.example.tangyi.travels.tools.CMD;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.StringReader;

/**
 * Created by aCandy on 2016/9/10.
 */
public class SplashFragment extends Fragment {
    private ImageView splash_bg;
    private TextView bg_author;
    private RelativeLayout mReLayout;
    private Activity mActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_splash,null);

        splash_bg = (ImageView)view.findViewById(R.id.splash_img);
        bg_author = (TextView)view.findViewById(R.id.splash_img_author);
        mReLayout = (RelativeLayout)view.findViewById(R.id.splash_relativelayout);
        mActivity = getActivity();
        initView();
        initAnimation();
        return view;
    }
    private void initView(){
        RequestParams params=new RequestParams(CMD.SPLASH_IMAGE);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
            //请求成功
            @Override
            public void onSuccess(String result) {
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }
        });
    }
    private void processData(String json){
        try {
            Gson gson=new Gson();
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            SplashImageBean splashBean=gson.fromJson(reader,SplashImageBean.class);
            String author=splashBean.getText();
            String imgURL=splashBean.getImg();
            if (imgURL!=null){
                Glide.with(getActivity())
                        .load(imgURL)
                        .into(splash_bg);
                bg_author.setText(author);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void initAnimation(){
        Animation animation= AnimationUtils.loadAnimation(getActivity(),R.anim.alpha_scale);
        animation.setFillAfter(true);
        mReLayout.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getActivity(),MainActivity.class));
                mActivity.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
