package com.lang.zheren.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lang.zheren.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.top_back)
    ImageView mTopBack;
    @BindView(R.id.top_title_login)
    TextView mTopTitleLogin;
    @BindView(R.id.top_register)
    TextView mTopRegister;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.register_phone)
    EditText mRegisterPhone;
    @BindView(R.id.register_verify)
    EditText mRegisterVerify;
    @BindView(R.id.get_verify_code)
    TextView mGetVerifyCode;
    @BindView(R.id.register_pwd)
    EditText mRegisterPwd;
    @BindView(R.id.agreement)
    ImageView mAgreement;
    @BindView(R.id.account_exist)
    TextView mAccountExist;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mTopTitleLogin.setText(R.string.register);
        mTopRegister.setText(R.string.login);
    }

    @OnClick({R.id.top_back, R.id.top_title_login, R.id.top_register, R.id.register_phone, R.id.register_verify, R.id.get_verify_code, R.id.register_pwd, R.id.agreement, R.id.account_exist, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_register:
                break;
            case R.id.register_phone:
                break;
            case R.id.register_verify:
                break;
            case R.id.get_verify_code:
                break;
            case R.id.register_pwd:
                break;
            case R.id.agreement:
                break;
            case R.id.account_exist:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
