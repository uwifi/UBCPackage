package com.ubc.ylkjcjq.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubc.ylkjcjq.R;
import com.ubc.ylkjcjq.models.EndMenu;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by castl on 2016/5/13.
 */
public class EndMenuItemAdapter extends BaseAdapter {

    private List<EndMenu> objects = new ArrayList<EndMenu>();

    @SuppressWarnings("unused")
    private Context context;
    private LayoutInflater layoutInflater;

    public EndMenuItemAdapter(Context context, List<EndMenu> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
    }

    public List<EndMenu> setObjects(List<EndMenu> objects) {
        return this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public EndMenu getItem(int position) {
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
            convertView.setTag(viewHolder);
            AutoUtils.autoSize(convertView);
        } else {
            viewHolder = (EndMenuItemAdapter.ViewHolder) convertView.getTag();
        }
        initializeViews((EndMenu) getItem(position), (EndMenuItemAdapter.ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(EndMenu object, EndMenuItemAdapter.ViewHolder holder) {
//        Picasso.with(context)
//                .load("file://" + object.getUri_thumb())
//                .error(R.mipmap.ic_error)
//                .fit()
//                .centerCrop()
//                .into(holder.imgv);
        holder.tv.setText(object.getName());

    }

    protected class ViewHolder {
        private ImageView imgv;
        private TextView tv;
    }
}

