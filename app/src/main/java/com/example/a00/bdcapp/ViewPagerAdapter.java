package com.example.a00.bdcapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 *点击我的课程，页面切换ViewPager，fragment的适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    //标题栏
    private String[] mTitles = new String[]{"教材","我的计划"};

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position){
        if (position==1){
            return new FragmentMyPlan();
        }
        return new FragmentBook();
    }
    @Override
    public int getCount(){
        return mTitles.length;
    }
    //ViewPager
    @Override
    public CharSequence getPageTitle(int position){
        return mTitles[position];
    }
}
