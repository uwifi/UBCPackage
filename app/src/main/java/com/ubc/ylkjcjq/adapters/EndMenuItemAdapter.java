package com.ubc.ylkjcjq.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.models.Wallet;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by castl on 2017/8/23.
 */
public class EndMenuItemAdapter extends BaseAdapter {

    private List<Wallet> objects = new ArrayList<Wallet>();

    @SuppressWarnings("unused")
    private Context context;
    private LayoutInflater layoutInflater;

    public EndMenuItemAdapter(Context context, List<Wallet> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public List<Wallet> setObjects(List<Wallet> objects) {
        return this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Wallet getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 数据量过大可能出现错乱，暂时不用缓存策略
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EndMenuItemAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.drawer_list_item, parent, false);
            viewHolder = new EndMenuItemAdapter.ViewHolder();
            viewHolder.imgv = (ImageView) convertView.findViewById(R.id.imgv);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.view = (FrameLayout) convertView.findViewById(R.id.view);
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        } else {
            viewHolder = (EndMenuItemAdapter.ViewHolder) convertView.getTag();
        }
        initializeViews((Wallet) getItem(position), (EndMenuItemAdapter.ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Wallet object, EndMenuItemAdapter.ViewHolder holder) {
//        Picasso.with(context)
//                .load("file://" + object.getUri_thumb())
//                .error(R.mipmap.ic_error)
//                .fit()
//                .centerCrop()
//                .into(holder.imgv);
        holder.tv.setText(object.getProjectAppellation());
        if(object.isShow()){
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.gray_btn_bg_color));
        }else {
            holder.view.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

    }

    protected class ViewHolder {
        private ImageView imgv;
        private FrameLayout view;
        private TextView tv;
    }
}

