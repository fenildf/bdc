package com.example.a00.fragment1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.Word;

import org.w3c.dom.Text;

import java.util.List;

public class WordInfoActivity extends AppCompatActivity {

    private TextView wordTextView, phUKTextView, phUSATextView,meaningshowTextView, sentenceTextView;
    private ImageButton proUKImageButton, proUSAImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_info);

        wordTextView = (TextView) findViewById(R.id.word_name);
        phUKTextView = (TextView) findViewById(R.id.ph_uk);
        phUSATextView = (TextView) findViewById(R.id.ph_usa);
        meaningshowTextView = (TextView) findViewById(R.id.meaningshow);
        sentenceTextView = (TextView) findViewById(R.id.sentence_text_view);
        proUKImageButton = (ImageButton) findViewById(R.id.pro_uk_icon);
        proUSAImageButton = (ImageButton) findViewById(R.id.pro_usa_icon);

        Intent intent = getIntent();
        Word word = intent.getParcelableExtra("word");
        String worName = intent.getStringExtra("wordName");


        if(word != null){
            wordTextView.setText(word.getWordName());
            phUKTextView.setText("/" + word.getPhUk() + "/");
            phUSATextView.setText("/" + word.getPhUsa() + "/");

            List<Mean> means = word.getMeans();
            if(means != null && !means.isEmpty()){
                for(Mean mean : means){
                    meaningshowTextView.append(mean.getPart() + " " + mean.getMean() + "\n");
                }
            }

            List<Sentence> sentences = word.getSentence();
            if(sentences != null && !sentences.isEmpty()){
                for(Sentence sentence : sentences){
                    sentenceTextView.append(sentence.getText() + "\n" + sentence.getTranslation() + "\n");
                }
            }

        }
    }


    /**
     * 启动 WordInfoActivity 活动
     * @param context
     * @param word
     */
    public static void actionStart(Context context, Word word,String wordName){
        Intent intent = new Intent(context, WordInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("word",word);
        intent.putExtras(bundle);
        intent.putExtra("wordName", wordName);
        context.startActivity(intent);
    }
}
