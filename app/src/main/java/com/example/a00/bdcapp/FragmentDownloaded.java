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
    private ListView lvDownloaded=null;
    private String[] names={"第一册","第二册","第三册"};
    ArrayList<Map<String,Object>> data= new ArrayList<Map<String,Object>>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_downloaded,container, false);
        lvDownloaded = view.findViewById(R.id.lv_downloaded);
        List<Map<String, Object>> list=getData();
        lvDownloaded.setAdapter(new ListViewAdapter(getActivity(), list));
        return view;
    }
    public List<Map<String, Object>> getData(){
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        for (int i = 0; i < 10; i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("book_image", R.drawable.book_pic);
            map.put("book_info", "书籍详细信息"+i);
            map.put("downloaded",R.drawable.down);
            list.add(map);
        }
        return list;
    }
}
