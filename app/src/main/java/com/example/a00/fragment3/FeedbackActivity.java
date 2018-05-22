package com.example.a00.fragment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a00.bdcapp.R;

/**
 * 这一个用来返回用户对我们的反馈，将用户输入的信息返回存储
 */
public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }
}
