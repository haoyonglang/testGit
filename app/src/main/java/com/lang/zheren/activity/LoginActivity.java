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
import com.lang.zheren.util.ActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/22.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.top_back)
    ImageView mTopBack;
    @BindView(R.id.top_title_login)
    TextView mTopTitleLogin;
    @BindView(R.id.top_register)
    TextView mTopRegister;
    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.login_username)
    EditText mLoginUsername;
    @BindView(R.id.login_pwd)
    EditText mLoginPwd;
    @BindView(R.id.register_now)
    TextView mRegisterNow;
    @BindView(R.id.forget_pwd)
    TextView mForgetPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.top_back, R.id.top_title_login, R.id.top_register, R.id.login_username, R.id.login_pwd, R.id.register_now, R.id.forget_pwd, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.top_register:
                ActivityUtil.gotoActivity(this, RegisterActivity.class);
                break;
            case R.id.login_username:
                break;
            case R.id.login_pwd:
                break;
            case R.id.register_now:
                ActivityUtil.gotoActivity(this, RegisterActivity.class);
                break;
            case R.id.forget_pwd:
                break;
            case R.id.btn_login:
                break;
        }
    }
}
