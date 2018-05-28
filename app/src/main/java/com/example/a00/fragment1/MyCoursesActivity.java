package com.example.a00.fragment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a00.bdcapp.R;

public class MyCoursesActivity extends AppCompatActivity {

    private TextView book,downloaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        book = findViewById(R.id.book);
        downloaded = findViewById(R.id.downloaded);


    }
}
