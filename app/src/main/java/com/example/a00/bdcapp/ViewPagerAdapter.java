package com.example.a00.bdcapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 0.0 on 2018/5/12.
 * 这个为主界面，点击底部导航栏时fragment切换的适配器
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mlist = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    public void addFragment(Fragment fragment) {
        mlist.add(fragment);
    }
}
