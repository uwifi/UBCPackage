package com.ubc.ylkjcjq.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.views.RecyclerViewItemClickListener;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by ylkjcjq on 2017/8/28.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public String[] datas = null;
    public RecyclerViewAdapter(String[] datas) {
        this.datas = datas;
    }

    public RecyclerViewItemClickListener mRecyclerViewItemClickListener;

    public void setmRecyclerViewItemClickListener(RecyclerViewItemClickListener mRecyclerViewItemClickListener) {
        this.mRecyclerViewItemClickListener = mRecyclerViewItemClickListener;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_item_,viewGroup,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        if(mRecyclerViewItemClickListener != null){
            viewHolder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRecyclerViewItemClickListener.onItemClick(viewHolder.view,position);
                }
            });
        }
        viewHolder.mTextView.setText(datas[position]);
    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.length;
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public View view;
        public ViewHolder(View view){
            super(view);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(view);
            this.view = view;
            mTextView = (TextView) view.findViewById(R.id.name);
        }
    }
}
