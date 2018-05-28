package com.example.a00.bdcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 本界面用于单词的轮播，单词背诵包括单词，发音，释义，例句，同类单词，同形单词
 * 有三个悬浮的Button、一个ScrollView（用于显示不能完全显示的界面）
 */
public class StartReciteWordActivity extends AppCompatActivity {

    private TextView word;//单词
    private ImageButton proUKImageButton, proUSAImageButton;//播放器
    private TextView phUKTextView, phUSATextView;//音标
    private TextView meaning,eg,sameClassification,sameLook;
    private TextView meaningShow,egShow,sameClassificationShow,sameSameLookShow;
    private Button know,addWordlist,notKnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_recite_word);

        word = findViewById(R.id.word);
        proUKImageButton = findViewById(R.id.pro_uk_icon);
        proUSAImageButton = findViewById(R.id.pro_usa_icon);
        phUKTextView = findViewById(R.id.ph_uk);
        phUSATextView = findViewById(R.id.ph_usa);
        meaning = findViewById(R.id.meaning);
        eg = findViewById(R.id.eg);
        sameClassification = findViewById(R.id.same_classification);
        sameLook = findViewById(R.id.same_look);
        meaningShow = findViewById(R.id.meaningshow);
        egShow = findViewById(R.id.eg_show);
        sameClassificationShow = findViewById(R.id.same_classification_show);
        sameSameLookShow = findViewById(R.id.same_look_show);
        know = findViewById(R.id.know);
        notKnow = findViewById(R.id.not_know);
        addWordlist = findViewById(R.id.add_wordlist);

    }
}
