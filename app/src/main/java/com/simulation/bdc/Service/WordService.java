package com.simulation.bdc.Service;

import android.util.Log;

import com.simulation.bdc.enitity.Word;
import com.simulation.bdc.util.ConnecteURL;
import com.simulation.bdc.util.ParseJson;
import com.simulation.bdc.util.RequestURL;

import java.util.List;

public class WordService {

    private static final String TAG = "WordService";
    private ConnecteURL connecteURL = new ConnecteURL();

    private RequestURL requestUrl = new RequestURL();

    /**
     * 通过单词拼写查找单词信息
     * @param word
     * @return
     */
    public List<Word> queryWordByName(String word) {
        String url = requestUrl.queryWordByWordName(null,word);
        String wordJson = connecteURL.connecteUrl(url);
        List<Word> wordList = ParseJson.parseWord(wordJson);
        return wordList;
    }
}
