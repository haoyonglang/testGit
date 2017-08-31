package com.lang.zheren.holder;

import android.view.View;
import android.widget.TextView;

import com.lang.zheren.MyApplication;
import com.lang.zheren.R;

/**
 * 街道列表的holder
 * Created by Administrator on 2017/8/24.
 */

public class StreetViewHolder extends MyViewHolder<String> {

    private TextView mTxtStreet;

    @Override
    public View initView() {
        View streetView = View.inflate(MyApplication.getContextObject(), R.layout.item_street, null);
        mTxtStreet = (TextView) streetView.findViewById(R.id.street);
        return streetView;
    }

    @Override
    public void refreshView(String data) {
        mTxtStreet.setText(data);
    }

}
