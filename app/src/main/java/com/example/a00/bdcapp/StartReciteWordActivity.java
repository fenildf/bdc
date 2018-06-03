package com.example.a00.bdcapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.Service.WordService;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.Unit;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.enitity.Word;
import com.simulation.bdc.util.Session;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 本界面用于单词的轮播，单词背诵包括单词，发音，释义，例句，同类单词，同形单词
 * 有三个悬浮的Button、一个ScrollView（用于显示不能完全显示的界面）
 */
public class StartReciteWordActivity extends AppCompatActivity {
    private static final String TAG = "StartReciteWordActivity";
    private TextView wordTextView;//单词
    private ImageButton proUKImageButton, proUSAImageButton;//播放器
    private TextView phUKTextView, phUSATextView;//音标
    private TextView meaningTextView,eg,sameClassification,sameLookTextView;
    private TextView meaningShowTextView,egShowTextView,sameClassificationShow,sameSameLookShow;
    private Button knowButton,notKnowButton;

    private WordService wordService = new WordService(); //单词的服务类
    private List<Word> wordList ;//单词列表

    private UserService userService = new UserService();

    private User user;//用户信息
    private UserPlan userPlan;//用户计划

    private int index = 0; //当前背诵到的单词在列表中的位置
    private Book book;//计划中的教材信息
    private Word word;//当前页面显示的单词

    private String ip = "http://123.206.29.55/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = userService.queryLoginUser();

        List<UserPlan> userPlans = user.getPlans();
        if(!userPlans.isEmpty()){
            userPlan = userPlans.get(0);
            book = userPlan.getBook();
        }
        //获取单词列表
        getWords();

        setContentView(R.layout.activity_start_recite_word);

        wordTextView = findViewById(R.id.word);
        proUKImageButton = findViewById(R.id.pro_uk_icon);
        proUSAImageButton = findViewById(R.id.pro_usa_icon);
        phUKTextView = findViewById(R.id.ph_uk);
        phUSATextView = findViewById(R.id.ph_usa);
        meaningTextView = findViewById(R.id.meaning);
        eg = findViewById(R.id.eg);
        sameClassification = findViewById(R.id.same_classification);
        sameLookTextView = findViewById(R.id.same_look);
        meaningShowTextView = findViewById(R.id.meaning_show);
        egShowTextView = findViewById(R.id.eg_show);
        sameClassificationShow = findViewById(R.id.same_classification_show);
        sameSameLookShow = findViewById(R.id.same_look_show);

        knowButton = findViewById(R.id.know);
        notKnowButton = findViewById(R.id.not_know);



        notKnowButton.setOnClickListener(nextWordLister);
        knowButton.setOnClickListener(nextWordLister);

