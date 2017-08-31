package com.lang.zheren.holder;

import android.view.View;
import android.widget.TextView;

import com.lang.zheren.MyApplication;
import com.lang.zheren.R;

/**
 * 城市列表的holder
 * Created by Administrator on 2017/8/24.
 */

public class CityViewHolder extends MyViewHolder<String> {

    private TextView mTxtCity;

    @Override
    public View initView() {
        View cityView = View.inflate(MyApplication.getContextObject(), R.layout.item_city, null);
        mTxtCity = (TextView) cityView.findViewById(R.id.city);
        return cityView;
    }

    @Override
    public void refreshView(String data) {
        mTxtCity.setText(data);
    }

}
