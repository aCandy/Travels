package com.example.tangyi.travels.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tangyi.travels.R;
import com.example.tangyi.travels.bean.ZhihuNewsBean;

import java.util.ArrayList;

/**
 * RecyclerView控件适配器
 */
public class ZhihuAdapter extends RecyclerView.Adapter<ZhihuAdapter.ViewHolder>{
    /**
     * 创建适配器对象时就维护一个数据对象，用于数据追加
     * 不用再每次解析完数据后判断是否是更多数据选择追加还是新建数据对象
     */
    private ArrayList<ZhihuNewsBean.Stories> dataList=new ArrayList<>();
    private Activity mActivity;
    public ZhihuAdapter(Activity mActivity){
        super();
        this.mActivity=mActivity;
    }

    /**
     * 自定义addAllData()方法，用于解析完后的数据追加
     * @param dataList
     */
    public void addAllData(ArrayList<ZhihuNewsBean.Stories> dataList) {
        //数据追加
        this.dataList.addAll(dataList);
        //刷新RecyclerView
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        //返回条目数，返回多少，就展示多少
        return dataList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //数据填充
        holder.newsTitle.setText(dataList.get(position).getTitle());
        Glide.with(mActivity)
                .load(dataList.get(position).getImages().get(0))
                .into(holder.newsImg);
    }

    //设置RecyclerView的Item布局
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(parent.getContext(), R.layout.zhihu_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //用于性能优化的ViewHolder类
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView newsTitle;
        public ImageView newsImg;
        public ViewHolder(View itemView){
            super(itemView);
            newsTitle = (TextView)itemView.findViewById(R.id.zhihu_item_text);
            newsImg = (ImageView)itemView.findViewById(R.id.zhihu_item_img);
        }
    }

}
