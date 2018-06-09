package com.example.a00.fragment1;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.a00.bdcapp.BlankFragmentThree;
import com.example.a00.bdcapp.FragmentBook;
import com.example.a00.bdcapp.FragmentDownloaded;
import com.example.a00.bdcapp.R;
import com.example.a00.bdcapp.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCoursesActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ViewPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        //初始化视图
        initViews();

    }
    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        myFragmentPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);

        //设置Tab的图标，假如不需要则把下面的代码删去
        one.setIcon(R.mipmap.ic_launcher);
        two.setIcon(R.mipmap.ic_launcher);

    }
}
