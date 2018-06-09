package com.example.a00.bdcapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 0.0 on 2018/6/9.
 */

public class WordViewPagerAdapter extends FragmentPagerAdapter {

    private String[] Titles = new String []{"字母组合","单词分类","同形异意"};
    public WordViewPagerAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public Fragment getItem(int position){
        if (position == 1) {
            return new FragmentClassify();
        } else if (position == 2) {
            return new FragmentHomonym();
        }
        return new FragmentCombination();

    }
    @Override
    public int getCount(){
        return Titles.length;
    }
    //ViewPager
    @Override
    public CharSequence getPageTitle(int position){
        return Titles[position];
    }
}
