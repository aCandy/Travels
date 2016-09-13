package com.example.tangyi.travels.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tangyi.travels.R;

/**
 * Created by aCandy on 2016/9/11.
 */
public class LeftMenuFragment extends Fragment{
    private RadioGroup radioGroup;
    private ZhihuFragment zhihuFragment;
    private TravelsFragment travelsFragment;
    private FragmentManager fm;
    private DrawerLayout mDrawer;
    private TextView textView;
    private Activity mActivity;
    private ImageButton listButton;
    private Animation animation;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmen_leftmenu,null);
        mActivity=getActivity();
        fm=getActivity().getFragmentManager();
        mDrawer=(DrawerLayout)mActivity.findViewById(R.id.drawer_layout);
        listButton=(ImageButton)mActivity.findViewById(R.id.setting_imageButton);
        textView=(TextView)mActivity.findViewById(R.id.textView2);
        radioGroup = (RadioGroup)view.findViewById(R.id.radioGroup);
        radioGroup.check(R.id.zhihu_button);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.zhihu_button:
                        if (zhihuFragment==null){
                            zhihuFragment=new ZhihuFragment();
                        }
                        switchContent(travelsFragment,zhihuFragment);

                        mDrawer.closeDrawers();
                        textView.setText(R.string.zhihu_project_text);
                        break;
                    case R.id.travels_button:
                        if (travelsFragment==null){
                            travelsFragment=new TravelsFragment();
                            FragmentTransaction transaction=fm.beginTransaction();
                            transaction.replace(R.id.content_frame_layout,travelsFragment);
                            transaction.commit();
                        }else {
                            switchContent(zhihuFragment,travelsFragment);
                        }

                        mDrawer.closeDrawers();
                        textView.setText(R.string.travels_project_text);
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
    private void switchContent(Fragment from,Fragment to){

        FragmentTransaction transaction = fm.beginTransaction();
        if (!to.isAdded()) { // 先判断是否被add过
            transaction.hide(from).add(R.id.content_frame_layout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }

    }


}
