package com.example.a00.bdcapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.a00.fragment1.ReviewPlanActivity;
import com.example.a00.fragment3.GetPictureActivity;
import com.example.a00.fragment3.ReviewPlanSettingActivity;
import com.example.a00.fragment3.ReviseInformationActivity;
import com.example.a00.fragment3.WordlistActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentClassify extends Fragment {
    ListView lvClassify;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_classify, container, false);
        lvClassify = view.findViewById(R.id.lv_classify);
        ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String, Object>>();

        for (int i=0;i<10;i++)
        {
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("word_classify","组合" +i);
            listItem.add(map);
        }
//        SimpleAdapter msimpleAdapter = new SimpleAdapter(getActivity(),listItem,R.layout.item_classify,new String[]{"word_classify"},new int[]{R.id.word_classify});

//        lvClassify.setAdapter(msimpleAdapter);
        return view;

    }

}
