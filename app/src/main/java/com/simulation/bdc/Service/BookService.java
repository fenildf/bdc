package com.simulation.bdc.Service;

import android.graphics.Bitmap;
import android.util.Log;

import com.simulation.bdc.enitity.Book;
import com.simulation.bdc.util.ConnecteURL;
import com.simulation.bdc.util.ParseJson;
import com.simulation.bdc.util.PictureBitmap;

import org.litepal.crud.DataSupport;

import java.util.List;

public class BookService {
    private static final String TAG = "BookService";
    private String url = "http://123.206.29.55:8080/lab.bdc/book/";
    private String ip = "http://123.206.29.55/";
    private static ConnecteURL connecteURL = new ConnecteURL();

    /**
     * 查询所有教材信息
     * @return
     */
    public List<Book> queryAllBooks(){
        String reponseData = connecteURL.connecteUrl(url+"query");
        Log.d(TAG, "queryAllBooks: " + reponseData);
        List<Book> books = ParseJson.parseBooks(reponseData);
        DataSupport.deleteAll(Book.class);
        DataSupport.saveAll(books);
        return books;
    }

    /**
     * 从本地数据库中查找所有教材信息
     * @return
     */
    public List<Book> queryBooksFromLocal(){
        return DataSupport.findAll(Book.class);
    }

    /**
     * 通过教材Id查找本地教材信息
     * @param bookId
     * @return
     */
    public Book queryBookFromLocal(int bookId){
        return DataSupport.where("bookId=?",bookId + "").findFirst(Book.class);
    }

    /**
     * 为book设置图片资源
     * @param book
     */
    public void getBookCoverPicture(Book book){
        book.setCoverPictureBitmap(PictureBitmap.getPictureBitMap(ip + book.getCoverPicture()));
    }
}
