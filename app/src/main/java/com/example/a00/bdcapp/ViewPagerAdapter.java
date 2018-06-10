package com.example.a00.bdcapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 0.0 on 2018/5/12.
 * 这个为主界面，点击底部导航栏时fragment切换的适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles = new String[]{"教材","已下载"};

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
