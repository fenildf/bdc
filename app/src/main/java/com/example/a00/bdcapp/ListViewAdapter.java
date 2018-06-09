package com.example.a00.bdcapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 0.0 on 2018/6/1.
 * 教材库单元列表显示的listview的适配器
 */

public class ListViewAdapter extends BaseAdapter {
    private List<Map<String, Object>> datalist;
    private Context context;
    private int resource;

    public ListViewAdapter(Context context, List<Map<String, Object>> datalist, int resource) {

        this.context = context;
        this.datalist = datalist;
        this.resource = resource;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup arg2) {

        Util util = null;
        if (view == null) {
            util = new Util();
            // 给xml布局文件创建java对象
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(resource, null);
            util.imageView = (ImageView) view.findViewById(R.id.book_image);
            util.textView = (TextView) view.findViewById(R.id.book_info);
            util.progressBar = (ProgressBar) view.findViewById(R.id.progress);
            view.setTag(util);

        } else {
            util = (Util) view.getTag();
        }

        Map<String, Object> map = datalist.get(position);
        util.imageView.setImageResource((Integer) map.get("book_image"));
        util.textView.setText((CharSequence) map.get("book_info"));
        util.progressBar.setProgress(0);

        return view;
    }
}
/**
 * 内部类
 */
class Util{
    ImageView imageView;
    TextView textView;
    ProgressBar progressBar;
}
