package com.example.a00.bdcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_recite_word);
    }
}
