package com.example.a00.bdcapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentDownloaded extends Fragment {
    private View view;
    private ListView lvDownloaded;
    private List<Map<String,Object>> dataList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_downloaded,container, false);
        lvDownloaded = view.findViewById(R.id.lv_downloaded);
        initDataList();//初始化数据
        ListViewAdapter adapter = new ListViewAdapter(getContext(),dataList,R.layout.item_downloaded);
        lvDownloaded.setAdapter(adapter);
        return view;
    }
    /**
     * 初始化适配器需要的数据格式
     */
    private void initDataList() {
        //图片资源
        int img[] = { R.drawable.book_pic };
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("book_image", R.drawable.book_pic);
            map.put("book_info", "新概念课本第一册\n" +
                    "小学三年级\n" +
                    "出版社\n" +
                    "出版时间" + i);
            dataList.add(map);
        }

    }
}
