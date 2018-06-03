package com.example.a00.bdcapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.fragment1.MyCoursesActivity;
import com.example.a00.fragment1.ReviewPlanActivity;
import com.example.a00.fragment1.StoryReadingActivity;
import com.example.a00.fragment1.WordInfoActivity;
import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.util.Session;

import org.litepal.crud.DataSupport;

import java.util.List;


/**
 * 首页，通过首页的泡泡，点击添加事件，分别跳转到各个对应功能的子页面
 */
public class HomeActivity extends AppCompatActivity {
    private EditText print;
    private Button search;
    private Button myCourse;
    private Button review;
    private Button mine;
    private Button story;
    private TextView todayTask;
    private TextView wordNumber;
    private Button startReciteWord;


    private UserService userService = new UserService();

    private User user = userService.queryLoginUser();

    private UserPlan userPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        print = findViewById(R.id.print);
        search = findViewById(R.id.search);
        myCourse = findViewById(R.id.mycourse);
        review = findViewById(R.id.review);
        mine = findViewById(R.id.mine);
        story = findViewById(R.id.story);
        todayTask = findViewById(R.id.today_task);
        wordNumber = findViewById(R.id.word_number);
        startReciteWord = findViewById(R.id.start_recite_word);

        List<UserPlan> userPlans = userService.queryUserPlan(user.getUserId());

        if(userPlans != null && !userPlans.isEmpty()){
            userPlan = userPlans.get(0);
            wordNumber.setText(userPlan.getWordNumber() + "");
        }
        //点击查询，跳转到单词释义显示界面
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, WordInfoActivity.class);
                startActivity(intent);
            }
        });
        //点击我的课程，跳转到我的课程页面，显示已收藏教材和已下载的课程
        myCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MyCoursesActivity.class);
                startActivity(intent);
            }
        });
        //点击复习，跳转到复习界面，显示要复习的内容
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ReviewPlanActivity.class);
                startActivity(intent);
            }
        });
        //点击我的，跳到用户管理界面，显示用户头像，生词本等内容
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MineActivity.class);
                startActivity(intent);
            }
        });
        //点击故事，跳转到故事阅读，显示小故事
        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, StoryReadingActivity.class);
                startActivity(intent);
            }
        });
        //点击开始背单词，跳转到背诵单词的界面
        startReciteWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,userService.queryUserPlan(user.getUserId()).toString(),Toast.LENGTH_LONG).show();
                if(userService.queryUserPlan(user.getUserId()) != null) {
                    Intent intent = new Intent(HomeActivity.this, StartReciteWordActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(HomeActivity.this,"请先添加计划" + Session.getAttribute("user"),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
