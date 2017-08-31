package com.lang.zheren.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lang.zheren.R;

/**
 * Created by Administrator on 2017/8/24.
 */

public class ChildrenCategoryAdapter2 extends BaseAdapter {
    private Context mContext;
    private String[] str;
    private int pos;

    public ChildrenCategoryAdapter2(Context context) {
        mContext = context;
    }

    public void setDatas(String[] str) {
        this.str = str;
    }

    @Override
    public int getCount() {
        if (str == null) {
            return 0;
        }
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_children_category_item, null);
            holder.tvChildrenCategoryName = (TextView) convertView.findViewById(R.id.tv_children_category_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvChildrenCategoryName.setText(str[position]);

        if (pos == position) {
            holder.tvChildrenCategoryName.setTextColor(mContext.getResources().getColor(R.color.list_text_select_color));
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.zu_choose_right_item_bg));
        } else {
            holder.tvChildrenCategoryName.setTextColor(mContext.getResources().getColor(android.R.color.black));
            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.zu_choose_left_item_bg));
        }

        return convertView;
    }

    private class ViewHolder {
        private TextView tvChildrenCategoryName;
    }
}
