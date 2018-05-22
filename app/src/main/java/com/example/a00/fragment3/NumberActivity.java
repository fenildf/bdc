package com.example.a00.fragment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a00.bdcapp.R;

/**
 * 用来用户选择听写单词数目选择的界面，将选择的单词数量返回到服务器
 */
public class NumberActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
    }
}
