package com.example.a00.bdcapp;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.simulation.bdc.Service.WordService;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.Unit;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.UserPlan;
import com.simulation.bdc.enitity.Word;
import com.simulation.bdc.util.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * 本界面用于单词的轮播，单词背诵包括单词，发音，释义，例句，同类单词，同形单词
 * 有三个悬浮的Button、一个ScrollView（用于显示不能完全显示的界面）
 */
public class StartReciteWordActivity extends AppCompatActivity {

    private TextView wordTextView;//单词
    private ImageButton proUKImageButton, proUSAImageButton;//播放器
    private TextView phUKTextView, phUSATextView;//音标
    private TextView meaningTextView,eg,sameClassification,sameLookTextView;
    private TextView meaningShowTextView,egShowTextView,sameClassificationShow,sameSameLookShow;
    private Button knowButton,addWordlist,notKnowButton;

    private WordService wordService = new WordService(); //单词的服务类
    private List<Word> wordList ;//单词列表

    private UserPlan userPlan = ((List<UserPlan>) Session.getAttribute("plans")).get(0); //当前用户计划

    private int index = 0; //当前背诵到的单词在列表中的位置



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        addWordlist = findViewById(R.id.add_wordlist);
        //获取单词列表
        getWords();


        notKnowButton.setOnClickListener(nextWordLister);
        addWordlist.setOnClickListener(nextWordLister);
        knowButton.setOnClickListener(nextWordLister);

    }

    private View.OnClickListener nextWordLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            index++;
            if(index > wordList.size()){
                Toast.makeText(StartReciteWordActivity.this,"已完成计划",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StartReciteWordActivity.this,HomeActivity.class);
                startActivity(intent);
            }
            showWord();
        }
    };


    public void showWord(){
        Word word = wordList.get(index);
        wordTextView.setText(word.getWordName()); //显示单词拼写

        phUKTextView.setText("英式  " + word.getPhUk()); //显示单词英式音标

        phUSATextView.setText("美式  " + word.getPhUsa()); //显示单词美式音标

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


    }

    /**
     * 获取将要背诵的单词
     * @return
     */
    public void getWords(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Book book = userPlan.getBook();
                List<Word> wordList = new ArrayList<Word>();
                for(Unit unit: book.getUnits()){
                    if(unit.getUnitId() >= userPlan.getUnitId()) {
                        wordList.addAll(wordService.queryWordByUnitId(unit.getUnitId()));
                    }
                }
                Message msg = new Message();
                msg.obj = wordList;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.obj != null){
                wordList = (List<Word>) msg.obj;

                //确定当前 所背诵单词在单词列表中的位置
                for(int i = 0;i < wordList.size();i++){
                    if(userPlan.getWordId() == wordList.get(i).getWordId()){
                        index = i;
                    }
                }
//
                //将单词信息 填充到页面
                showWord();
            }
        }
    };

}
