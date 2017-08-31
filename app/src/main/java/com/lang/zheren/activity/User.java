package com.lang.zheren.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lang.zheren.R;

/**
 * Created by Administrator on 2017/8/14.
 */

public class User extends AppCompatActivity {
    private Button mReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        mReturnButton = (Button) findViewById(R.id.returnback);
    }

    public void back_to_login(View view) {
        Intent intent3 = new Intent(User.this, TestLoginActivity.class);
        startActivity(intent3);
        finish();
    }
}
