package com.lang.zheren.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.lang.zheren.R;

/**
 * 修改密码
 * Created by Administrator on 2017/8/14.
 */

public class VerifyFragment extends BaseFragment {
    public static final String ARG_VERIFY = "verify";

    public static final int REGISTER = 0;
    public static final int RESET = 1;

    private int mAction;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        if (mAction == RESET) {
            actionBar.setTitle(R.string.reset_psw);
        } else {
            actionBar.setTitle(R.string.register);
        }
    }

    public static VerifyFragment newInstance(int action) {
        VerifyFragment fragment = new VerifyFragment();
        Bundle arg = new Bundle();
        arg.putInt(ARG_VERIFY, action);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAction = getArguments().getInt(ARG_VERIFY);
        }
    }

}
