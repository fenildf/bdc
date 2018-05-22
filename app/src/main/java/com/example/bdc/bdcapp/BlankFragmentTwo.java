package com.example.bdc.bdcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.example.bdc.fragment2.CYLEActivity;
import com.example.bdc.fragment2.NCEActivity;


public class BlankFragmentTwo extends Fragment {
    private TextView publisher;
    private TextView grade ;
    private View view;
    private Button bookNCE;//新概念英语
    private Button bookCYLE;//剑桥少儿英语
    private Button bookpreschool;//学前
    private Button bookprimary;//小学
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment_two,container, false);
        publisher = view.findViewById(R.id.book_publisher);
        bookCYLE = view.findViewById(R.id.book_jianqiao);
        bookNCE = view.findViewById(R.id.book_newconcept);
/*        grade = view.findViewById(R.id.book_grade);
        bookpreschool = view.findViewById(R.id.book_preschool);
        bookprimary = view.findViewById(R.id.book_primary);*/
        bookCYLE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlankFragmentTwo.this.getActivity(),CYLEActivity.class);
                startActivity(intent);
            }
        });
        bookNCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlankFragmentTwo.this.getActivity(),NCEActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
