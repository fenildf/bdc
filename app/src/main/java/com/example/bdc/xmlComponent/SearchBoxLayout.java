package com.example.bdc.xmlComponent;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.a00.bdcapp.R;
import com.example.bdc.bdcapp.HomeActivity;
import com.example.bdc.bdcapp.HomeFragment;
import com.example.bdc.bdcapp.MainActivity;
import com.example.bdc.bdcapp.SearchActivity;
import com.simulation.lab.bdc.adapter.WordAdapter;
import com.simulation.lab.bdc.enitity.Word;

import java.util.ArrayList;
import java.util.List;

public class SearchBoxLayout extends LinearLayout {
    private static final String TAG = "SearchBoxLayout";
    private SearchView searchView;//搜索框

    private ListView listView; //搜索列表

    private List<Word> wordList;//单词列表

    private Context context;

    public SearchBoxLayout(Context context) {
        super(context);
    }

    public SearchBoxLayout(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.search_box,this);
        searchView = (SearchView) findViewById(R.id.search_view);

        searchView.setOnClickListener(onClickListener);

        listView = (ListView) findViewById(R.id.list_view);
        init();
        WordAdapter wordAdapter = new WordAdapter( context, R.layout.word_item, wordList);
        listView.setAdapter(wordAdapter);
        listView.setTextFilterEnabled(true);
    }
    private View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + "开始跳转页面");
            Intent intent = new Intent(getContext(), SearchActivity.class);
            context.startActivity(intent);
            Log.d(TAG, "onClick: " + "跳转页面结束");
        }
    };

    public void init(){
        wordList =new ArrayList<Word>();
        for(int i = 0;i < 10;i++){
            Word word = new Word();
            word.setWordName("word" + i);
            wordList.add(word);
        }
    }
    private SearchView.OnQueryTextListener serachViewListener = new SearchView.OnQueryTextListener(){

        // 当点击搜索按钮时触发该方法
        @Override
        public boolean onQueryTextSubmit(String query) {
            return true;
        }
        // 当搜索内容改变时触发该方法
        @Override
        public boolean onQueryTextChange(String newText) {
            return true;
        }
    };


}
