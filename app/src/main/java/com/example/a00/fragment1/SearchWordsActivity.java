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
    private SearchView searchView;
    private TextView word,pronounce,england,america,meaning,meaningshow,eg,egshow,relatedwords,rwshow;
    private ImageButton enpronounce,ampronounce;
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
        public boolean onQueryTextSubmit(String query) {

            if("".equals(query) || query== null){
                return false;
            } else {
//                Intent intent = new Intent(S)
                return true;
            }
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            Log.i(TAG, "run: " + newText);
            if(newText != null && newText.length() > 0) {
                currentSearchTip = newText;
                Toast.makeText(SearchWordsActivity.this, newText, Toast.LENGTH_SHORT).show();
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
                schedule(lastThread, 0);
            }
            return true;
        }
    };
    public ScheduledFuture<?> schedule(Runnable command, long delayTimeMills) {
        return scheduledExecutor.schedule(command, delayTimeMills, TimeUnit.MILLISECONDS);
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List<Word> wordList = (List<Word>) msg.obj;
            if(wordList != null) {
//                List<Word> showList = wordList;
//                if(wordList.size() > 10){
//                    showList = wordList.subList(0,10);
//                }
                WordAdapter wordAdapter = new WordAdapter(SearchWordsActivity.this, wordList);
                wordListView.setAdapter(wordAdapter);
            }
        }
    };
}
