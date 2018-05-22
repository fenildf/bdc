package com.example.bdc.fragment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

public class SearchWordsActivity extends AppCompatActivity {
    private SearchView search;
    private TextView word,pronounce,england,america,meaning,meaningshow,eg,egshow,relatedwords,rwshow;
    private ImageButton enpronounce,ampronounce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_words);
        search = findViewById(R.id.search);
        word = findViewById(R.id.word);
        pronounce = findViewById(R.id.pronounce);
        england = findViewById(R.id.england);
        america = findViewById(R.id.america);
        meaning = findViewById(R.id.meaning);
        meaningshow = findViewById(R.id.meaningshow);
        eg = findViewById(R.id.eg);
        egshow = findViewById(R.id.egshow);
        relatedwords = findViewById(R.id.relatedwords);
        rwshow = findViewById(R.id.rwshow);
    }
}
