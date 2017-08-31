package com.lang.zheren.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lang.zheren.R;
import com.lang.zheren.viewholder.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected List<T> data;

    public MyBaseAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getHolder(convertView, parent, position, R.layout.item_house_list);
        setData(holder, data.get(position));
        return holder.getConvertView();
    }

    public abstract void setData(ViewHolder holder, T t);
}