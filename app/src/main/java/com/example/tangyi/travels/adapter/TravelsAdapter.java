package com.example.tangyi.travels.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tangyi.travels.R;
import com.example.tangyi.travels.bean.TravelsBean;
import com.example.tangyi.travels.bean.ZhihuNewsBean;

import java.util.ArrayList;


/**
 * Created by aCandy on 2016/9/11.
 */
public class TravelsAdapter extends RecyclerView.Adapter{

    private ArrayList<TravelsBean.MyData.Content> booksList=new ArrayList<>();
    private Activity mActivity;
    public TravelsAdapter(Activity activity) {
        super();
        this.mActivity=activity;
    }
    /**
     * 自定义addAllData()方法，用于解析完后的数据追加
     * @param booksList
     */
    public void addAllData(ArrayList<TravelsBean.MyData.Content> booksList) {
        //数据追加
        this.booksList.addAll(booksList);
        //刷新RecyclerView
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.recyclerview_item, null);
        // 创建一个ViewHolder
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        viewHolder.itemText.setText(booksList.get(position).getTitle());
        Glide.with(mActivity)
                .load(booksList.get(position).getHeadImage())
                .into(viewHolder.itemImg);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView itemText;
        public ImageView itemImg;

        public ViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.listview_item_text);
            itemImg = (ImageView) itemView.findViewById(R.id.listview_item_img);
        }
    }


}
