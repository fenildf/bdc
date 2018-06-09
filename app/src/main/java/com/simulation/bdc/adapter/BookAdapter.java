package com.simulation.bdc.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Word;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends BaseAdapter {
    private List<Book> books = new ArrayList<Book>();
    private Context context;
    private LayoutInflater listContainer;     //视图容器
    private static final String TAG = "BookAdapter";
    private String ip = "http://123.206.29.55/";
    ListItemView listItemView = null;

    public BookAdapter(Context context, List<Book> books){
        this.context = context;
        this.books = books;
        listContainer = LayoutInflater.from(context);    //创建视图容器并设置上下文
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            listItemView = new ListItemView();
            convertView = listContainer.inflate(R.layout.book_item,null);
            listItemView.bookName = (TextView)convertView.findViewById(R.id.book_name);
            listItemView.bookCoverPicture = (ImageView) convertView.findViewById(R.id.book_cover_picture);
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView) convertView.getTag();
        }
        final Book book = books.get(position);
        listItemView.book = book;
        listItemView.bookName.setText(book.getBookName());
        listItemView.bookCoverPicture.setImageBitmap(book.getCoverPictureBitmap());
        return convertView;
    }


    //自定义控件集合
    public final class ListItemView {
        public Book book;
        public TextView bookName;
        public ImageView bookCoverPicture;
    }


//    public Bitmap getPictureBitMap(String url) {
//        URL pictureUrl = null;
//        Bitmap bitmap = null;
//        try {
//            pictureUrl = new URL(url);
//        } catch (MalformedURLException e) {
//            Log.e(TAG, "returnBitMap: " + e);
//        }
//        try {
//            HttpURLConnection conn = (HttpURLConnection) pictureUrl
//                    .openConnection();
//            conn.setDoInput(true);
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            bitmap = BitmapFactory.decodeStream(is);
//            is.close();
//        } catch (IOException e) {
//            Log.e(TAG, "returnBitMap: " + e);
//        }
//        return bitmap;
//    }
}
