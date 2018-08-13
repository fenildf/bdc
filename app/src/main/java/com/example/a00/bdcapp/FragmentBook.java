package com.example.a00.bdcapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.simulation.bdc.Service.BookService;
import com.simulation.bdc.adapter.BookAdapter;
import com.simulation.bdc.enitity.Book;

import java.util.List;


public class FragmentBook extends Fragment {
    private View view;
    private ListView bookList;
    private List<Book> books;
    private BookService bookService = new BookService();
    private String ip = "http://123.206.29.55/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book, container, false);
        bookList = view.findViewById(R.id.lv_book);
        bookList.setOnItemClickListener(itemClickListener);
        books = bookService.queryBooksFromLocal();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);
                    bookService.getBookCoverPicture(book);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BookAdapter bookAdapter = new BookAdapter(getActivity(), books);
                        bookList.setAdapter(bookAdapter);
                    }
                });
            }
        }).start();
        return view;
    }

    ListView.OnItemClickListener itemClickListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BookAdapter.ListItemView listItemView = (BookAdapter.ListItemView) view.getTag();
            BookInfoActivity.startAction(getActivity(),listItemView.book.getBookId());
        }
    };
}