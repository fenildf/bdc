package com.example.bdc.fragment1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

public class SentenceEveryDayActivity extends AppCompatActivity {
    private TextView sentenceenshow;//英文句子显示

    private TextView sentencechshow;//中文句子显示

    private ImageView sentencepicshow;//图片显示

    private TextView sentenceperday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence_every_day);

        sentenceperday = findViewById(R.id.sentenceperday);
        sentencepicshow = findViewById(R.id.sentencepicshow);
        sentenceenshow = findViewById(R.id.sentenceenshow);
        sentencechshow = findViewById(R.id.sentencechshow);
    }

}
