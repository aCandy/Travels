package com.example.tangyi.travels.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tangyi.travels.R;
import com.example.tangyi.travels.adapter.TravelsAdapter;
import com.example.tangyi.travels.adapter.ZhihuAdapter;
import com.example.tangyi.travels.bean.TravelsBean;
import com.example.tangyi.travels.bean.ZhihuNewsBean;
import com.example.tangyi.travels.tools.CMD;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by aCandy on 2016/9/10.
 */
public class TravelsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        PullLoadMoreRecyclerView.PullLoadMoreListener{
    private PullLoadMoreRecyclerView pullRecyView;
    private TravelsAdapter mAdapter;
    private ArrayList<TravelsBean.MyData.Content> booksList;
    private SwipeRefreshLayout mSwipesh;
    private int isMore=1;
    private RequestParams params;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获取控件实例
        pullRecyView=(PullLoadMoreRecyclerView)view.findViewById(R.id.travels_pull_recycle_view);
        //设置线性布局
        pullRecyView.setLinearLayout();
        //不需要下拉刷新(这里下拉刷新使用的是官方的SwipeRefreshLayout)
        pullRecyView.setPullRefreshEnable(false);
        //设置加载更多背景色
       // pullRecyView.setFooterViewBackgroundColor(R.color.title_color);
        //设置下载更多文字
        pullRecyView.setFooterViewText("加载中...");
        //下拉加载更多的文字颜色
        pullRecyView.setFooterViewTextColor(R.color.title_color);
        //官方下拉刷新控件
        mSwipesh=(SwipeRefreshLayout)view.findViewById(R.id.travels_swipesh);
        //控件颜色
        mSwipesh.setColorSchemeResources(R.color.title_color);
        //创建适配器对象
        mAdapter=new TravelsAdapter(getActivity());
        //设置适配器
        pullRecyView.setAdapter(mAdapter);
        //下拉刷新监听
        mSwipesh.setOnRefreshListener(this);
        //加载更多监听
        pullRecyView.setOnPullLoadMoreListener(this);

        //初始化数据
        initHttpData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_travels,null);
    }

    //初始化数据
    private void initHttpData(){
        if (isMore==1){
            //Xutils3数据请求
            params = new RequestParams(CMD.TRAVELS_NEWS+isMore);
        }else {
             params=new RequestParams(CMD.TRAVELS_NEWS+isMore);
        }

        //添加请求头
        params.addHeader("apikey",CMD.API_KEY);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                //数据解析
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    private void processData(String json){
        try{
            //官方框架Gson用于解析JSON数据
            Gson gson=new Gson();

            /**
             * gson.fromJson()函数意思是将Json数据转换为JAVA对象。
             * Json数据中字段对应的内容就是JAVA对象中字段内所存储的内容。
             * JsonReader reader = new JsonReader(new StringReader(json));
             * reader.setLenient(true);
             * 这两句代码的意思是设置JSON数据解析时为宽松模式，不容易OOM
             */
            JsonReader reader = new JsonReader(new StringReader(json));
            reader.setLenient(true);
            TravelsBean travelsBean= gson.fromJson(reader,TravelsBean.class);
            //创建数据对象
            booksList = travelsBean.getData().getBooks();
            //追加到数据集合中addAllData()为自定义方法
            mAdapter.addAllData(booksList);
            //刷新结束
            pullRecyView.setPullLoadMoreCompleted();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //下拉刷新回调
    @Override
    public void onRefresh() {
        initHttpData();
        isMore=1;
        mSwipesh.setRefreshing(false);
    }
    //加载更多回调
    @Override
    public void onLoadMore() {
        isMore++;
        initHttpData();
    }
}
