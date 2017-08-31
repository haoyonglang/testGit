package com.lang.zheren.holder;

import android.view.View;
import android.widget.TextView;

import com.lang.zheren.MyApplication;
import com.lang.zheren.R;

/**
 * 区列表的holder
 * Created by Administrator on 2017/8/24.
 */

public class AreaViewHolder extends MyViewHolder<String> {

    private TextView mTxtArea;

    @Override
    public View initView() {
        View areaView = View.inflate(MyApplication.getContextObject(), R.layout.item_area, null);
        mTxtArea = (TextView) areaView.findViewById(R.id.area);
        return areaView;
    }

    @Override
    public void refreshView(String data) {
        mTxtArea.setText(data);
    }

}
