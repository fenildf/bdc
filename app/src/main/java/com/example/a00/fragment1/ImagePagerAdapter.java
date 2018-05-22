package com.example.a00.fragment1;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by 0.0 on 2018/5/14.
 * 只是关于首页里每日一句的图片轮播设置的适配器
 */

public class ImagePagerAdapter extends PagerAdapter {

    private List<ImageView> images;
    private ViewPager viewPager;

    /**
     * 构造方法，传入图片列表和ViewPager实例
     * @param images
     * @param viewPager
     */

    public ImagePagerAdapter(List<ImageView> images, ViewPager viewPager){
        this.images  = images;
        this.viewPager = viewPager;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;//返回一个无限大的值，可以 无限循环
    }

    /**
     * 判断是否使用缓存, 如果返回的是true, 使用缓存. 不去调用instantiateItem方法创建一个新的对象
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化一个条目
     * @param container
     * @param position 当前需要加载条目的索引
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        ImageView iv = images.get(position % images.size());
        viewPager.addView(iv);
        // 把当前添加ImageView返回回去.
        return iv;
    }
    /**
     * 销毁一个条目
     * position 就是当前需要被销毁的条目的索引
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 把ImageView从ViewPager中移除掉
        viewPager.removeView(images.get(position % images.size()));

    }
}
