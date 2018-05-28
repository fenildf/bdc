package com.example.a00.bdcapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.fragment1.MyCoursesActivity;
import com.example.a00.fragment3.GetPictureActivity;
import com.example.a00.fragment3.ReviewPlanSettingActivity;
import com.example.a00.fragment3.ReviseInformationActivity;
import com.example.a00.fragment3.WordlistActivity;

public class MineActivity extends AppCompatActivity {

    private TextView userNameShow;
    private ImageView userPicture;
    private Button userRevise;
    private Button userSetting;
    private Button logout;
    private Button wordList;
    private Button userCollect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        wordList = findViewById(R.id.user_wordlist);
        userRevise = findViewById(R.id.user_revise);
        userSetting = findViewById(R.id.user_setting);
        logout = findViewById(R.id.user_logout);
        userPicture = findViewById(R.id.user_picture);
        userCollect = findViewById(R.id.user_collect);

/*

        //点击头像我会设置一个选项，查看头像，替换头像
        userPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, GetPictureActivity.class);
                startActivity(intent);
            }
        });
*/

        //单词本
        wordList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, WordlistActivity.class);
                startActivity(intent);
            }
        });

        //基本信息查看
        userRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, ReviseInformationActivity.class);
                startActivity(intent);
            }
        });
        //复习计划设置
        userSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, ReviewPlanSettingActivity.class);
                startActivity(intent);
            }
        });

        //我的收藏
        userCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, MyCoursesActivity.class);
                startActivity(intent);
            }
        });

        //点击退出登录弹出对话框是否确定退出
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MineActivity.this).setTitle("系统提示")//设置对话框标题

                        .setMessage("退出登录不会删除任何数据！")//设置显示的内容

                        .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮



                            @Override

                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件


                                dialog.dismiss();

                            }

                        }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮



                    @Override

                    public void onClick(DialogInterface dialog, int which) {//响应事件



                        Log.i("alertdialog"," 请保存数据！");

                    }

                }).show();//在按键响应事件中显示此对话框
            }
        });



    }
}
