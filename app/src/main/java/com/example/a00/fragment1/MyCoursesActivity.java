package com.example.a00.fragment1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.bdcapp.BookInfoActivity;
import com.example.a00.bdcapp.R;
import com.simulation.bdc.Service.BookService;
import com.simulation.bdc.adapter.BookAdapter;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.util.PictureBitmap;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class MyCoursesActivity extends AppCompatActivity {
    private static final String TAG = "MyCoursesActivity";
    private TextView book,downloaded;
    private ListView bookList;
    private String ip = "http://123.206.29.55/";

    private List<Book> books;
    private BookService bookService = new BookService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_courses);

        book = findViewById(R.id.book);
        downloaded = findViewById(R.id.downloaded);

        bookList = findViewById(R.id.book_list);
        bookList.setOnItemClickListener(itemClickListener);

        books = bookService.queryBooksFromLocal();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < books.size();i++){
                    Book book = books.get(i);
                    book.setCoverPictureBitmap(PictureBitmap.getPictureBitMap(ip + book.getCoverPicture()));
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BookAdapter bookAdapter = new BookAdapter(MyCoursesActivity.this,books);
                        bookList.setAdapter(bookAdapter);
                    }
                });
            }
        }).start();

    }


    ListView.OnItemClickListener itemClickListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            BookAdapter.ListItemView listItemView = (BookAdapter.ListItemView) view.getTag();
            BookInfoActivity.startAction(MyCoursesActivity.this,listItemView.book.getBookId());
        }
    };
}
