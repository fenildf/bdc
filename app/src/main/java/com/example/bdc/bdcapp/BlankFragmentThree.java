package com.example.bdc.bdcapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.example.bdc.fragment3.GetPictureActivity;
import com.example.bdc.fragment3.ReviewPlanSettingActivity;
import com.example.bdc.fragment3.ReviseInformationActivity;
import com.example.bdc.fragment3.WordlistActivity;

public class BlankFragmentThree extends Fragment {
    private TextView userNameShow;
    private ImageView userPicture;
    private Button userRevise;
    private Button setting;
    private Button logout;
    private Button wordList;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank_fragment_three, container, false);
        wordList = view.findViewById(R.id.user_wordlist);
        userRevise = view.findViewById(R.id.user_revise);
        setting = view.findViewById(R.id.user_setting);
        logout = view.findViewById(R.id.user_logout);
        userPicture = view.findViewById(R.id.user_picture);

        userPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlankFragmentThree.this.getActivity(),GetPictureActivity.class);
                startActivity(intent);
            }
        });
        wordList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlankFragmentThree.this.getActivity(), WordlistActivity.class);
                startActivity(intent);
            }
        });
        userRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlankFragmentThree.this.getActivity(), ReviseInformationActivity.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlankFragmentThree.this.getActivity(), ReviewPlanSettingActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(BlankFragmentThree.this.getActivity()).setTitle("系统提示")//设置对话框标题

                        .setMessage("退出登录不会删除任何数据！")//设置显示的内容

                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件

                                // TODO Auto-generated method stub

                                dialog.dismiss();

                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮



                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件

                        // TODO Auto-generated method stub

                        Log.i("alertdialog"," 请保存数据！");

                    }

                }).show();//在按键响应事件中显示此对话框
            }
        });
        return view;
    }

}
