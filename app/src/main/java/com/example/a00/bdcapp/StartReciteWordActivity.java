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
import com.simulation.bdc.adapter.PlanAdapter;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.Unit;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.enitity.Word;
import com.simulation.bdc.util.Player;
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

    private int unitIndex;//当前单词所在的单元列表的位置
    private int wordIndex; //当前背诵到的单词在列表中的位置
    private Book book;//计划中的教材信息
    private Word word;//当前页面显示的单词

    private String ip = "http://123.206.29.55/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = userService.queryLoginUser();

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
        initActivity(); //初始化背单词页面
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
            //播放单词发音
            if(word != null) {
                switch (viewId) {
                    case R.id.pro_uk_icon:
                    case R.id.ph_uk: {
                        Player.play(ip + word.getProUk()); //播放英式发音
                        break;
                    }
                    case R.id.pro_usa_icon:
                    case R.id.ph_usa: {
                        Player.play(ip + word.getProUsa()); //播放美式发音
                        break;
                    }
                }
            }

        }
    };
    //显示下一个单词
    public void showNextWord(){
        wordIndex ++;
        if(wordIndex < wordList.size()){
            userPlan.setHasDone(userPlan.getHasDone()+1); //计划已完成的单词加一
            userPlan.setWordId(word.getWordId());
            word = wordList.get(wordIndex);
        }else {
            wordIndex = 0;
            unitIndex++;
            if (unitIndex < userPlan.getBook().getUnits().size()){
                userPlan.setUnitId(userPlan.getBook().getUnits().get(unitIndex).getUnitId());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        wordList = wordService.queryWordByUnitId(userPlan.getBook().getUnits().get(unitIndex).getUnitId(), user.getUserId());
                        msg.what = SHOW_NEXT_WORD;
                        handler.sendMessage(msg);
                    }
                }).start();
            }else {
                toast("计划已完成");
                Intent intent = new Intent(StartReciteWordActivity.this,HomeActivity.class);
                startActivity(intent);
            }

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
//    /**
//     * 获取将要背诵的单词
//     * @return
//     */
//    public void getWords(final int unitId){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Word> wordList = new ArrayList<Word>();
//                for(Unit unit: userPlan.getBook().getUnits()){
//                    if(unit.getUnitId() == userPlan.getUnitId()) {
//
//                    }
//                }
//                wordList.addAll(wordService.queryWordByUnitId(unitId,user.getUserId()));
//                Message msg = new Message();
//                msg.obj = wordList;
//                handler.sendMessage(msg);
//            }
//        }).start();
//    }

    /**
     * 初始化当前页面
     */
    public  void initActivity(){
        user = userService.queryLoginUser();
        if(user == null){
            toast("出错了！");
            this.onDestroy();
        }
        userPlan = userService.queryUserPlanFromLocal(user.getUserId());
        if(userPlan != null){
            for(int i = 0;i < userPlan.getBook().getUnits().size();i++){
                Unit unit = userPlan.getBook().getUnits().get(i);
                if(unit.getUnitId() == userPlan.getUnitId()){
                    unitIndex = i;
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    wordList = wordService.queryWordByUnitId(userPlan.getUnitId(),user.getUserId());
                    msg.what = INIT_ACTIVITY;
                    handler.sendMessage(msg);
                }
            }).start();
        }else{
            toast("没有计划！");
            this.onDestroy();
        }
    }

    /**
     * 将单词信息填充到页面上
     * 如果单词为空 则销毁活动
     */
    public void showWord(){
        if(word == null){
            toast("没有单词信息");
            onDestroy();
        }else {
            wordTextView.setText(word.getWordName()); //显示单词拼写
            phUKTextView.setText(" 英式/" + word.getPhUk() + "/"); //显示单词英式音标
            phUSATextView.setText(" 美式/" + word.getPhUsa() + "/"); //显示单词美式音标

            //显示单词释义
            meaningShowTextView.setText("");
            if (word.getMeans() != null) {
                for (int i = 0; i < word.getMeans().size(); i++) {
                    Mean mean = word.getMeans().get(i);
                    meaningShowTextView.append(mean.getPart() + " " + mean.getMean() + "\n");
                }
            }
            //显示单词例句
            egShowTextView.setText("");
            if (word.getSentence() != null) {
                for (Sentence sentence : word.getSentence()) {
                    egShowTextView.append(sentence.getText() + "\n" + sentence.getTranslation() + "\n");
                }
            }

            //显示以前学过的同类型单词
            sameClassificationShow.setText("");
            if (word.getSameTypeWord() != null) {
                for (int i = 0; i < word.getSameTypeWord().size(); i++) {
                    sameClassificationShow.append(word.getSameTypeWord().get(i) + "   ");
                }
            }

            //显示以前学过的形似的单词
            sameSameLookShow.setText("");
            if (word.getAlikeWord() != null) {
                for (int i = 0; i < word.getAlikeWord().size(); i++) {
                    sameSameLookShow.append(word.getAlikeWord().get(i) + "  ");
                }
            }
        }
    }
    private final int INIT_ACTIVITY = 1;//初始化活动
    private final int SHOW_NEXT_WORD = 2;//显示下一个单词

    /**
     * 处理器
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case INIT_ACTIVITY:{
                   if(wordList == null || wordList.isEmpty()){
                       showNextWord();
                   }else{
                       for(int i = 0;i <= wordList.size();i++){
                           if(userPlan.getWordId() == wordList.get(i).getWordId()){
                               wordIndex = i;
                               word = wordList.get(wordIndex);
                               word = wordList.get(wordIndex);
                               break;
                           }
                       }
                       showWord();
                   }
                   break;
                }
                case SHOW_NEXT_WORD:{
                    if(wordList == null || wordList.isEmpty()){
                        showNextWord();
                    }else{
                        word = wordList.get(wordIndex);
                        showWord();
                    }
                    break;
                }
            }
        }
    };

    /**
     * 在当前页面进行广播
     * @param text
     */
    public void toast(String text){
        Toast.makeText(StartReciteWordActivity.this,text,Toast.LENGTH_SHORT).show();
}
}
