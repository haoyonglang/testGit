package com.lang.zheren.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lang.zheren.R;
import com.lang.zheren.entity.UserDataManager;

/**
 * Created by Administrator on 2017/8/14.
 */

public class TestLoginActivity extends AppCompatActivity {

    public int pwdResetFlag = 0;
    private EditText mAccount;                         //用户名编辑
    private EditText mPwd;                             //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮
    private Button mCancleButton;                     //注销按钮
    private CheckBox mRememberCheck;                  //是否记住密码
    private TextView mforgetPwd;                      //忘记密码

    private SharedPreferences login_sp;
    private String userNameValue, passwordValue;

    private View loginView;                           //登录
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private UserDataManager mUserDataManager;         //用户数据管理类


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_test);
        //通过id找到相应的控件
        mAccount = (EditText) findViewById(R.id.username);
        mPwd = (EditText) findViewById(R.id.login_edit_pwd);
        mRegisterButton = (Button) findViewById(R.id.btn_register);
        mLoginButton = (Button) findViewById(R.id.btn_login);
        mCancleButton = (Button) findViewById(R.id.login_btn_cancle);
        loginView = findViewById(R.id.login_view);
        loginSuccessView = findViewById(R.id.login_success_view);
        loginSuccessShow = (TextView) findViewById(R.id.login_success_show);

        mChangepwdText = (TextView) findViewById(R.id.login_text_change_pwd);
        mforgetPwd = (TextView) findViewById(R.id.txt_forget_pwd);

        mRememberCheck = (CheckBox) findViewById(R.id.ckb_remmber);

        login_sp = getSharedPreferences("userInfo", 0);
        String name = login_sp.getString("USER_NAME", "");
        String pwd = login_sp.getString("PASSWORD", "");
        boolean choseRemember = login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin = login_sp.getBoolean("mAutologinCheck", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if (choseRemember) {
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }

        mRegisterButton.setOnClickListener(mListener);                      //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mLoginButton.setOnClickListener(mListener);
        mCancleButton.setOnClickListener(mListener);
        mChangepwdText.setOnClickListener(mListener);

        ImageView image = (ImageView) findViewById(R.id.logo);             //使用ImageView显示logo
        image.setImageResource(R.mipmap.logo);

        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();                              //建立本地数据库
        }
    }

    View.OnClickListener mListener = new View.OnClickListener() {                  //不同按钮按下的监听事件选择
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_register:                            //登录界面的注册按钮
                    Intent intent_Login_to_Register = new Intent(TestLoginActivity.this, TestRegisterActivity.class);    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_Register);
                    finish();
                    break;
                case R.id.btn_login:                              //登录界面的登录按钮
                    login();
                    break;
                case R.id.login_btn_cancle:                             //登录界面的注销按钮
                    cancel();
                    break;
                case R.id.txt_forget_pwd:                             //忘记密码
                    forgetPwd();
                    break;
                case R.id.login_text_change_pwd:                             //登录界面的注销按钮
                    Intent intent_Login_to_reset = new Intent(TestLoginActivity.this, Resetpwd.class);    //切换Login Activity至User Activity
                    startActivity(intent_Login_to_reset);
                    finish();
                    break;
            }
        }
    };

    /**
     * 忘记密码
     */
    private void forgetPwd() {

    }


    public void login() {                                              //登录按钮监听事件
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = mPwd.getText().toString().trim();
            SharedPreferences.Editor editor = login_sp.edit();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if (result == 1) {                                             //返回1说明用户名和密码均正确
                //保存用户名和密码
                editor.putString("USER_NAME", userName);
                editor.putString("PASSWORD", userPwd);

                //是否记住密码
                if (mRememberCheck.isChecked()) {
                    editor.putBoolean("mRememberCheck", true);
                } else {
                    editor.putBoolean("mRememberCheck", false);
                }
                editor.commit();

                Intent intent = new Intent(TestLoginActivity.this, User.class);    //切换Login Activity至User Activity
                startActivity(intent);
                finish();
                Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();//登录成功提示
            } else if (result == 0) {
                Toast.makeText(this, getString(R.string.login_fail), Toast.LENGTH_SHORT).show();  //登录失败提示
            }
        }
    }

    public void cancel() {           //注销
        if (isUserNameAndPwdValid()) {
            String userName = mAccount.getText().toString().trim();    //获取当前输入的用户名和密码信息
            String userPwd = mPwd.getText().toString().trim();
            int result = mUserDataManager.findUserByNameAndPwd(userName, userPwd);
            if (result == 1) {                                             //返回1说明用户名和密码均正确
                Toast.makeText(this, getString(R.string.cancel_success), Toast.LENGTH_SHORT).show();
//                <span style = "font-family: Arial;" ></span >//注销成功提示
                mPwd.setText("");
                mAccount.setText("");
                mUserDataManager.deleteUserDatabyname(userName);
            } else if (result == 0) {
                Toast.makeText(this, getString(R.string.cancel_fail), Toast.LENGTH_SHORT).show();  //注销失败提示
            }
        }

    }

    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.account_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            Toast.makeText(this, getString(R.string.pwd_empty),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onResume() {
        if (mUserDataManager == null) {
            mUserDataManager = new UserDataManager(this);
            mUserDataManager.openDataBase();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mUserDataManager != null) {
            mUserDataManager.closeDataBase();
            mUserDataManager = null;
        }
        super.onPause();
    }
}
