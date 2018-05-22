package com.example.bdc.fragment3;

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

public class WordlistActivity extends AppCompatActivity {
    private ViewPager mPager;//页卡内容
    private List<View> listViews; // Tab页面列表
    private ImageView cursor;// 动画图片
    private TextView combination,recite,other;// 页卡头标
    private int offset = 0;// 动画图片偏移量
    private int currIndex = 0;// 当前页卡编号
    private int bmpW;// 动画图片宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
    }
    private void InitTextView() {
        combination = (TextView) findViewById(R.id.combination);
        recite = (TextView) findViewById(R.id.recite);
        other = (TextView) findViewById(R.id.other);

        combination.setOnClickListener(new WordsOnClickListener(0));
        recite.setOnClickListener(new WordsOnClickListener(1));
        other.setOnClickListener(new WordsOnClickListener(2));
    }
    private void InitViewPager() {
        mPager = findViewById(R.id.vPager);
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.combination, null));
        listViews.add(mInflater.inflate(R.layout.recite, null));
        listViews.add(mInflater.inflate(R.layout.other, null));
        mPager.setAdapter(new WordPagerAdapter(listViews));
        mPager.setCurrentItem(0);
    }
    public class WordsOnClickListener implements View.OnClickListener {

        private int index = 0;

        public WordsOnClickListener(int i) {
            index = i;
        }
        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    };
    private void InitImageView() {
        cursor = (ImageView) findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.loginbackground)
                .getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);// 设置动画初始位置
    }
}
