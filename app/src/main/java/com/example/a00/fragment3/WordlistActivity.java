package com.example.a00.fragment3;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个是单词本点开以后，类似HomeActivity
 */
public class WordlistActivity extends AppCompatActivity {
    private ViewPager mPager;//页卡内容
    private List<View> listViews; // Tab页面列表
    private TextView combination,recite,other;// 页卡头标
    private int currIndex = 0;// 当前页卡编号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
    }

}
