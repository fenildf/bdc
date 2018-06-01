package com.example.a00.bdcapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by 0.0 on 2018/6/1.
 */

public class ListViewAdapter extends BaseAdapter {

    private List<Map<String, Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public ListViewAdapter(Context context,List<Map<String, Object>> data){
        this.context=context;
        this.data=data;
        this.layoutInflater=LayoutInflater.from(context);
    }
    /**
     * 组件集合，对应item_downloaded.xml中的控件
     */
    public final class Zujian{
        public ImageView bookImage;
        public TextView bookInfo;
        public ImageButton down;
    }
    @Override
    public int getCount() {
        return data.size();
    }
    /**
     * 获得某一位置的数据
     */
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
    /**
     * 获得唯一标识
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Zujian zujian=null;
        if(convertView==null){
            zujian=new Zujian();
            //获得组件，实例化组件
            convertView=layoutInflater.inflate(R.layout.item_downloaded, null);
            zujian.bookImage=convertView.findViewById(R.id.book_image);
            zujian.bookInfo=convertView.findViewById(R.id.book_info);
            zujian.down=convertView.findViewById(R.id.downloaded);
            convertView.setTag(zujian);
        }else{
            zujian=(Zujian)convertView.getTag();
        }
        //绑定数据
        zujian.bookImage.setBackgroundResource((Integer)data.get(position).get("book_image"));
        zujian.bookInfo.setText((String)data.get(position).get("book_info"));
        zujian.down.setImageResource((Integer) data.get(position).get("downloaded"));
        return convertView;
    }
}
