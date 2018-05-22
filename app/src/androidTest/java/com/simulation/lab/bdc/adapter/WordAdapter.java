package com.simulation.lab.bdc.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.a00.bdcapp.R;
import com.simulation.lab.bdc.enitity.Word;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int resourceId;

    public WordAdapter(Context context, int resource, @NonNull List<Word> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word word = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        } else {
            view = convertView;
        }
        TextView wordView = view.findViewById(R.id.list_word_item);
        wordView.setText(word.getWordName());
        return view;
    }
}