        proUKImageButton.setOnClickListener(playProListener);
        proUSAImageButton.setOnClickListener(playProListener);
        phUKTextView.setOnClickListener(playProListener);
        phUSATextView.setOnClickListener(playProListener);
    }
    //底部按钮的监听器
    private View.OnClickListener nextWordLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.know:{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            boolean addReview = wordService.addReviewWord(user.getUserId(), word.getWordId());
                            if(!wordService.addUserCompleteWord(user.getUserId(),word.getWordId()) && addReview){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(StartReciteWordActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                    break;
                }
                case R.id.not_know:{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            if(!wordService.addNewWord(user.getUserId(),word.getWordId())){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(StartReciteWordActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                    break;
                }
            }
            showNextWord();
        }
    };

    //播放音频的Listener
    private View.OnClickListener playProListener = new View.OnClickListener(){
        private MediaPlayer mediaPlayer = new MediaPlayer();
        @Override
        public void onClick(View v) {
            final int viewId = v.getId();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Log.d(TAG, "run: " + ip + word.getProUk());
                        Log.d(TAG, "run: " + ip + word.getProUsa());
                        mediaPlayer.reset();
                        switch (viewId){
                            case R.id.pro_uk_icon:
                            case R.id.ph_uk:{
                                mediaPlayer.setDataSource(ip + word.getProUk()); // 设置数据源
                                break;
                            }
                            case R.id.pro_usa_icon:
                            case R.id.ph_usa:{
                                mediaPlayer.setDataSource(StartReciteWordActivity.this,Uri.parse(ip + word.getProUsa())); // 设置数据源
                                break;
                            }
                        }
                        mediaPlayer.prepareAsync(); // prepare自动播放 ;
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                // 装载完毕回调
                                mediaPlayer.start();
                             }
                        });
                    }catch (Exception e){
                        Log.d(TAG, "onClick: " + e);
                    }
                }
            }).start();

        }
    };
    //显示下一个单词
    public void showNextWord(){
        index++;
        if(index < wordList.size()){
            userPlan.setHasDone(userPlan.getHasDone()+1); //计划已完成的单词加一
            userPlan.setWordId(word.getWordId());
            word = wordList.get(index);
        }else{
            index=0;
            wordList.clear();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    for(Unit unit : book.getUnits()){
                        if(unit.getUnitId() > userPlan.getUnitId()){
                            userPlan.setUnitId(unit.getUnitId());
                            msg.obj = wordService.queryWordByUnitId(unit.getUnitId(),user.getUserId());
                            break;
                        }
                    }
                    handler.sendMessage(msg);
                }
            }).start();

        }
        //更新 用户计划的子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(!userService.updateUserPlan(userPlan)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(StartReciteWordActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).start();
        showWord(); //将下一个单词填充到页面内上
    }
    /**
     * 获取将要背诵的单词
     * @return
     */
    public void getWords(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Word> wordList = new ArrayList<Word>();
                for(Unit unit: book.getUnits()){
                    if(unit.getUnitId() == userPlan.getUnitId()) {
                        wordList.addAll(wordService.queryWordByUnitId(unit.getUnitId(),user.getUserId()));
                    }
                }
                Log.d(TAG, "run: " + DataSupport.where("bookId=?" ,userPlan.getBookId()+"").find(Book.class));
                Log.d(TAG, "run: " + userPlan.getBookId());
                Message msg = new Message();
                msg.obj = wordList;
                handler.sendMessage(msg);
            }
        }).start();
    }
    //将单词信息填充到页面上
    public void showWord(){

        wordTextView.setText(word.getWordName()); //显示单词拼写

        phUKTextView.setText(" 英式/" + word.getPhUk() + "/"); //显示单词英式音标

        phUSATextView.setText(" 美式/" + word.getPhUsa() + "/"); //显示单词美式音标

        //显示单词释义
        meaningShowTextView.setText("");
        if(word.getMeans() != null){
            for(int i = 0;i < word.getMeans().size();i++){
                Mean mean = word.getMeans().get(i);
                meaningShowTextView.append(mean.getPart() + " " + mean.getMean() + "\n");
            }
        }
       //显示单词例句
        egShowTextView.setText("");
        if(word.getSentence() != null){
            for(Sentence sentence : word.getSentence()){
                egShowTextView.append(sentence.getText() + "\n" + sentence.getTranslation() + "\n");
            }
        }

        //显示以前学过的同类型单词
        sameClassificationShow.setText("");
        if(word.getSameTypeWord() != null) {
            for (int i = 0; i < word.getSameTypeWord().size(); i++) {
                sameClassificationShow.append(word.getSameTypeWord().get(i) + "   ");
            }
        }

        //显示以前学过的形似的单词
        sameSameLookShow.setText("");
        if(word.getAlikeWord() != null) {
            for (int i = 0; i < word.getAlikeWord().size(); i++) {
                sameSameLookShow.append(word.getAlikeWord().get(i) + "  ");
            }
        }
    }

    //更新页面
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj != null){
                wordList = (List<Word>) msg.obj;
                //确定当前 所背诵单词在单词列表中的位置
                for (int i = 0; i < wordList.size(); i++) {
                    if (userPlan.getWordId() == wordList.get(i).getWordId()) {
                        index = i;
                        break;
                    }
                }
                if(index < wordList.size()) {
                    //获取当前页面单词信息
                    word = wordList.get(index);
                    showWord();
                }
                //将单词信息 填充到页面
            }else{
                Toast.makeText(StartReciteWordActivity.this,"完成计划",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(StartReciteWordActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        }
    };
}
