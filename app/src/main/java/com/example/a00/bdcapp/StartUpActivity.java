package com.example.a00.bdcapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.simulation.bdc.enitity.DailySentence;
import com.simulation.bdc.util.ConnecteURL;
import com.simulation.bdc.util.ParseJson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class StartUpActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    final private int FAIL_TO_GET_DAIYSENTENCE = 0;
    final private int SUCESS_TO_GET_DAIYSENTENCE = 1;
    private ImageView dailySentenceImageView;
    /**
     * 处理message的信息
     */
    private Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FAIL_TO_GET_DAIYSENTENCE: {
                    break;
                }
                case SUCESS_TO_GET_DAIYSENTENCE: {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    if (bitmap != null) {
                        dailySentenceImageView.setImageBitmap(bitmap);
                    }
                    break;
                }
            }
        }
    };
    /**
     * 每日一句 Runable
     */
    private Runnable getDailySentencePicture = new Runnable() {
        @Override
        public void run() {
            Date date = new Date();
            String url = "http://open.iciba.com/dsapi/?date=" + date.getYear() + "-" + date.getMonth() + "-" + date.getDay();
            String dailySentenceJson = new ConnecteURL().getJosnString(url);
            DailySentence dailySentence = ParseJson.parseDailySentenceJson(dailySentenceJson);
            Message message = new Message();
            if (dailySentence == null) {
                message.what = FAIL_TO_GET_DAIYSENTENCE;
            } else {
                message.what = SUCESS_TO_GET_DAIYSENTENCE;
                String pictureUrl = dailySentence.getFenxiang_img();
                message.obj = getPictureBitMap(pictureUrl);
            }
            handler.sendMessage(message);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(this, R.layout.activity_startup, null);
        setContentView(view);

        dailySentenceImageView = findViewById(R.id.daily_sentence);
        Log.d(TAG, "onCreate: " + dailySentenceImageView);
        new Thread(getDailySentencePicture).start(); //访问网页获取每日一句图片

        //渐变展示启动
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }

        });
    }

    /**
     * 获取每日一句图片的Bitmap对象
     *
     * @param url
     * @return
     */
    public Bitmap getPictureBitMap(String url) {
        URL pictureUrl = null;
        Bitmap bitmap = null;
        try {
            pictureUrl = new URL(url);
        } catch (MalformedURLException e) {
            Log.e(TAG, "returnBitMap: " + e);
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) pictureUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "returnBitMap: " + e);
        }
        return bitmap;
    }

    /**
     * 如何检测到用户以前的登录信息并还未失效则跳转到首页 并更新用户信息
     */
    private void redirectTo() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}