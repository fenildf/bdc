package com.example.a00.bdcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_main, null);
        setContentView(view);

        //渐变展示启动
        AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationStart(Animation animation) {}

        });
    }
    /**
     * 跳转到登录界面，这里第一次是登录界面，如果不是第一次就直接跳转首页...
     */
    private void redirectTo(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
