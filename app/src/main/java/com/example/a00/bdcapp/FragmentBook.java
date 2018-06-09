package com.example.a00.bdcapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.a00.fragment1.SearchWordsActivity;
import com.example.a00.fragment1.SentenceEveryDayActivity;
import com.example.a00.fragment1.StoryReadingActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class FragmentBook extends Fragment {
    private View view;
    private ListView lvBook;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book,container, false);
        lvBook = view.findViewById(R.id.lv_book);
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String, Object>>();

        for (int i=0;i<10;i++)
        {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("book_image",R.drawable.book_pic);
            map.put("book_info","新概念课本第一册\n" +
                    "小学三年级\n" +
                    "出版社\n" +
                    "出版时间");
            map.put("downloaded",R.drawable.down);
            listItem.add(map);
        }
        SimpleAdapter msimpleAdapter = new SimpleAdapter(getActivity(),listItem,R.layout.item_course,new String[]{"book_image","book_info","downloaded"},new int[]{R.id.book_image,R.id.book_info,R.id.downloaded});

        lvBook.setAdapter(msimpleAdapter);
        return view;
    }


}
