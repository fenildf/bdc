package com.example.a00.fragment1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.enitity.Review;
import com.simulation.bdc.enitity.Word;

import java.util.List;

/**
 * 点击首页的泡泡中的复习
 */
public class ReviewPlanActivity extends AppCompatActivity {

    private TextView wordComplement;//单词补全
    private ImageButton pronounce;//单词发音
    private EditText wordWrite;//写出这个单词
    private TextView wordRemind;//单词提示


    private Word word; //当前页面所显示单词
    private Review review;//当前页面计划

    private List<Review> reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_plan);

        wordComplement = findViewById(R.id.word_complement);
        pronounce = findViewById(R.id.pronouce);
        wordWrite = findViewById(R.id.word_write);
        wordRemind = findViewById(R.id.word_remind);

    }

}
