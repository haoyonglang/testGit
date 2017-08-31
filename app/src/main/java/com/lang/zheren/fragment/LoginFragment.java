package com.lang.zheren.fragment;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lang.zheren.MainActivity;
import com.lang.zheren.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 * Created by Administrator on 2017/8/14.
 */

public class LoginFragment extends BaseFragment {
    @BindView(R.id.ckb_remmber)
    CheckBox mCheckVisible;
    @BindView(R.id.username)
    EditText mUserName;
    @BindView(R.id.login_edit_pwd)
    EditText mUserPsw;

    @OnClick(R.id.btn_login)
    public void onLoginBtnClicked() {
        Intent intent = new Intent(getHoldingActivity(), MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_register)
    public void onRegisterBtnClicked() {
        pushFragment(VerifyFragment.newInstance(VerifyFragment.REGISTER));
    }

    @OnClick(R.id.txt_forget_pwd)
    public void onForgetBtnClicked() {
        pushFragment(VerifyFragment.newInstance(VerifyFragment.RESET));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_test;
    }

    @Override
    protected void initActionBar(ActionBar actionBar) {
        super.initActionBar(actionBar);
        actionBar.setTitle(R.string.login);
    }
}
