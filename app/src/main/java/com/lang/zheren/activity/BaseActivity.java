package com.lang.zheren.activity;

import com.lang.zheren.R;

/**
 * 封装BaseActivity
 * <p>
 * Created by Administrator on 2017/8/14.
 */

public abstract class BaseActivity extends AppActivity {
    @Override
    protected int getContentViewId() {
        return R.layout.activity_base;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }
}
