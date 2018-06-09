package com.example.a00.fragment1;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.example.a00.bdcapp.StartReciteWordActivity;
import com.simulation.bdc.Service.WordService;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.Word;
import com.simulation.bdc.util.Player;

import org.w3c.dom.Text;

import java.util.List;

public class WordInfoActivity extends AppCompatActivity {

    private TextView wordTextView, phUKTextView, phUSATextView,meaningshowTextView, sentenceTextView;
    private ImageButton proUKImageButton, proUSAImageButton;
    private String wordName;
    private Word word;
    private WordService wordService = new WordService();
    private String ip = "http://123.206.29.55/";

    private static final String TAG = "WordInfoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_info);

        wordTextView = (TextView) findViewById(R.id.word);
        phUKTextView = (TextView) findViewById(R.id.ph_uk);
        phUSATextView = (TextView) findViewById(R.id.ph_usa);
        meaningshowTextView = (TextView) findViewById(R.id.meaningshow);
        sentenceTextView = (TextView) findViewById(R.id.sentence_text_view);
        proUKImageButton = (ImageButton) findViewById(R.id.pro_uk_icon);
        proUSAImageButton = (ImageButton) findViewById(R.id.pro_usa_icon);

        phUKTextView.setOnClickListener(playerListener);
        phUSATextView.setOnClickListener(playerListener);
        proUKImageButton.setOnClickListener(playerListener);
        proUSAImageButton.setOnClickListener(playerListener);

        Intent intent = getIntent();
        wordName = intent.getStringExtra("wordName");
        if(wordName == null){
            finish();
        }
        getWord();

    }
    //获取单词信息
    public void getWord(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.obj = wordService.queryWordByWordName(wordName);
                msg.what = SHOW_WORD_INFO;
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 在页面上显示单词信息
     */
    public void showWordInfo(){
        wordTextView.setText(wordName);
        if(word != null){
            phUKTextView.setText("英式/" + word.getPhUk() + "/");
            phUSATextView.setText("美式/" + word.getPhUsa() + "/");
            List<Mean> means = word.getMeans();
            meaningshowTextView.setText("");
            for(Mean mean : means){
                meaningshowTextView.append(mean.getPart() + " " + mean.getMean() + "\n");
            }

            List<Sentence> sentences = word.getSentence();
            sentenceTextView.setText("");
            for(Sentence sentence : sentences){
                sentenceTextView.append(sentence.getText() + "\n" + sentence.getTranslation() + "\n");
            }

        }
    }
    /**
     * 播放发音的Listener
     */
    private View.OnClickListener playerListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if(word != null) {
                switch (v.getId()) {
                    case R.id.pro_uk_icon:
                    case R.id.ph_uk: {
                        Player.play(ip + word.getProUk());
                        break;
                    }
                    case R.id.pro_usa_icon:
                    case R.id.ph_usa: {
                        Player.play(ip + word.getProUsa());
                        break;
                    }
                }
            }
        }
    };
    private final int SHOW_WORD_INFO = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SHOW_WORD_INFO:{
                   word = (Word) msg.obj;
                    showWordInfo();
                    break;
                }
            }
        }
    };

    /**
     * 启动 WordInfoActivity 活动
     * @param context
     */
    public static void actionStart(Context context, String wordName){
        Intent intent = new Intent(context,WordInfoActivity.class);
        intent.putExtra("wordName",wordName);
        context.startActivity(intent);
    }
}
