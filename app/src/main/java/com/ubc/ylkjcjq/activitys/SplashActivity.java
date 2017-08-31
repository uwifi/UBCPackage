package com.ubc.ylkjcjq.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ubc.ylkjcjq.R;
import com.zhy.autolayout.AutoLayoutActivity;


public class SplashActivity extends BaseActivity  {

    private ImageView splash_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_loading = (ImageView) findViewById(R.id.main_iv_loading);
        anim();
    }

    // 设置动画
    private void anim() {
        Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.main_loadingpage);

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!mLoginConfig.getAvailbleTime().equals("0")) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    goLogin();
                }
            }

        });
        splash_loading.setAnimation(anim);
        anim.start();
    }

    // 进入登录页
    private void goLogin() {

        Intent intent = new Intent(SplashActivity.this,
                LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
