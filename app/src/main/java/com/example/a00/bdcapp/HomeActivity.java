package com.example.a00.bdcapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.fragment1.MyCoursesActivity;
import com.example.a00.fragment1.ReviewPlanActivity;
import com.example.a00.fragment1.SearchWordsActivity;
import com.example.a00.fragment1.StoryReadingActivity;
import com.example.a00.fragment1.WordInfoActivity;
import com.simulation.bdc.Service.BookService;
import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.Service.WordService;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Review;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.util.Session;

import org.litepal.crud.DataSupport;

import java.util.List;


/**
 * 首页，通过首页的泡泡，点击添加事件，分别跳转到各个对应功能的子页面
 */
public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private EditText searchTextEdit;
    private Button searchButton;
    private Button myCourse;
    private Button review;
    private Button mine;
    private Button story;
    private TextView wordNumber;
    private Button startReciteWord;


    private UserService userService = new UserService();

    private User user = userService.queryLoginUser();
    private WordService wordService = new WordService();
    private BookService bookService = new BookService();

    private UserPlan userPlan;
    List<UserPlan> userPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchTextEdit = findViewById(R.id.search_text_edit);
        searchButton = findViewById(R.id.search);
        myCourse = findViewById(R.id.mycourse);
        review = findViewById(R.id.review);
        mine = findViewById(R.id.mine);
        story = findViewById(R.id.story);
        wordNumber = findViewById(R.id.word_number);
        startReciteWord = findViewById(R.id.start_recite_word);

        userPlans = userService.queryUserPlanFromLocal(user.getUserId());

        if(userPlans != null && !userPlans.isEmpty()){
            userPlan = userPlans.get(0);
            if(userPlan.getBook().getWordNumber() == userPlan.getHasDone()){
                wordNumber.setText("计划已完成");
                startReciteWord.setText("更改计划");
            }else {
                wordNumber.setText("今日背诵:" + userPlan.getWordNumber());
            }
        }
        Log.d(TAG, "onCreate: " + userPlans);
        Log.d(TAG, "onCreate: " + userPlan.getBook());


        //点击查询，跳转到单词释义显示界面
        searchTextEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, SearchWordsActivity.class);
                startActivity(intent);
            }
        });
        //点击我的课程，跳转到我的课程页面，显示已收藏教材和已下载的课程
        myCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Book> books = bookService.queryAllBooks();
                        Message msg = new Message();
                        if(books != null && !books.isEmpty()){
                            msg.what = MY_COURSES;
                        }else{
                            msg.what = MY_COURSES_IS_NULL;
                        }
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        //点击复习，跳转到复习界面，显示要复习的内容
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Review> reviews = wordService.queryReview(user.getUserId());
                        Message msg = new Message();
                        if( reviews != null && !reviews.isEmpty()){
                            msg.what = REVIEW;
                        }else{
                            msg.what = FAIL;
                        }
                        handler.sendMessage(msg);
                    }
                }).start();
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
                if(!startReciteWord.getText().toString().equals("更改计划")){
                    if(userPlan != null  ) {
                        if(userPlan.getBook().getWordNumber() != userPlan.getHasDone()) {
                            Intent intent = new Intent(HomeActivity.this, StartReciteWordActivity.class);
                            startActivity(intent);
                        }else{
                            toast("当前计划已完成，请选择其他计划");
                        }
                    }else{
                        toast("请先添加计划");
                        Intent intent = new Intent(HomeActivity.this,MyCoursesActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    final private int FAIL = 0;
    final private int REVIEW = 1;
    final private int MY_COURSES = 2;
    final private int MY_COURSES_IS_NULL = 3;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case REVIEW:{
                    Intent intent = new Intent(HomeActivity.this,ReviewPlanActivity.class);
                    startActivity(intent);
                    break;
                }
                case MY_COURSES:{
                    Intent intent = new Intent(HomeActivity.this, MyCoursesActivity.class);
                    startActivity(intent);
                    break;
                }
                case MY_COURSES_IS_NULL:{
                    toast("没有教材信息");
                }
            }
        }
    };

    /**
     * 在当前页面上广播
     * @param text
     */
    public void toast(String text){
        Toast.makeText(HomeActivity.this,text,Toast.LENGTH_SHORT).show();
    }
}
