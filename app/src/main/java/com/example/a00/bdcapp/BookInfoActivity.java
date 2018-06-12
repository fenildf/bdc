package com.example.a00.bdcapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.simulation.bdc.Service.BookService;
import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.adapter.UnitAdapter;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.util.PictureBitmap;
import com.simulation.bdc.util.Player;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.util.List;

/**
 * 教材详细信息
 */
public class BookInfoActivity extends AppCompatActivity {

    private static final String TAG = "BookInfoActivity";
    private TextView bookNameTextView,bookGradeTextView,bookPublisherTextView,bookWordNumberTextView;
    private ListView unitListView;
    private ImageView bookCoverPictureImageView;
    private Button addToPlan;
    private int bookId;
    private BookService bookService = new BookService();
    private UserService userService = new UserService();
    private Book book;
    private User user;
    private List<UserPlan> plans;

    private String ip = "http://123.206.29.55/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        bookCoverPictureImageView = findViewById(R.id.book_cover_picture);
        bookNameTextView = findViewById(R.id.book_name);
        bookGradeTextView = findViewById(R.id.book_grade);
        bookPublisherTextView = findViewById(R.id.book_publisher);
        bookWordNumberTextView = findViewById(R.id.book_word_number);
        unitListView = findViewById(R.id.unit_list);
        addToPlan = findViewById(R.id.add_to_plan);

        addToPlan.setOnClickListener(addToPlanListener);

        user = userService.queryLoginUser();
        plans = user.getPlans();

        Intent intent = getIntent();
        bookId = intent.getIntExtra("bookId",0);
        book = bookService.queryBookFromLocal(bookId);

        if(book == null){
            finish();
        }else {
            showBookInfo();
            new Thread(new Runnable() {
               @Override
               public void run() {
                  final Bitmap bitmap = PictureBitmap.getPictureBitMap(ip + book.getCoverPicture());
                  Message msg = new Message();
                  msg.what = SHOW_BOOK_COVER_PICTURE;
                  msg.obj = bitmap;
                  handler.sendMessage(msg);
               }
           }).start();
        }
        initAddToPlanButton(bookId);
    }

    /**
     * 初始化添加计划按钮
     * @param bookId
     */
    public void initAddToPlanButton(int bookId){
        if(plans != null && !plans.isEmpty()){
            for(UserPlan plan : plans){
                if(bookId == plan.getBook().getBookId()){
                    addToPlan.setBackgroundColor(getResources().getColor(R.color.addedToPlan));
                    addToPlan.setText(R.string.added_to_plan);
                    addToPlan.setClickable(false);
                    break;
                }
            }
        }
    }

    /**
     * 如果教材没有添加到用户计划中，点击添加按钮后添加
     */
    private View.OnClickListener addToPlanListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String butotnText = addToPlan.getText().toString();
            if(butotnText.equals("添加到计划")){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        if(userService.addUserPlan(user.getUserId(),bookId,30)){
                            msg.what = ADD_PLAN_SUCESS;
                            Log.d(TAG, "run: " + "添加成功");
                        }else{
                            msg.what = ADD_PLAN_FAIL;
                            Log.d(TAG, "run: " + "添加失败");
                        }
                        handler.sendMessage(msg);
                        Log.d(TAG, "onClick: " + msg.what);
                    }
                }).start();
            }
        }
    };

    /**
     * 更新用户计划
     */
    public void updateUserPlan(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                user.setPlans(userService.queryUserPlanFromServer(user.getUserId()));
                plans = user.getPlans();
                Message msg = new Message();
                msg.what = UPDATE_USEER_PLAN;
                handler.sendMessage(msg);
            }
        }).start();
    }
    private final int SHOW_BOOK_COVER_PICTURE = 1;
    private final int ADD_PLAN_SUCESS=2;
    private final int ADD_PLAN_FAIL = 3;
    private final int UPDATE_USEER_PLAN = 4;

    /**
     * 处理器
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SHOW_BOOK_COVER_PICTURE:{
                    bookCoverPictureImageView.setImageBitmap((Bitmap) msg.obj);
                    break;
                }
                case ADD_PLAN_SUCESS:{
                    toast("添加计划成功！");
                    updateUserPlan();
                    break;
                }
                case ADD_PLAN_FAIL:{
                    toast("添加计划失败！");
                    break;
                }
                case UPDATE_USEER_PLAN:{
                    initAddToPlanButton(bookId);
                    break;
                }
            }
        }
    };
    /**
     * 在页面上展示教材信息
     */
    public void showBookInfo(){
        bookNameTextView.setText(book.getBookName());
        bookGradeTextView.append(book.getGrade().getGrandeName());
        bookPublisherTextView.append(book.getPublisher().getPublisherName());
        bookWordNumberTextView.append(book.getWordNumber() + "");
        unitListView.setAdapter(new UnitAdapter(BookInfoActivity.this,book.getUnits()));
        Log.d(TAG, "showBookInfo: " + book.getUnits());
    }
    /**
     * 启动BookInfoActivity
     * @param context
     * @param bookId
     */
    public static void startAction(Context context, int bookId){
        Intent intent = new Intent(context,BookInfoActivity.class);
        intent.putExtra("bookId",bookId);
        context.startActivity(intent);
    }

    /**
     * 在当前页面进行广播
     * @param text
     */
    public void toast(String text){
        Toast.makeText(BookInfoActivity.this,text,Toast.LENGTH_SHORT).show();
    }
}
