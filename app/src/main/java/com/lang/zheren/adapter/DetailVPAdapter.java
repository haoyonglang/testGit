package com.lang.zheren.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 详情列表的viewpager的适配器
 * Created by Administrator on 2017/8/30.
 */

public class DetailVPAdapter extends PagerAdapter {

    private List<ImageView> mData = new ArrayList<>();

    public DetailVPAdapter(List<ImageView> data) {
        this.mData = data;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }
}
