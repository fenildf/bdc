package com.example.a00.fragment1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

/**
 *点击故事阅读以后跳转到故事阅读
 */
public class StoryReadingActivity extends AppCompatActivity {
    private TextView storyShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_reading);
        storyShow = findViewById(R.id.storyshow);
        storyShow.setMovementMethod(ScrollingMovementMethod.getInstance());

    }

}
