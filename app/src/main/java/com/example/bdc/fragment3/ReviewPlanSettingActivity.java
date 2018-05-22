package com.example.bdc.fragment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

public class ReviewPlanSettingActivity extends AppCompatActivity {

    private TextView plansetting;
    private TextView tvdic;
    private TextView dicnumber;
    private TextView tvmem;
    private TextView memnumber;
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
