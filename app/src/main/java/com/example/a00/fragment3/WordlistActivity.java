package com.example.a00.fragment3;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.example.a00.bdcapp.WordViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WordlistActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private WordViewPagerAdapter FragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wordlist);
        InitViews();
    }
    private void InitViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= findViewById(R.id.word_viewpager);
        FragmentPagerAdapter = new WordViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(FragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = findViewById(R.id.word_tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);

        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);
        three.setIcon(R.mipmap.ic_launcher);
    }
}
