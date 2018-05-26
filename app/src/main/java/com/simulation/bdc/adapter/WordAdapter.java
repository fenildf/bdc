package com.simulation.bdc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.simulation.bdc.enitity.Mean;
import com.simulation.bdc.enitity.Word;

import java.util.List;
import java.util.Map;

public class WordAdapter extends BaseAdapter {
    private Context context;   //运行上下文
    List<Word> wordList;  //单词集合
    private LayoutInflater listContainer;     //视图容器

    //自定义控件集合
    public final class ListItemView {
        public Word word;
        public TextView word_name;
        public TextView word_mean;
    }

    public WordAdapter(Context context, List<Word> wordList) {
        this.context = context;
        listContainer = LayoutInflater.from(context);    //创建视图容器并设置上下文
        this.wordList = wordList;
    }

    @Override
    public int getCount() {
        return wordList.size();
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
        ListItemView listItemView = null;
        if(convertView == null){
            listItemView = new ListItemView();
            convertView = listContainer.inflate(R.layout.word_item,null);
            listItemView.word_name = (TextView)convertView.findViewById(R.id.word_name);
            listItemView.word_mean = (TextView) convertView.findViewById(R.id.word_mean);
            convertView.setTag(listItemView);
        }else{
            listItemView = (ListItemView) convertView.getTag();
        }
        Word word = wordList.get(position);
        listItemView.word = word;
        listItemView.word_name.setText(word.getWordName());
        for(Mean mean : word.getMeans()){
            listItemView.word_mean.append(mean.getPart() + " " + mean.getMean());
        }
        return convertView;
    }
}
