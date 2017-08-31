package com.lang.zheren.fragment;

import android.os.Bundle;
import android.view.View;

import com.lang.zheren.R;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ThirdFragment extends BaseFragment {

    public static String THIRD_FRAGMENT = "third_fragment";

    public static ThirdFragment newInstance(String msg) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(THIRD_FRAGMENT, msg);
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
