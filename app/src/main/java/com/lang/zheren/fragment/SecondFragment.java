package com.lang.zheren.fragment;

import android.os.Bundle;
import android.view.View;

import com.lang.zheren.R;

/**
 * Created by Administrator on 2017/8/14.
 */

public class SecondFragment extends BaseFragment {

    public static String SECOND_FRAGMENT = "second_fragment";

    public static SecondFragment newInstance(String msg) {
        SecondFragment fragment = new SecondFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(SECOND_FRAGMENT, msg);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_second;
    }
}
