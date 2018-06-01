package com.example.a00.fragment1;

import android.graphics.Color;
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

public class MyCoursesActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private TextView book,downloaded;
    private MenuItem menuItem;
    private List<Fragment> list;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);


        InitView();

    // 设置菜单栏的点击事件
        book.setOnClickListener(this);
        downloaded.setOnClickListener(this);
        viewPager.setOnPageChangeListener(new MyPagerChangeListener());

    //把Fragment添加到List集合里面
        list = new ArrayList<>();
        list.add(new FragmentDownloaded());
        list.add(new FragmentBook());
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);  //初始化显示第一个页面
        book.setBackgroundColor(Color.RED);//被选中就为红色

        book = findViewById(R.id.book);
        downloaded = findViewById(R.id.downloaded);
        viewPager = findViewById(R.id.viewpager);

    }
    /**
     * 初始化控件
     */
    private void InitView() {
       book = findViewById(R.id.book);
       downloaded= findViewById(R.id.downloaded);
       viewPager =  findViewById(R.id.viewpager);
    }

    /**
     * 点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.book:
                viewPager.setCurrentItem(0);
                book.setBackgroundColor(Color.RED);
                downloaded.setBackgroundColor(Color.WHITE);
                break;
            case R.id.downloaded:
             viewPager.setCurrentItem(1);
                book.setBackgroundColor(Color.WHITE);
                downloaded.setBackgroundColor(Color.RED);
                break;
        }
    }

    /**
     * 设置一个ViewPager的侦听事件，当左右滑动ViewPager时菜单栏被选中状态跟着改变
     */
    public class MyPagerChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    viewPager.setCurrentItem(0);
                    book.setBackgroundColor(Color.RED);
                    downloaded.setBackgroundColor(Color.WHITE);
                    break;
                case 1:
                    viewPager.setCurrentItem(1);
                    book.setBackgroundColor(Color.WHITE);
                    downloaded.setBackgroundColor(Color.RED);
                    break;
            }
        }
    }
}
