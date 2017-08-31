package com.lang.zheren;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.lang.zheren.activity.GuideActivity;
import com.lang.zheren.util.SharePreferenceUtils;

/**
 * 闪屏页
 * Created by Administrator on 2017/8/17.
 */

public class SplashActivity extends AppCompatActivity {
    /**
     * 闪屏页
     */
    private RelativeLayout rlSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        rlSplash = (RelativeLayout) findViewById(R.id.rl_splash);

        startAnim();
    }

    /**
     * 启动动画
     */
    private void startAnim() {
        // 渐变动画,从完全透明到完全不透明
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        // 持续时间 2 秒
        alpha.setDuration(2000);
        // 动画结束后，保持动画状态
        alpha.setFillAfter(true);

        // 设置动画监听器
        alpha.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画结束时回调此方法
            @Override
            public void onAnimationEnd(Animation animation) {
                // 跳转到下一个页面
                jumpNextPage();
            }
        });

        // 启动动画
        rlSplash.startAnimation(alpha);
    }

    /**
     * 跳转到下一个页面
     */
    private void jumpNextPage() {
//        startActivity(new Intent(SplashActivity.this, GuideActivity.class));

        // 判断之前有没有展示过功能引导页
        boolean guideShowed = SharePreferenceUtils.getBoolean(SplashActivity.this,
                SharePreferenceUtils.GUIDE_SHOWED, false);

        // 如果没有展示过功能引导页
        if (!guideShowed) {
            // 跳转到功能引导页
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
        } else {
            // 跳转到主页面
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
        finish();
    }
}
