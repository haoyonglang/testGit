package com.lang.zheren.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lang.zheren.holder.MyViewHolder;

import java.util.ArrayList;

/**
 * listview的封装
 * <p>
 *
 *  封装思路:
     将BaseAdapter封装成MyBaseAdapter
     将ViewHolder封装成BaseHolder
     让BaseHolder和MyBaseAdapter打交道
     在用到ListView的时候直接创建一个对应的Holder即可.

 * Created by acer on 2017/8/23.
 */

public abstract class MyListAdapter<T> extends BaseAdapter {
    private ArrayList<T> data;

    public MyListAdapter(ArrayList<T> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (convertView == null) {
            //1.加载布局文件
            //2,初始化控件fvb
            //3.打标记tag
            holder = getHolder();
        }else{
            holder= (MyViewHolder) convertView.getTag();
        }
        //4.设置数据刷新界面

        holder.setData(getItem(position));

        return holder.getRootView();
    }

    /**
     *拿到holder的抽象方法,让子类实现
     *@author zfy
     *@created at 2016/7/20 17:03
     */
    public abstract MyViewHolder getHolder();
}
