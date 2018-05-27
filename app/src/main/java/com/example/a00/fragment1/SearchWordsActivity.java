package com.example.a00.fragment1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.Service.WordService;
import com.simulation.bdc.adapter.WordAdapter;
import com.simulation.bdc.enitity.Word;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SearchWordsActivity extends AppCompatActivity {
    private static final String TAG = "SearchWordsActivity";
    private SearchView searchView;//搜索框
    private ListView wordListView;//单词列表
    private ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

    private Thread lastThread;
    private String currentSearchTip;
    private WordService wordService = new WordService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_words);

        searchView = findViewById(R.id.search);
        searchView.setIconified(false);
        searchView.setSubmitButtonEnabled(true);
        wordListView = (ListView) findViewById(R.id.word_list_view);

        searchView.setOnQueryTextListener(searchQueryTextList);

    }
    public final int  SUBMIT = 1;
    private SearchView.OnQueryTextListener searchQueryTextList =  new SearchView.OnQueryTextListener() {
        List<Word> wordList = null;
        @Override
        public boolean onQueryTextSubmit(final String query) {

            if("".equals(query) || query== null){
                return false;
            } else {
                if(lastThread != null){
                    lastThread.interrupt();
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Word word = wordService.queryWordByWordName(query);
                    }
                }).start();

                return true;
            }
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.i(TAG, "run: " + newText);
            if(newText != null && newText.length() > 0) {
                currentSearchTip = newText;
                if(lastThread != null){
                    lastThread.interrupt();
                }
                lastThread = new Thread(new Runnable() {
                    Message msg = new Message();
                    @Override
                    public void run() {
                        List<Word> wordList = wordService.queryWordByName(currentSearchTip );
                        msg.obj = wordList;
                        handler.sendMessage(msg);
                    }
                });
                schedule(lastThread, 200);
            }
            return true;
        }
    };

    /**
     * 设置线程休眠
     * @param command
     * @param delayTimeMills
     * @return
     */
    public ScheduledFuture<?> schedule(Runnable command, long delayTimeMills) {
        return scheduledExecutor.schedule(command, delayTimeMills, TimeUnit.MILLISECONDS);
    }

    /**
     * 显示在线提示
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List<Word> wordList = (List<Word>) msg.obj;
            if(wordList != null) {
                WordAdapter wordAdapter = new WordAdapter(SearchWordsActivity.this, wordList);
                wordListView.setAdapter(wordAdapter);
            }
        }
    };
}
