package com.lang.zheren.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lang.zheren.MyApplication;
import com.lang.zheren.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 城市列表的适配器
 * Created by Administrator on 2017/8/24.
 */

public class CityAdapter extends BaseAdapter {

    private List<String> mData = new ArrayList<>();
    //默认第一个为选中
    private int mCurrentItem = 0;
    private boolean isClick = false;

    public CityAdapter(List<String> data) {
        this.mData = data;
        for (int i = 0; i < 20; i++) {
            mData.add("城市：" + i);
        }
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = View.inflate(MyApplication.getContextObject(), R.layout.item_city, null);

        TextView city = (TextView) inflate.findViewById(R.id.city);

        city.setText(mData.get(i));

        // 只有当更新的位置等于当前位置时，更改颜色
        if (mCurrentItem == i && isClick) {
            city.setTextColor(Color.RED);
        } else {
            city.setTextColor(Color.BLACK);
        }

        notifyDataSetChanged();
        return inflate;
    }

    //设置set方法
    public void setCurrentItem(int currentItem) {
        this.mCurrentItem = currentItem;
    }

    public void setClick(boolean click) {
        this.isClick = click;
    }

}
