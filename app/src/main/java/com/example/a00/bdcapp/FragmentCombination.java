package com.example.a00.bdcapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class FragmentCombination extends Fragment {
    ListView lvCombination;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_combination, container, false);
        lvCombination = view.findViewById(R.id.lv_combination);
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String, Object>>();

        for (int i=0;i<10;i++)
        {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("word_combination","组合" +i);
            listItem.add(map);
        }
//        SimpleAdapter msimpleAdapter = new SimpleAdapter(getActivity(),listItem,R.layout.item_combination,new String[]{"word_homonym"},new int[]{R.id.lv_combination});
//
//        lvCombination.setAdapter(msimpleAdapter);
        return view;

    }
}
