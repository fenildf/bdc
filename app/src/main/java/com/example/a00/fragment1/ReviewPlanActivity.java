package com.example.a00.fragment1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.bdcapp.HomeActivity;
import com.example.a00.bdcapp.R;
import com.simulation.bdc.Service.UserService;
import com.simulation.bdc.Service.WordService;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Review;
import com.simulation.bdc.enitity.Sentence;
import com.simulation.bdc.enitity.User;
import com.simulation.bdc.enitity.Word;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

/**
 * 点击首页的泡泡中的复习
 */
public class ReviewPlanActivity extends AppCompatActivity {
    private static final String TAG = "ReviewPlanActivity";
    private TextView wordComplement;//单词补全
    private ImageButton pronounce;//单词发音
    private EditText wordWrite;//写出这个单词
    private TextView wordMean;//单词释义
    private Button sureButton;


    private Word word; //当前页面所显示单词
    private Review review;//当前页面计划
    private List<Review> reviews; //用户复习表
    private WordService wordService = new WordService();//单词服务类
    private UserService userService = new UserService();//用户服务
    private User user;//当前用户
    private int index = 0;//当前单词所在位置



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_plan);

        wordComplement = findViewById(R.id.word_complement);
        pronounce = findViewById(R.id.pronouce);
        wordWrite = findViewById(R.id.word_write);
        wordMean = findViewById(R.id.word_mean);
        sureButton = findViewById(R.id.sure);

        user = userService.queryLoginUser();
        if(user == null) this.onDestroy();//如果用户信息为空 则销毁活动

        reviews = wordService.queryReviewFromLocal(user.getUserId()); //获取用户复习表信息

        showNextWord();
        //发音
        pronounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(word.getProUsa().length() > 0) {
                    player(ip + word.getProUsa());
                }else{
                    player(ip + word.getProUk());
                }
            }
        });

        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wordName = wordWrite.getText().toString();
                if(wordName == null || "".equals(wordName)){
                    Toast.makeText(ReviewPlanActivity.this,"请输入单词",Toast.LENGTH_SHORT).show();
                }else if(checkUserInput(wordName)){
                    updateReview();
                    showNextWord();
                }else{
                    Toast.makeText(ReviewPlanActivity.this,"单词拼写错误", Toast.LENGTH_SHORT).show();
        }
    }
        });
    }

    /**
     * 修改用户复习计划
     */
    public void updateReview(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                review.setReviewTime(review.getReviewTime()+1);
//                Log.d(TAG, "updateReview: " + reviews.toString() );
//                Log.d(TAG, "updateReview: " + (review == reviews.get(index)));
//                Log.d(TAG, "run: " + wordService.updateReview(review));
                if(wordService.updateReview(review)){
                    msg.what = UPDATA_REVIEW_SUCESS;
                }else{
                    msg.what = UPDATA_REVIEW_FAIL;
                }
                handler.sendMessage(msg);
            }
        }).start();
        if(review.getReviewTime() >= 4){
            DataSupport.deleteAll(Review.class,"reviewId=?",review.getReviewId() + "");
            reviews.remove(index);
        }else{
            index++;
            if(index >= reviews.size()){
                index = 0;
            }
        }
    }
    //判断用户输入单词是否正确
    public boolean checkUserInput(String wordName){
        if(word == null){
            return false;
        }else if(word.getWordName().equals(wordName)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 显示下一个单词
     */
    public void  showNextWord(){
        if(reviews.size() != 0){
            review = reviews.get(index);
            getWord(review.getWordId());
        }else{
            Toast.makeText(ReviewPlanActivity.this,"单词复习完毕",Toast.LENGTH_SHORT).show();
            onDestroy();
        }
    }
    /**
     * 获取单词信息
     * @param wordId
     */
    public void getWord(final int wordId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                word = wordService.queryWordByWordId(wordId);
                Message msg = new Message();
                msg.obj = word;
                msg.what = SEND_WORD;
                handler.sendMessage(msg);
            }
        }).start();
    }
    //在页面显示单词信息
    private void showWordInfo(Word word){
        if(word == null) return;
        Log.d(TAG, "showWordInfo: " + word.toString());
        String wordName = word.getWordName();
        wordComplement.setText(wordName);
        wordWrite.setText("");
        wordMean.setText("");
        List<Mean> means = word.getMeans();
        if(means != null && !means.isEmpty()){
            for(Mean mean : means){
                wordMean.append(mean.getPart() + " " + mean.getMean() + "\n");
            }
        }
        //播放音频
        if(word.getProUsa().length() > 0) {
            player(ip + word.getProUsa());
        }else{
            player(ip + word.getProUk());
        }
    }

    final private  MediaPlayer mediaPlayer = new MediaPlayer();
    private String ip = "http://123.206.29.55/";
    /**
     * 播放读音
     * @param url
     */
    public void player(final String url){
        if(url == null ) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync(); // prepare自动播放 ;
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            // 装载完毕回调
                            mediaPlayer.start();
                        }
                    });
                }catch (IOException e){
                    Log.d(TAG, "player: " + e);
                }
            }
        }).start();

    }

    private final int SEND_WORD = 1;
    private final int UPDATA_REVIEW_SUCESS = 2;
    private final int UPDATA_REVIEW_FAIL = 3;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case SEND_WORD:{
                    word = (Word) msg.obj;
                    if(word == null){
                        Toast.makeText(ReviewPlanActivity.this,"单词为空",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ReviewPlanActivity.this,HomeActivity.class);
                        startActivity(intent);
//                        onDestroy();
                    }else{
                        showWordInfo(word);
                    }
                    break;
                }
                case UPDATA_REVIEW_FAIL:{
                    Toast.makeText(ReviewPlanActivity.this,"计划更新失败",Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }
    };

}
