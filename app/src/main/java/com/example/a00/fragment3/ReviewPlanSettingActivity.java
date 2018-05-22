package com.example.a00.fragment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

/**
 * 这一个用于用户自己选择修改复习计划，
 */
public class ReviewPlanSettingActivity extends AppCompatActivity {

    private TextView plansetting;//显示左上角复习计划设置
    private TextView tvdic; //单词听写
    private TextView dicnumber;//单词听写数量
    private TextView tvmem;//单词测试，也就是一个闯关
    private TextView memnumber;//测试数量
    private LinearLayout dictation;
    private LinearLayout memory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewplan_seeting);
        plansetting=findViewById(R.id.plan_setting);
        tvdic = findViewById(R.id.tvdic);
        tvmem = findViewById(R.id.tvmem);
        dicnumber = findViewById(R.id.dic_number);
        memnumber = findViewById(R.id.mem_number);
        dictation = findViewById(R.id.dictation);
        memory = findViewById(R.id.memory);
        //如果听写单词一栏被点击则跳转到更改数字的界面，以及数字更新
        dictation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //如果单词闯关一栏被点击则跳转到更改数字的界面，以及数字更新
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
