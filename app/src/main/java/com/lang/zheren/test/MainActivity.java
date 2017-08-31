package com.lang.zheren.test;

import android.app.Activity;
import android.os.Bundle;

import com.lang.zheren.R;
import com.lang.zheren.util.ActivityUtil;
import com.lang.zheren.view.WheelView;

import java.util.Arrays;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String[] PLANETS = new String[]{"一", "二", "三", "四", "五", "六", "七", "八"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        WheelView wva = (WheelView) findViewById(R.id.main_wv);
        wva.setOffset(1);
        wva.setItems(Arrays.asList(PLANETS));
        wva.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                ActivityUtil.showToast(MainActivity.this, item);
            }
        });

        initView();
        initData();
        initListener();

    }

    private void initView() {

    }

    private void initData() {

    }

    private void initListener() {

    }

}
