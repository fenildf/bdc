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
     * 通过单词拼写模糊查找单词信息
     * @param word
     * @return
     */
    public List<Word> queryWordByName(String word) {
        String url = requestUrl.queryWordByWordName(null,word);
        String wordJson = connecteURL.connecteUrl(url);
        List<Word> wordList = ParseJson.parseWord(wordJson);
        return wordList;
    }

    /**
     * 通过单词拼写精确查找单词信息
     * @param word
     * @return
     */
    public Word queryWordByWordName(String word){
        String url = requestUrl.queryWordByWordName(word);
        String wordJson = connecteURL.connecteUrl(url);
        List<Word> wordList = ParseJson.parseWord(wordJson);
        if(wordList != null && !wordList.isEmpty()){
            return wordList.get(0);
        }else{
            return null;
        }
    }

    /**
     * 通过单元Id查找单词
     * @param unitId
     * @return
     */
    public List<Word> queryWordByUnitId(int unitId){
        List<Word> wordList = null;
        String url = requestUrl.queryWordByUnitId(unitId);
        Log.d(TAG, "queryWordByUnitId: " + url);
        Log.d(TAG, "queryWordByUnitId: " + connecteURL.connecteUrl(url));
        wordList = ParseJson.parseWord(connecteURL.connecteUrl(url));
        return wordList;
    }
}
